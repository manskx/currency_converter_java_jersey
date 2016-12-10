package com.manskx.currencyconverter.manager;

import org.json.JSONObject;

public class ExchangeRates {
	private static ExchangeRates instance;
	
	public JSONObject rates;

	private ExchangeRates() {
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
