package com.manskx.currencyconverter.api;

import java.net.HttpURLConnection;

import javax.ws.rs.*;
import javax.ws.rs.core.Response;


@Path("/exchange_rate")
public class ExchangeRate {
	 @GET
     @Path("/")
     @Produces("text/plain")
	 public String hello(  
			 @QueryParam("from") String from,
			 @QueryParam("to") String to,
			 @DefaultValue("1") @QueryParam("qty") int quantity){
		  if (from == null || to == null) {
			    throw new WebApplicationException(
			      Response.status(HttpURLConnection.HTTP_BAD_REQUEST)
			        .entity("'from' and 'to' parameter is mandatory")
			        .build()
			    );
			  }
         return "from: "+from+ " to: "+to+ "qty: "+quantity;
     }
}
