package com.manskx.currencyconverter.api;

import java.net.HttpURLConnection;

import javax.ws.rs.*;
import javax.ws.rs.core.Response;

import org.junit.Test;

import com.manskx.currencyconverter.manager.Configurations;
import com.manskx.currencyconverter.manager.Controller;
import com.manskx.currencyconverter.manager.Currency;


@Path("/exchange_rate")
public class ExchangeRate {
	 @GET
     @Path("/do")
     @Produces("text/plain")
	 public String hello(  
			 @DefaultValue(Configurations.BASE_CURRENCY_STRING) @QueryParam("from") String from,
			 @QueryParam("to") String to,
			 @DefaultValue("1") @QueryParam("qty") int quantity){
		  if (to == null) {
			    throw new WebApplicationException(
			      Response.status(HttpURLConnection.HTTP_BAD_REQUEST)
			        .entity("'to' parameter is mandatory")
			        .build()
			    );
			  }
		  
		  float result 	= Controller.
							  getInstance().
							  getExchangeRateFromTo(
									  Currency.valueOf(from),
									  Currency.valueOf(to));
		  
         return "result: "+result*quantity;
     }

	 
	 @POST
	 @Path("/update_exchange_rates")
	 @Produces("text/plain")
	 public String updateExchangeRates(){
		 return String.valueOf(Controller.getInstance().UpdateExchangeRates());
	 }
}
