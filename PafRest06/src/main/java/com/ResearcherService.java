package com;


import model.Researcher;

//For REST Service
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;

//For JSON
import com.google.gson.*;

//For XML
import org.jsoup.*;
import org.jsoup.parser.*;
import org.jsoup.nodes.Document;

@Path("/Items")

public class ResearcherService
{
		Researcher itemObj = new Researcher();
		/*
		@GET
		@Path("/")
		@Produces(MediaType.TEXT_HTML)
		public String readItems()
		{
			return itemObj.readItems();
		}
		
		
		@POST
		@Path("/")
		@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
		@Produces(MediaType.TEXT_PLAIN)
		public String insertItem(@FormParam("userName") String itemName,
							@FormParam("userPassword") String itemPw,
							@FormParam("userCode") String itemCode,
							@FormParam("userEmail") String itemEmail,
							@FormParam("userPhone") String itemPh)
		{
				String output = itemObj.insertItem(itemName,itemPw , itemCode, itemEmail, itemPh);
				return output;
		}
		
*/
		
		@PUT
		@Path("/")
		@Consumes(MediaType.APPLICATION_JSON)
		@Produces(MediaType.TEXT_PLAIN)
		public String updateItem(String itemData)
		{
			//Convert the input string to a JSON object
			JsonObject itemObject = new JsonParser().parse(itemData).getAsJsonObject();
			//Read the values from the JSON object
			String itemID = itemObject.get("userID").getAsString();
			String itemName = itemObject.get("userName").getAsString();
			String itemPw = itemObject.get("userPassword").getAsString();
		
			String itemEmail = itemObject.get("userEmail").getAsString();
			String itemPh = itemObject.get("userPhone").getAsString();
			String output = itemObj.updateItem(itemID, itemName, itemPw, itemEmail, itemPh);
			return output;
		}
		
		
		@DELETE
		@Path("/")
		@Consumes(MediaType.APPLICATION_XML)
		@Produces(MediaType.TEXT_PLAIN)
		public String deleteItem(String itemData)
		{
		//Convert the input string to an XML document
		 Document doc = Jsoup.parse(itemData, "", Parser.xmlParser());

		//Read the value from the element <itemID>
		 String itemID = doc.select("userID").text();
		 String output = itemObj.deleteItem(itemID);
		return output;
		}

		
}



