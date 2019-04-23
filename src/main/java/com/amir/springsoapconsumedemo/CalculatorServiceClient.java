package com.amir.springsoapconsumedemo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.amir.client.Add;
import com.amir.client.AddResponse;

@Component
public class CalculatorServiceClient {

@Autowired	
 private SoapClientService soapClientService;	

private static String SOAP_ADDRESS_LOCATION="http://www.dneonline.com/calculator.asmx";

	public int addTwoNumber(int a,int b) {
		int ret = 0;
		String soapAction="http://tempuri.org/Add";	
		Add request =  new Add();
		request.setIntA(a);
		request.setIntB(b);
		AddResponse addResponse = soapClientService.callSoapService(SOAP_ADDRESS_LOCATION, soapAction, request);
		ret = addResponse.getAddResult();
		return ret;
	}
	
}
