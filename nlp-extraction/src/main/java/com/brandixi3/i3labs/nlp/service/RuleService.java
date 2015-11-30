package com.brandixi3.i3labs.nlp.service;

import java.io.StringReader;
import java.io.StringWriter;

import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.soap.MessageFactory;
import javax.xml.soap.MimeHeaders;
import javax.xml.soap.SOAPBody;
import javax.xml.soap.SOAPConnection;
import javax.xml.soap.SOAPConnectionFactory;
import javax.xml.soap.SOAPElement;
import javax.xml.soap.SOAPEnvelope;
import javax.xml.soap.SOAPMessage;
import javax.xml.soap.SOAPPart;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathConstants;
import javax.xml.xpath.XPathFactory;

import org.w3c.dom.CDATASection;
import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.xml.sax.InputSource;

import com.brandixi3.i3labs.nlp.core.Configurations;
import com.brandixi3.i3labs.nlp.model.Rank;
import com.brandixi3.i3labs.nlp.model.rule.Demographics;
import com.brandixi3.i3labs.nlp.model.rule.RuleEvent;
import com.thoughtworks.xstream.XStream;

// FIXME Should be optimized and revamped before use in the production.
public class RuleService {

	public static void main(String[] args) {
		RuleEvent event = new RuleEvent();

		Demographics demo = new Demographics();
		demo.setFirstName("Dushman");
		event.setDemographics(demo);

		Rank rank = new Rank();
		rank.setValue(0);
		event.setRank(rank);
		getExecutedRule(event);

	}

	public static RuleEvent getExecutedRule(RuleEvent event) {
		System.out.println("Executing rules");
		RuleEvent returnedEvent = null;
		try {
			// Create SOAP Connection
			SOAPConnectionFactory soapConnectionFactory = SOAPConnectionFactory
					.newInstance();
			SOAPConnection soapConnection = soapConnectionFactory
					.createConnection();

			// Send SOAP Message to SOAP Server
			String url = Configurations.getConfig("rule.service.url");
			System.out.println("sending..");
			SOAPMessage soapResponse = soapConnection.call(
					createSOAPRequest(event), url);

			// Process the SOAP Response
			// printSlOAPResponse(soapResponse);

			String value = soapResponse.getSOAPBody()
					.getElementsByTagName("ns1:return").item(0).getFirstChild()
					.getNodeValue();
			System.out.println("value==== " + value);

			DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
			Document doc = dbf.newDocumentBuilder().parse(
					new InputSource(new StringReader(value)));

			XPath xPath = XPathFactory.newInstance().newXPath();
			Node result = (Node) xPath.evaluate(
					"//com.brandixi3.i3labs.nlp.model.rule.RuleEvent", doc,
					XPathConstants.NODE);
			StringWriter buf = new StringWriter();
			Transformer xform = TransformerFactory.newInstance()
					.newTransformer();
			xform.setOutputProperty(OutputKeys.OMIT_XML_DECLARATION, "yes");
			xform.transform(new DOMSource(result), new StreamResult(buf));
			String obj = buf.toString();

			XStream xStream = new XStream();
			returnedEvent = (RuleEvent) xStream.fromXML(obj);
			System.out.println(returnedEvent.getRank().getValue());

			soapConnection.close();
		} catch (Exception e) {
			System.err
					.println("Error occurred while sending SOAP Request to Server");
			e.printStackTrace();
		}
		return returnedEvent;
	}

	private static SOAPMessage createSOAPRequest(RuleEvent event)
			throws Exception {
		MessageFactory messageFactory = MessageFactory.newInstance();
		SOAPMessage soapMessage = messageFactory.createMessage();
		SOAPPart soapPart = soapMessage.getSOAPPart();

		String serverURI = Configurations.getConfig("rule.service.url");

		SOAPEnvelope envelope = soapPart.getEnvelope();
		envelope.addNamespaceDeclaration("soap", "http://soap.jax.drools.org/");

		XStream xStream = new XStream();

		String xml = xStream.toXML(event);

		StringBuilder builder = new StringBuilder(
				"<batch-execution lookup=\"ksession1\"><insert out-identifier=\"message\">");
		builder.append(xml);
		builder.append("</insert><fire-all-rules/></batch-execution>");

		// SOAP Body
		SOAPBody soapBody = envelope.getBody();

		SOAPElement soapBodyElem = soapBody.addChildElement("execute", "soap");
		SOAPElement soapBodyElem1 = soapBodyElem
				.addChildElement("arg0", "soap");
		CDATASection cdata = soapBodyElem1.getOwnerDocument()
				.createCDATASection(builder.toString());
		soapBodyElem1.appendChild(cdata);

		MimeHeaders headers = soapMessage.getMimeHeaders();
		headers.addHeader("SOAPAction", serverURI + "execute");

		soapMessage.saveChanges();

		/* Print the request message */
		System.out.print("Request SOAP Message = ");
		soapMessage.writeTo(System.out);
		System.out.println();

		return soapMessage;
	}

	/*	*//**
	 * Method used to print the SOAP Response
	 */
	/*
	 * private static void printSOAPResponse(SOAPMessage soapResponse) throws
	 * Exception { TransformerFactory transformerFactory = TransformerFactory
	 * .newInstance(); Transformer transformer =
	 * transformerFactory.newTransformer(); Source sourceContent =
	 * soapResponse.getSOAPPart().getContent();
	 * System.out.print("\nResponse SOAP Message = "); StreamResult result = new
	 * StreamResult(System.out); transformer.transform(sourceContent, result); }
	 */

}
