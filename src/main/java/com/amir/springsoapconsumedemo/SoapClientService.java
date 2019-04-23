package com.amir.springsoapconsumedemo;

import org.springframework.ws.client.core.WebServiceTemplate;
import org.springframework.ws.client.core.support.WebServiceGatewaySupport;
import org.springframework.ws.soap.client.core.SoapActionCallback;

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
