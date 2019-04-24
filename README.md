Spring webservice(JAX_WS) Client with jaxws-maven-plugin.
--------------------------------------------------------
Note:
-----
		Important about wsdl elements which going to be use while creating client.<br>
		1-spring-uri or endpoint of wsdl is known as <soap:address location=?> <br>
		2-spring-soapAction or operation point is known as <soap:operation soapAction=?> <br>
		3-you can find both value inside wsdl and use them in client code.


How to make Spring-Soap-Client
----------------------------
Step-1
-----
create spring boot project with dependency called-"spring-boot-starter-web-services"

step-2
------
add jaxws-maven-plugin into pom.xml and generated jaxb classes.

Example
--------

			<plugin>
					<groupId>org.codehaus.mojo</groupId>
					<artifactId>jaxws-maven-plugin</artifactId>
					<version>2.5</version>
					<executions>
						<execution>
							<id>wsimport-from-jdk</id>
							<goals>
								<goal>wsimport</goal>
							</goals>

						<configuration>
							<wsdlUrls>
								<wsdlUrl>http://www.dneonline.com/calculator.asmx?WSDL</wsdlUrl>
							</wsdlUrls>
							<packageName>com.amir.client</packageName>
						</configuration>

					</execution>

				</executions>
			</plugin>

			
			
step-3
--------

create  child class of WebServiceGatewaySupport to call the soap operation<br>

Example:
-----
	public class SoapClientService extends WebServiceGatewaySupport {	
		@SuppressWarnings("unchecked")
		public <T> T  callSoapService(String location,String soapAction,Object request) {
			 T ret =  null;
			 WebServiceTemplate webServiceTemplate = getWebServiceTemplate();
			 SoapActionCallback soapActionCallback = new SoapActionCallback(soapAction);
			 ret = (T) webServiceTemplate.marshalSendAndReceive(location, request, soapActionCallback);
			 return ret;
		}
	}

location --you can get from wsdl it is value of <soap:address..><br>
soapAction --you can get from wsdl it is valie of <soap:operation..><br>

step-4
--------
create configuration class and add following 2 beans<br>
1-Jaxb2Marshaller<br>
2-SoapClientService (user class extends WebServiceGatewaySupport)<br>

Example:
--------
	@Configuration
	public class SoapConfiguration {
	@Bean
	public Jaxb2Marshaller marshaller() {
		Jaxb2Marshaller marshaller = new Jaxb2Marshaller();
		marshaller.setContextPath("com.amir.client");
		return marshaller;
	}
	@Bean
	public SoapClientService soapClientService(Jaxb2Marshaller marshaller) {
		SoapClientService client = new SoapClientService();
		//default-uri means wsdl attribue called-<soap:address location=?)
		//client.setDefaultUri("http://www.dneonline.com/calculator.asmx");//no need to tell soap:address(endpoint)
		client.setMarshaller(marshaller);
		client.setUnmarshaller(marshaller);
		//for basic authentication you can use following..
		//client.setMessageSender(httpComponentsMessageSender());
		return client;
	}	}

			
