package com.manskx.currencyconverter.test;


import static org.junit.Assert.*;
import javax.ws.rs.core.Application;

import org.glassfish.jersey.server.ResourceConfig;
import org.glassfish.jersey.test.JerseyTest;
import org.junit.Test;

import com.manskx.currencyconverter.api.ExchangeRate;
import com.manskx.currencyconverter.manager.ExchangeRates;

public class ExchangeRateTest extends JerseyTest {


	@Override
	protected Application configure() {
		return new ResourceConfig(ExchangeRate.class);
	}
	@Test
	public void test() {
		String hello = target("exchange_rate/do")
				.queryParam("from","EUR")
				.queryParam("to", "USD")
				.queryParam("qty", 1)
				.request().get(String.class);
		ExchangeRates exchangeRates = ExchangeRates.getInstance();
		double ExchangeRate = exchangeRates.getRates().getJSONObject("rates").getDouble("USD");
		assertEquals("{\"result\":\""+(ExchangeRate*1)+"\",\"quantity\":1,\"from\":\"EUR\",\"to\":\"USD\"}", hello);
	}
	@Test
	public void test2() {
		String hello = target("exchange_rate/do")
				.queryParam("from","JPY")
				.queryParam("to", "JPY")
				.request().get(String.class);
		assertEquals("{\"result\":\"1.0\",\"quantity\":1,\"from\":\"JPY\",\"to\":\"JPY\"}", hello);
	}
	@Test
	public void test3() {
		String hello = target("exchange_rate/do")
				.queryParam("from","USD")
				.queryParam("to", "USD")
				.request().get(String.class);
		assertEquals("{\"result\":\"1.0\",\"quantity\":1,\"from\":\"USD\",\"to\":\"USD\"}", hello);
	}
	@Test
	public void test4() {
		String hello = target("exchange_rate/do")
				.queryParam("from","USD")
				.queryParam("to", "USD")
				.request().get(String.class);
		assertEquals("{\"result\":\"1.0\",\"quantity\":1,\"from\":\"USD\",\"to\":\"USD\"}", hello);
	}
	
}
