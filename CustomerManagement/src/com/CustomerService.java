package com;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.FormParam;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.parser.Parser;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import model.Customer;



@Path("/Customers")
public class CustomerService {

	Customer customer = new Customer();

	@GET
	@Path("/")
	@Produces(MediaType.TEXT_HTML)
	public String displayCustomerDetails()
	{
		return customer.displayCustomerDetails();
	}
	
	@POST
	@Path("/")
	@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
	@Produces(MediaType.TEXT_PLAIN)
	public String insertCustomer(@FormParam("customerName") String customerName,
			@FormParam("customerType") String customerType,
			@FormParam("addedDate") String addedDate,
			@FormParam("email") String email,
			@FormParam("password") String password)
	{
		String output = customer.insertCustomer(customerName, customerType, addedDate, email, password);
		return output;
	}
	
	@PUT
	@Path("/")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.TEXT_PLAIN)
	public String updateCustomer(String customerData)
	{
		//Convert the input string to a JSON object
		JsonObject customerObj = new JsonParser().parse(customerData).getAsJsonObject();
		//Read the values from the JSON object
		String customerId = customerObj.get("customerId").getAsString();
		String customerName = customerObj.get("customerName").getAsString();
		String customerType = customerObj.get("customerType").getAsString();
		String addedDate = customerObj.get("addedDate").getAsString();
		String email = customerObj.get("email").getAsString();
		String password = customerObj.get("password").getAsString();
		String output = customer.updateCustomer(customerId, customerName, customerType, addedDate, email, password);
		return output;
	}

	@DELETE
	@Path("/")
	@Consumes(MediaType.APPLICATION_XML)
	@Produces(MediaType.TEXT_PLAIN)
	public String deleteCustomer(String customerData)
	{
		//Convert the input string to an XML document
		Document doc = Jsoup.parse(customerData, "", Parser.xmlParser());

		//Read the value from the element <researchID>
		String customerId = doc.select("customerId").text();
		String output = customer.deleteCustomer(customerId);
				
		return output;
	}


}
