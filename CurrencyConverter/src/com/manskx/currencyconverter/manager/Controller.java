package com.manskx.currencyconverter.manager;

import javax.ws.rs.client.Client;

import java.io.*;

import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.client.*;

import org.glassfish.jersey.client.ClientResponse;
import org.json.JSONException;
import org.json.JSONObject;

public class Controller {

	public boolean UpdateExchangeRates() {
		String responseEntity = ClientBuilder.newClient().target("http://api.fixer.io/").path("latest").request()
				.get(String.class);
		JSONObject responseJSON_obj = new JSONObject(responseEntity);
		System.out.println(responseEntity);
		return true;

	}
	
	
	
	

}
