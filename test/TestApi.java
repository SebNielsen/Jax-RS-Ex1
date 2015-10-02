/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import org.junit.BeforeClass;
import org.junit.Test;

import static com.jayway.restassured.RestAssured.*;
import com.jayway.restassured.parsing.Parser;
import com.jayway.restassured.path.json.JsonPath;
import com.jayway.restassured.response.Response;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.junit.Assert.assertEquals;

/**
 *
 * @author sebastiannielsen
 */
public class TestApi {

    public TestApi() {
    }

    @BeforeClass
    public static void setUpBeforeClass() throws Exception {
        baseURI = "http://localhost:8080";
        defaultParser = Parser.JSON;
        basePath = "/Jax-RS-Ex1/api/quote";
    }

    @Test
    public void testGetQuoteWithID() {
        when()
                .get("/1")
                .then().
                statusCode(200).assertThat().
                body("quote", equalTo("Friends are kisses blown to us by angels"));
    }

    @Test
    public void testGetRandomQuote() {
        when()
                .get("/random")
                .then().
                statusCode(200);
    }

    @Test
    public void testGetQuotes() {
        when()
                .get("")
                .then().
                statusCode(200).assertThat().
                body("[0].quote", equalTo("Friends are kisses blown to us by angels")).
                body("[1].quote", equalTo("Do not take life too seriously. You will never get out of it alive")).
                body("[2].quote", equalTo("Behind every great man, is a woman rolling her eyes"));
                
    }

    @Test
    public void testCreateQuote() {
        String myJson = "{\"quote\":\"Test quote\"}";

        given().
                contentType("application/json").
                body(myJson).
        when().
                post("").
        then().statusCode(200).assertThat().body("quote", equalTo("Test quote"));
    }
    
    @Test
    public void testUpdateQuote() {
       String myJson = "{'quote': 'Test'}";
       given().
                contentType("application/json").
                body(myJson).
               
        when().
                put("/2").
        then().assertThat().body("quote", equalTo("Test"));
       
    }

}
