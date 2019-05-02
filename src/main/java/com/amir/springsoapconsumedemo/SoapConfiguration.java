package com.amir.springsoapconsumedemo;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.oxm.jaxb.Jaxb2Marshaller;

@Configuration
public class SoapConfiguration {

	@Bean
	public Jaxb2Marshaller marshaller() {
		Jaxb2Marshaller marshaller = new Jaxb2Marshaller();
		//marshaller.setContextPath("com.amir.client");
		marshaller.setPackagesToScan("com.amir.client","com.amir.student");
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
	}
	
	/*@Bean
	  public HttpComponentsMessageSender httpComponentsMessageSender() {
	    HttpComponentsMessageSender httpComponentsMessageSender = new HttpComponentsMessageSender();
	    // set the basic authorization credentials
	    httpComponentsMessageSender.setCredentials(usernamePasswordCredentials());

	    return httpComponentsMessageSender;
	  }

	  @Bean
	  public UsernamePasswordCredentials usernamePasswordCredentials() {
	    // pass the user name and password to be used
	    return new UsernamePasswordCredentials("userName", "userPassword");
	  }*/
}
