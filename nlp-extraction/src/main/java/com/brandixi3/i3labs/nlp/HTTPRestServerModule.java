package com.brandixi3.i3labs.nlp;

import static io.advantageous.qbit.http.server.HttpServerBuilder.httpServerBuilder;
import static io.advantageous.qbit.server.EndpointServerBuilder.endpointServerBuilder;
import io.advantageous.qbit.http.server.HttpServer;
import io.advantageous.qbit.server.ServiceEndpointServer;
import io.advantageous.qbit.system.QBitSystemManager;

import com.brandixi3.i3labs.nlp.core.InitializableModule;
import com.brandixi3.i3labs.nlp.core.InitializationException;
import com.brandixi3.i3labs.nlp.service.NLPRestService;

import static org.boon.Boon.resource;
//import static io.advantageous.boon.core.

public class HTTPRestServerModule implements InitializableModule {

	ServiceEndpointServer server = null;

	public static final String HTML_PAGE = "/ui/index.html";

	public static final String HIGHLIGHT_HTML_PAGE = "/ui/highlight.html";
	
	@Override
	public void init() throws InitializationException {

		//final ServiceEndpointServer serviceEndpointServer = new EndpointServerBuilder()
		//		.setFlushInterval(50).setRequestBatchSize(10).setPort(6060)
		//		.build().initServices(new NLPRestService()).startServer();


		
		/* Create the system manager to manage the shutdown. */
        QBitSystemManager systemManager = new QBitSystemManager();

        
		HttpServer httpServer =  httpServerBuilder().setPort(6060).build();
				
		httpServer.setShouldContinueHttpRequest(httpRequest -> {
   
            /* Send the HTML file out to the browser. */
            if(httpRequest.getUri().equals("/")){
                httpRequest.getReceiver().response(200, "text/html", resource(HTML_PAGE));
            } else if (httpRequest.getUri().equals("/hightlight")){
                httpRequest.getReceiver().response(200, "text/html", resource(HIGHLIGHT_HTML_PAGE));
            }
            else {
                return true;
            } 
            
            return false;
        });
		
		ServiceEndpointServer serviceServer = endpointServerBuilder().setSystemManager(systemManager)
		                .setHttpServer(httpServer).build().initServices(new NLPRestService()).startServer();

		systemManager.waitForShutdown();
		
		// Sys.sleep(1_000_000_000);
	}

	@Override
	public void stop() {
		server.stop();
	}

}
