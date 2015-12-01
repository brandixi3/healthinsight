# Health Insight
A generic framework consisting of NLP, Machine Learning and Rules for analyzing unstructured clinical data.
Please refer below link for more information.

Important:Learning purposes only. Not yet ready for production use.

https://whitereflections.wordpress.com/2015/11/29/insight-on-unstructured-clinical-documents/

##Build 
mvn clean install

##Deploy

###Deploy on docker.
1. Copy two war files located in /nlp-extraction/target and /nlp-rules/target in to /deployable directory
2. Deploy docker file located in /deployable/Dockerfile

###Deploy on tomcat.
Deploy two war files located in /nlp-extraction/target and /nlp-rules/target
