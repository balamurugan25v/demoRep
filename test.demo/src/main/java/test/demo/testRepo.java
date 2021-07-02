package test.demo;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.junit.Assert;
import org.junit.Ignore;
import org.junit.Test;

import groovy.console.ui.SystemOutputInterceptor;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

import static io.restassured.RestAssured.*;



public class testRepo {

	@Ignore
	public void testCode() {


	
	}
	
	@Test
	public void postCode() {
		
		RequestSpecification newConfig = given();
		
		newConfig.header("Content-Type","application/json");
		
		JSONObject jsonValue = new JSONObject();
		jsonValue.put("name", "Bala");
		jsonValue.put("job", "Product Analysis");
		
		newConfig.body(jsonValue.toJSONString());
		Response postResponse =newConfig.post("https://reqres.in/api/users");
		
		 System.out.println(postResponse.getBody().jsonPath().prettify());
				 //body().asString());
		Assert.assertEquals(postResponse.getStatusCode(),201);
		
		JsonPath pathDir = postResponse.jsonPath();
		
		String id =pathDir.get("id");
		
		System.out.println("Created UserId     "+id);
		
		Response getResponse = get("https://reqres.in/api/users/2");
		
		int statusCode=getResponse.getStatusCode();
		
		//ResponseBody value=response.getBody();
		
		System.out.println("Response Code    "+statusCode);
		//System.out.println("Response Value    "+value);
		
		Assert.assertEquals(statusCode, 200);
		System.out.println(getResponse.body().asString());
		
		
		
	}
	
	@Test
	public void  queueStatus() {
		
		RequestSpecification getToken = given();
		
		getToken.header("Content-Type","application/json");
		
		JSONObject jsonToken= new JSONObject();
		jsonToken.put("userName", "gseautobahnui_system");
		jsonToken.put("password", "PNeKgTTvBJUP2zYau4gOkXs1dC8X7xxl");
		
		getToken.body(jsonToken.toJSONString());
		
		Response getTokenValue = getToken.post("https://piapi-internal.openclass.com/tokens");
		System.out.println(getTokenValue.jsonPath().prettify());
		
		JsonPath getTokenData = getTokenValue.jsonPath();
		String authToken =getTokenData.get("data");
		System.out.println("Final Token      : "+authToken);
		
		
		RequestSpecification getQueue = given();
		
		getQueue.header("X-Authorization",authToken); 	
		
		Response getQueueCount = getQueue.get("https://messaging-admin.prd-prsn.com/queuesize?queuename=prduse1-ab-GSE-eTextIssueValidation");
		JsonPath countPath =  getQueueCount.jsonPath();
		String queueCount = countPath.get("message-count");
				
		System.out.println("Queue count     :  "+queueCount);
		Assert.assertEquals(getQueueCount.getStatusCode(),200);
	}
	
	
	

}
