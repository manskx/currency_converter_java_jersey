# README #

CurrencyConverter Service.
This is a currency converter from different currencies in all directions
Input exchange rate is crawred from "fixer.io"
### Technologies  ###
* jersey
* Maven 3
* Tomcat 8.5
* jersey test framework
* junit
### How do I get set up? ###
This is eclipse based project. 
just import it as eclipse project and configure tomcat server
### Example Usage ##

Service: Convert currencies
Returns json conversion rate.
URL: /CurrencyCoverter/api/exchange_rate/do
Method: GET
URL Params:
	Required:
		to=[String][Currency Code] 
	Optional:
		from=[String][Currency Code][Default:"EUR"]
		qty=[Integer][Quantity][Default:1]
Success Response:
	Code: 200 
Content: 
	{
	  "result": "26.784735",
	  "quantity": 20,
	  "from": "AUD",
	  "to": "USD"
	}
Error Response:
	Code: 404 NOT FOUND
	Code: 400 BAD REQUEST 
Sample Call: /CurrencyCoverter/api/exchange_rate/do?from=AUD&to=USD&qty=20


Service: Update currencies Exchange rate ( helpful for cron tasks)
Returns boolean.
URL /CurrencyCoverter/api/exchange_rate/update_exchange_rates
Method: POST
URL Params: none
Success Response:
	Code: 200 
Content: true
Error Response:
	Code: 404 NOT FOUND
Sample Call: /CurrencyCoverter/api/exchange_rate/update_exchange_rates

### contact ###
Ahmed Mansy
ahmed.mansy156@gmail.com