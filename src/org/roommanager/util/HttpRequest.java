package org.roommanager.util;

import java.io.IOException;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpDelete;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.util.EntityUtils;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

public class HttpRequest {

	public static void deleteResourceByName(String name){
		getResourceByName(name);
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
	
	
	private static void getResourceByName(String name) {

		String url = PropertyReader.getRoomManagerUrl() + "resources";
		
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
                        	deleteResourceById(obj.get("_id").toString());
                        }
                    }

                }else if (resultObject instanceof JSONObject) {
                    JSONObject obj =(JSONObject)resultObject;
                    if(obj.get("name").toString().equals(name)){
                    	deleteResourceById(obj.get("_id").toString());
                    }
                }

            } catch (Exception e) {
            }

        } catch (IOException ex) {
        }
    }
}
