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
		deleteResourceById(id);
	}

	public static void createResource(String name, String displayName, String icon, String description){
		String resourceBody = "{"
			+ " \"name\": \""+ name +"\","
			+ " \"customName\": \""+ displayName +"\","
			+ "\"fontIcon\": \""+ icon +"\","
			+ " \"from\": \"\","
			+ "\"description\": \""+ description +"\""
			+ "}";
		createResource(resourceBody);
	}
	
	private static void createResource(String resource) {

		String url = PropertyReader.getRoomManagerUrl() + "resources";
		
		try (CloseableHttpClient httpClient = HttpClientBuilder.create().build()) {
            HttpPost request = new HttpPost(url);
            StringEntity params = new StringEntity(resource);
            request.addHeader("content-type", "application/json");
            request.setEntity(params);
            HttpResponse result = httpClient.execute(request);
        } catch (IOException ex) {
        }
    }
	
	private static void deleteResourceById(String id) {

		String url = PropertyReader.getRoomManagerUrl() + "resources/" + id;
		
		try (CloseableHttpClient httpClient = HttpClientBuilder.create().build()) {
            HttpDelete request = new HttpDelete(url);
            request.addHeader("content-type", "application/json");
            HttpResponse result = httpClient.execute(request);
        } catch (IOException ex) {
        }
    }
	
	
	private static String getResourceByName(String name) {

		String url = PropertyReader.getRoomManagerUrl() + "resources";
		String id = null;
				
		try (CloseableHttpClient httpClient = HttpClientBuilder.create().build()) {
            HttpGet request = new HttpGet(url);
            request.addHeader("content-type", "application/json");
            HttpResponse result = httpClient.execute(request);
            
            String json = EntityUtils.toString(result.getEntity(), "UTF-8");
            try {
                JSONParser parser = new JSONParser();
                Object resultObject = parser.parse(json);

                if (resultObject instanceof JSONArray) {
                    JSONArray array=(JSONArray)resultObject;
                    for (Object object : array) {
                        JSONObject obj =(JSONObject)object;
                        if(obj.get("name").toString().equals(name)){
                        	return obj.get("_id").toString();
                        }
                    }

                }else if (resultObject instanceof JSONObject) {
                    JSONObject obj =(JSONObject)resultObject;
                    if(obj.get("name").toString().equals(name)){
                    	return obj.get("_id").toString();
                    }
                }

            } catch (Exception e) {
            }

        } catch (IOException ex) {
        }
		return id;
    }
}
