package com.manskx.currencyconverter.test;


import static org.junit.Assert.*;
import javax.ws.rs.core.Application;

import org.glassfish.jersey.server.ResourceConfig;
import org.glassfish.jersey.test.JerseyTest;
import org.junit.Test;

import com.manskx.currencyconverter.api.ExchangeRate;

public class ExchangeRateTest extends JerseyTest {


	@Override
	protected Application configure() {
		return new ResourceConfig(ExchangeRate.class);
	}

	@Test
	public void test() {
		final String hello = target("exchange_rate/do")
				.queryParam("from","xxx")
				.queryParam("to", "sss")
				.request().get(String.class);
		assertEquals("result", hello);
	}
}
