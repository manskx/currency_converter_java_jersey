package com.manskx.currencyconverter.manager;

import javax.ws.rs.client.Client;

import java.io.*;

import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.client.*;

import org.glassfish.jersey.client.ClientResponse;
import org.json.JSONException;
import org.json.JSONObject;

public class Controller {
	private static Controller instance;

	private Controller() {
	}

	public static Controller getInstance() {
		if (instance == null) {
			instance = new Controller();
		}
		return instance;
	}

	public boolean UpdateExchangeRates() {
		String responseEntity = ClientBuilder.newClient()
				.target(Configurations.API_URL)
				.path(Configurations.API_URL_PATH)
				.queryParam("base", Configurations.BASE_CURRENCY.name()).request()
				.get(String.class);
		JSONObject responseJSON_obj = new JSONObject(responseEntity);
		ExchangeRates exchangeRates = ExchangeRates.getInstance();
		exchangeRates.setRates(responseJSON_obj);
		return true;
	}

	public float getExchangeRateFromTo(Currency fromCurrency, Currency targetCurrency){
		if(fromCurrency==Configurations.BASE_CURRENCY){
			return getExchangeRateToBase(targetCurrency);
		}
		float fromCurrencyRateToBase	=	getExchangeRateToBase(fromCurrency);
		float targetCurrencyRateToBase	=	getExchangeRateToBase(targetCurrency);
		return (fromCurrencyRateToBase / targetCurrencyRateToBase);
	}
	public float getExchangeRateToBase(Currency targetCurrency) {
		 UpdateExchangeRates();
		ExchangeRates exchangeRates = ExchangeRates.getInstance();
		double ExchangeRate = exchangeRates.getRates().getJSONObject("rates").getDouble(targetCurrency.name());
		return (float)(ExchangeRate);
	}

}
