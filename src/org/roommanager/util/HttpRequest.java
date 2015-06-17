package org.roommanager.util;

import java.io.IOException;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpDelete;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.util.EntityUtils;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

public class HttpRequest {

	public static void deleteResourceByName(String name){
		String id = getResourceByName(name);
		String url = PropertyReader.getRoomManagerUrl() + "resources/" + id;
		deleteHttpMethod(url);
	}

	public static void createResource(String name, String displayName, String icon, String description){
		String url = PropertyReader.getRoomManagerUrl() + "resources";
		String resourceBody = "{ \"name\": \"[name]\", \"customName\": \"[displayName]\","
				+ " \"fontIcon\": \"[fontIcon]\", \"from\": \"\", \"description\": \"[description]\"}";
		
		resourceBody = resourceBody.replace("[name]", name)
			.replace("[displayName]", displayName)
			.replace("[fontIcon]", icon)
			.replace("[description]", description);
		postHttpMethod(url, resourceBody);
	}	
	
	private static String getResourceByName(String name) {
		String id = null;
		String url = PropertyReader.getRoomManagerUrl() + "resources";
		String propertyName = "name";
		String propertyId = "_id";
		
		String json = getHttpMethod(url);
		Object resourcesAsJson = parseStringToJSONObject(json);
		
		if (resourcesAsJson instanceof JSONArray) {
            JSONArray array=(JSONArray)resourcesAsJson;
            for (Object object : array) {
                JSONObject obj =(JSONObject)object;
                if(obj.get(propertyName).toString().equals(name)){
                	return obj.get(propertyId).toString();
                }
            }
        }else if (resourcesAsJson instanceof JSONObject) {
            JSONObject obj =(JSONObject)resourcesAsJson;
            if(obj.get(propertyName).toString().equals(name)){
            	return obj.get(propertyId).toString();
            }
        }
		return id;
    }
	
	private static Object parseStringToJSONObject(String json){
		Object jsonObject = null;
		try {
            JSONParser parser = new JSONParser();
            jsonObject = parser.parse(json);
        } catch (Exception e) {
        }
		return jsonObject;
	}
	
	private static String getHttpMethod(String url){
		String json = null;
		String codification = "UTF-8";
		String contentTypeHeader = "content-type";
		String contentType = "application/json";
		
		try (CloseableHttpClient httpClient = HttpClientBuilder.create().build()) {
			HttpGet request = new HttpGet(url);
            request.addHeader(contentTypeHeader, contentType);
            HttpResponse result = httpClient.execute(request);
            json = EntityUtils.toString(result.getEntity(), codification);
        } catch (IOException ex) {
        }
		return json;
	}
	
	private static void deleteHttpMethod(String url){
		String contentTypeHeader = "content-type";
		String contentType = "application/json";
		
		try (CloseableHttpClient httpClient = HttpClientBuilder.create().build()) {
			HttpDelete request = new HttpDelete(url);
			request.addHeader(contentTypeHeader, contentType);
            httpClient.execute(request);
        } catch (IOException ex) {
        }
	}
	
	private static void postHttpMethod(String url, String body){
		String contentTypeHeader = "content-type";
		String contentType = "application/json";
		
		try (CloseableHttpClient httpClient = HttpClientBuilder.create().build()) {
			HttpPost request = new HttpPost(url);
            StringEntity params = new StringEntity(body);
            request.addHeader(contentTypeHeader, contentType);
            request.setEntity(params);
            httpClient.execute(request);
        } catch (IOException ex) {
        }
	}
	
	
}
