/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rest;

import facade.Facade;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonObject;
import entity.Quote;
import exception.QuoteNotFoundException;
import java.util.HashMap;
import java.util.Map;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.UriInfo;
import javax.ws.rs.Produces;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PUT;
import javax.ws.rs.PathParam;
import javax.ws.rs.core.Response;

/**
 * REST Web Service
 *
 * @author sebastiannielsen
 */
@Path("quote")
public class RestService{
    private static Gson gson = new GsonBuilder().setPrettyPrinting().create();
    
    private static int lastId = 4;
    private static Quote q1 = new Quote(1, "Friends are kisses blown to us by angels");
    private static Quote q2 = new Quote(2, "Do not take life too seriously. You will never get out of it alive");
    private static Quote q3 = new Quote(3, "Behind every great man, is a woman rolling her eyes");

    static Map<Integer, Quote> quotes = new HashMap() {
        {
            put(q1.getId(), q1);
            put(q2.getId(), q2);
            put(q3.getId(), q3);
        }
    };
    
    
    @Context
    private UriInfo context;

    public RestService() {
    }

    // Method 1 - Returns the quote with the given id as: {"quote" : "Quote text"}
    @GET
    @Path("{id}")
    @Produces("application/json")
    public Response getQuote(@PathParam("id") int id) throws QuoteNotFoundException{
        if (id == 0) {
            throw new NullPointerException("fejl");
        }
        else if(!quotes.containsKey(id)){
            throw new QuoteNotFoundException("Quote with requested id not found");
        }
        
        JsonObject json = new JsonObject();
        json.addProperty("quote",quotes.get(id).toString());
        return Response.ok(gson.toJson(json)).build();
    }
    
    // Method 2 - Returns a random quote as: {"quote" : "Quote text"}
    @GET
    @Path("random")
    @Produces("application/json")
    public Response getRandomQuote() throws QuoteNotFoundException{
        if(quotes.isEmpty()){
            throw new QuoteNotFoundException("No Quotes Created yet");
        }
        int id = (int) Math.ceil(Math.random() * (quotes.size()));
        JsonObject json = new JsonObject();
        json.addProperty("quote", quotes.get(id).toString());
        json.addProperty("id", id);
        return Response.ok(gson.toJson(json)).build();
        
    }
    
    //Method 3 - Returns all quotes as: {"quote" : "Quote text")
    @GET
    @Produces("application/json")
    public Response getQuotes() throws QuoteNotFoundException{
        if(quotes.isEmpty()){
            throw new QuoteNotFoundException("No Quotes Created yet");
        }
        return Response.ok(gson.toJson(quotes.values())).build();
    }
    
    //Method 4 - creates new quote
    @POST
    @Consumes("application/json")
    @Produces("application/json")
    public Response createQuote(String quote){
        Quote newQuote = gson.fromJson(quote, Quote.class);
        
        for (int i = 1; i <= quotes.size() + 1; i++) {
            if (!quotes.containsKey(i)) {
                newQuote.setId(i);
                quotes.put(i, newQuote);
                break;
            }
        }
        JsonObject json = new JsonObject();
        json.addProperty("id", newQuote.getId());
        json.addProperty("quote", newQuote.getQuote());
        return Response.ok(gson.toJson(json)).build();
    }
    
    //Method 5 - update a existing quote
    @PUT
    @Path("{id}")
    @Consumes("application/json")
    @Produces("application/json")
    public Response updateQuote(@PathParam("id") Integer id, String quote) throws QuoteNotFoundException{
        Quote updatedQuote = gson.fromJson(quote, Quote.class);
        if(!quotes.containsKey(id)){
            throw new QuoteNotFoundException("Quote with requested id not found");
        } else {
            updatedQuote.setId(id);
            quotes.replace(id, updatedQuote);
        }
        JsonObject json = new JsonObject();
        json.addProperty("id", updatedQuote.getId());
        json.addProperty("quote", updatedQuote.getQuote());
        return Response.ok(gson.toJson(json)).build();
        
    }
    
    // Method 6 - Deletes the quote with the given ID
    @DELETE
    @Path("{id}")
    public Response deleteQuote(@PathParam("id") int id) throws QuoteNotFoundException{
        if(!quotes.containsKey(id)){
            throw new QuoteNotFoundException("Quote with requested id not found");
        }
        Quote quote = quotes.get(id);
        quotes.remove(id);
        JsonObject jo = new JsonObject();
        jo.addProperty("id", id);
        jo.addProperty("quote", quote.toString());
        return Response.ok(gson.toJson(jo)).build();
    }

    
}
