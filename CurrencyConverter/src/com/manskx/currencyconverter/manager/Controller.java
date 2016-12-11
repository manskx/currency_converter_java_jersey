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



	/**
	 * This method is responsible for converting the currency rates 
	 * from two different currencies 
	 * @param fromCurrency ( input currency )
	 * @param targetCurrency
	 * @return
	 */
	public float getExchangeRateFromTo(Currency fromCurrency, Currency targetCurrency){
		// check if the target equals the base currency
		if(fromCurrency==Configurations.BASE_CURRENCY){
			return getExchangeRateToBase(targetCurrency);
		}
		float fromCurrencyRateToBase	=	getExchangeRateToBase(fromCurrency);
		float targetCurrencyRateToBase	=	getExchangeRateToBase(targetCurrency);
		return (fromCurrencyRateToBase / targetCurrencyRateToBase);
	}
	
	/**
	 * This method is responsible for converting the input currency
	 * to base currency
	 * @param targetCurrency
	 * @return target exchange rate
	 */
	public float getExchangeRateToBase(Currency targetCurrency) {
		ExchangeRates exchangeRates = ExchangeRates.getInstance();
		double ExchangeRate = exchangeRates.getRates().getJSONObject("rates").getDouble(targetCurrency.name());
		return (float)(ExchangeRate);
	}

}
