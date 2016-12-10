package com.manskx.currencyconverter.api;

import java.net.HttpURLConnection;

import javax.ws.rs.*;
import javax.ws.rs.core.Response;

import org.junit.Test;

import com.manskx.currencyconverter.manager.Controller;


@Path("/exchange_rate")
public class ExchangeRate {
	 @GET
     @Path("/do")
     @Produces("text/plain")
	 public String hello(  
			 @QueryParam("from") String from,
			 @QueryParam("to") String to,
			 @DefaultValue("1") @QueryParam("qty") int quantity){
		  if (from == null || to == null) {
			    throw new WebApplicationException(
			      Response.status(HttpURLConnection.HTTP_BAD_REQUEST)
			        .entity("'from' and 'to' parameter are mandatory")
			        .build()
			    );
			  }
         return "from: "+from+ " to: "+to+ "qty: "+quantity;
     }

	 
	 @POST
	 @Path("/update_exchange_rates")
	 @Produces("text/plain")
	 public String updateExchangeRates(){
		 Controller	x =	 new Controller();
		 return String.valueOf(x.UpdateExchangeRates());
	 }
}
