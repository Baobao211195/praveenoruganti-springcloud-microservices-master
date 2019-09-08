package com.praveen.ordermanagement.service;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class OrderManagementService {

	@Autowired
	RestTemplate restTemplate;
	
	@Autowired
	@Value("${praveen-flipkart-ordermanagement-service.billingURL}")
	private String billingURL;

	public String createOrder(String orderid) throws Exception {
		try {
		HttpHeaders headers = new HttpHeaders();
		headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
		HttpEntity<String> entity = new HttpEntity<String>(orderid,headers);
		// call billing service
		String msg=restTemplate
		.exchange(billingURL, HttpMethod.POST, entity, String.class).getBody();
		return msg;
		}catch(Exception  e) {
			throw new Exception(e);
		}
		
		
	}

}
