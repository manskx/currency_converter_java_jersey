package com.manskx.currencyconverter.api;

import java.net.HttpURLConnection;

import javax.ws.rs.*;
import javax.ws.rs.core.Response;

import org.json.JSONObject;

import com.manskx.currencyconverter.manager.Configurations;
import com.manskx.currencyconverter.manager.Controller;
import com.manskx.currencyconverter.manager.Currency;
import com.manskx.currencyconverter.manager.ExchangeRates;

@Path("/exchange_rate")
public class ExchangeRate {
	/**
	 * This the main conversion service that converts the rated from two currencies 
	 * NOTE: Currencies must be in the currencies list ( Enum file)
	 * @param from ( default value is the base currency in config file)
	 * @param to
	 * @param quantity ( default value is 1)
	 * @return
	 */
	@GET
	@Path("/do")
	@Produces("application/json")
	public String hello(@DefaultValue(Configurations.BASE_CURRENCY_STRING) @QueryParam("from") String from,
			@QueryParam("to") String to, @DefaultValue("1") @QueryParam("qty") int quantity) {
		
		if (to == null) {
			throw new WebApplicationException(
					Response.status(HttpURLConnection.HTTP_BAD_REQUEST).entity("'to' parameter is mandatory").build());
		}

		float result = Controller.getInstance().getExchangeRateFromTo(Currency.valueOf(from), Currency.valueOf(to));
		JSONObject resultObj = new JSONObject();
		resultObj.put("result", String.valueOf( result * quantity));
		resultObj.put("from", from);
		resultObj.put("to", to);
		resultObj.put("quantity", quantity);
		return resultObj.toString();
	}

	/**
	 * this service could be used in cron task to periodically 
	 * update the exchange rated. also it could be Google Translate.html 
	 * with authentication
	 */
	@POST
	@Path("/update_exchange_rates")
	@Produces("text/plain")
	public String updateExchangeRates() {
		return String.valueOf(ExchangeRates.getInstance().UpdateExchangeRates());
	}
}
