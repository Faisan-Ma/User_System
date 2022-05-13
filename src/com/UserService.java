package com;

import model.User;
//For REST Service
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;

//For JSON
import com.google.gson.*;
//For XML
import org.jsoup.*;
import org.jsoup.parser.*;
import org.jsoup.nodes.Document;
@Path("/Users")

public class UserService{

	User userObj = new User();
	
//	@GET
//	@Path("/")
//	@Produces(MediaType.TEXT_HTML)
//	public String readUsers()
//	{
//	return "Hello";
//	}
	
	//GET
	@GET
	@Path("/")
	@Produces(MediaType.TEXT_HTML)
	public String readUsers()
	{
		return userObj.readUsers();
	}
	
	//POST
	@POST
	@Path("/")
	@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
	@Produces(MediaType.TEXT_PLAIN)
	public String insertUser(@FormParam("userCode") String userCode,
	 @FormParam("userName") String userName,
	 @FormParam("userPnumber") String userPnumber,
	 @FormParam("userGmail") String userGmail)
	{
	 String output = userObj.insertUser(userCode, userName, userPnumber, userGmail);
	return output;
	}
	
	//PUT
	@PUT
	@Path("/")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.TEXT_PLAIN)
	public String updateUser(String userData)
	{
	//Convert the input string to a JSON object
	 JsonObject userObject = new JsonParser().parse(userData).getAsJsonObject();
	//Read the values from the JSON object
	 String ID = userObject.get("ID").getAsString();
	 String userCode = userObject.get("userCode").getAsString();
	 String userName = userObject.get("userName").getAsString();
	 String userPnumber = userObject.get("userPnumber").getAsString();
	 String userGmail = userObject.get("userGmail").getAsString();
	 String output = userObj.updateUser(ID, userCode, userName, userPnumber, userGmail);
	return output;
	}
	
	//DELETE
	@DELETE
	@Path("/")
	@Consumes(MediaType.APPLICATION_XML)
	@Produces(MediaType.TEXT_PLAIN)
	public String deleteUser(String userData)
	{
	//Convert the input string to an XML document
	 Document doc = Jsoup.parse(userData, "", Parser.xmlParser());

	//Read the value from the element <ID>
	 String ID = doc.select("ID").text();
	 String output = userObj.deleteUser(ID);
	return output;
	}

}