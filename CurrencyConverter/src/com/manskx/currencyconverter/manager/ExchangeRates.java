package com.manskx.currencyconverter.manager;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileReader;
import java.io.InputStream;

import javax.ws.rs.client.ClientBuilder;

import org.json.JSONObject;
import org.json.simple.parser.JSONParser;

public class ExchangeRates {
	private static ExchangeRates instance;

	public JSONObject rates;

	private ExchangeRates() {
		this.UpdateExchangeRates();
	}
	
	/**
	 * This method is responsible for updating the currency rates
	 */
	public boolean UpdateExchangeRates() {
		String responseEntity = ClientBuilder.newClient()
				.target(Configurations.API_URL)
				.path(Configurations.API_URL_PATH)
				.queryParam("base", Configurations.BASE_CURRENCY.name()).request()
				.get(String.class);
		JSONObject responseJSON_obj = new JSONObject(responseEntity);
		this.setRates(responseJSON_obj);
		return true;
	}
	/**
	 * This method can read the latest exchange rates json from file
	 * 
	 */
	public void readFile() {
		JSONParser parser = new JSONParser();
		try {

			Object obj = parser.parse(new FileReader(Configurations.LATEST_RATES_FILE));
			this.rates = (JSONObject) obj;
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public JSONObject getRates() {
		return rates;
	}

	public void setRates(JSONObject rates) {
		this.rates = rates;
	}

	public static ExchangeRates getInstance() {
		if (instance == null) {
			instance = new ExchangeRates();
		}
		return instance;
	}

}
