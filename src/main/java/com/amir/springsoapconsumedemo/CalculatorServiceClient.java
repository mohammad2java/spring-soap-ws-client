package com.amir.springsoapconsumedemo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.amir.client.Add;
import com.amir.client.AddResponse;
import com.amir.student.FindCountryRequest;
import com.amir.student.FindCountryResponse;

@Component
public class CalculatorServiceClient {

@Autowired	
 private SoapClientService soapClientService;	

private static String SOAP_ADDRESS_LOCATION="http://www.dneonline.com/calculator.asmx";
private static String STUDENT_ADDRESS_LOCATION="http://localhost:8088/ws/services/mystudent";

	public int addTwoNumber(int a,int b) {
		int ret = 0;
		String soapAction="http://tempuri.org/Add";	
		Add request =  new Add();
		request.setIntA(a);
		request.setIntB(b);
		AddResponse addResponse = soapClientService.callSoapService(SOAP_ADDRESS_LOCATION, soapAction, request);
		System.out.println(addResponse);
		ret = addResponse.getAddResult();
		return ret;
	}
	
	
	public String getCountry(String name)  {
		String ret = "";
		//String soapAction="http://www.example.org/demo/findCountryRequest";
		FindCountryRequest req =  new FindCountryRequest();
		req.setName("India");
		FindCountryResponse response = soapClientService.callSpringSoapService(STUDENT_ADDRESS_LOCATION,req);
		ret = response.getCountry().toString();
		return ret;
	}
	
}
