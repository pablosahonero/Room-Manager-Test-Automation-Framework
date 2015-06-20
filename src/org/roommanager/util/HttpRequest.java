package org.roommanager.util;

import java.io.IOException;

import org.apache.http.HttpResponse;
import org.apache.http.client.methods.CloseableHttpResponse;
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
	private static final String CODIFICATION_UTF_8 = "UTF-8";
	private static final String CONTENT_TYPE_HEADER = "content-type";
	private static final String APPLICATION_CONTENT_TYPE = "application/json";
	private static final String AUTHORIZATION_HEADER = "Authorization";
	private static final String RESOURCE_BODY = "{ \"name\": \"[name]\", \"customName\": \"[displayName]\",\"fontIcon\": \"[fontIcon]\", \"from\": \"\", \"description\": \"[description]\"}";
	private static final String  MEETING_BODY = "{\"organizer\": \"[organizer]\",\"title\": \"[subject]\", \"start\": \"[startTime]\",\"end\": \"[endTime]\","
			+ " \"location\": \"[roomName]\",\"roomEmail\": \"[roomEmail]\", \"resources\": [\"[roomEmail]\"], \"attendees\": [[attendees]]}";
	
	public static void createMeeting(String organizer, String subject, String startTime, 
		String endTime, String roomName, String attendees){
		String meeting= MEETING_BODY;
		String url = PropertyReader.getRoomManagerUrl() + "services/[serviceId]/rooms/[roomId]/meetings";
		String serviceId = getEmailServiceId();
		String roomId = getRoomIdByName(roomName);
	
		url = url.replace("[serviceId]", serviceId).replace("[roomId]", roomId);
		
		String roomEmail = getRoomEmailByName(roomName);
		meeting= meeting.replace("[organizer]", organizer)
			.replace("[subject]", subject)
			.replace("[startTime]", startTime)
			.replace("[endTime]", endTime)
			.replace("[roomName]", roomName)
			.replace("[roomEmail]", roomEmail)
			.replace("[attendees]", attendees);
		postHttpMethod(url, meeting);
	}
	
	public static void deleteMeetingBySubjectName(String roomName, String meetingSubject){
		String url = PropertyReader.getRoomManagerUrl() + "services/[serviceId]/rooms/[roomId]/meetings";
		String serviceId = getEmailServiceId();
		String roomId = getRoomIdByName(roomName);
		
		url = url.replace("[serviceId]", serviceId).replace("[roomId]", roomId);
		String propertyName = "title";
		
		String meetingId = getObejctPropertyByGivenPropertyValue("_id", propertyName, meetingSubject, url);
		url = url + "/" + meetingId;
		deleteHttpMethod(url);
	}
	
	public static String getEmailServiceId(){
		String url = PropertyReader.getRoomManagerUrl() + "services";
		String propertyId = "_id";
		
		String serviceResponse = getHttpMethod(url);
		JSONArray services = (JSONArray)parseStringToJSONObject(serviceResponse);
		JSONObject emailService =(JSONObject)services.get(0);
		return emailService.get(propertyId).toString();
	}
	
	public static String getRoomEmailByName(String roomName){
		String url = PropertyReader.getRoomManagerUrl() + "rooms";
		String propertyName = "displayName";
		
		String roomId = getObejctPropertyByGivenPropertyValue("emailAddress", propertyName, roomName, url);
		return roomId;
	}
	
	public static String getRoomIdByName(String roomName){
		String url = PropertyReader.getRoomManagerUrl() + "rooms";
		String propertyName = "displayName";
		
		String roomId = getObejctPropertyByGivenPropertyValue("_id", propertyName, roomName, url);
		return roomId;
	}
	
	public static void deleteResourceByName(String name){
		String id = getResourceByName(name);
		String url = PropertyReader.getRoomManagerUrl() + "resources/" + id;
		deleteHttpMethod(url);
	}

	public static void createResource(String name, String displayName, String icon, String description){
		String url = PropertyReader.getRoomManagerUrl() + "resources";
		String resourceBody = RESOURCE_BODY;
		
		resourceBody = resourceBody.replace("[name]", name)
			.replace("[displayName]", displayName)
			.replace("[fontIcon]", icon)
			.replace("[description]", description);
		postHttpMethod(url, resourceBody);
	}	
	
	private static String getResourceByName(String resourceName) {
		String url = PropertyReader.getRoomManagerUrl() + "resources";
		String propertyName = "name";
		
		String resourceId = getObejctPropertyByGivenPropertyValue("_id", propertyName, resourceName, url);
		return resourceId;
    }
	
	private static Object parseStringToJSONObject(String json){
		Object jsonObject = null;
		try {
            JSONParser parser = new JSONParser();
            jsonObject = parser.parse(json);
        } catch (Exception e) {
        	TestLogger.error(e.getMessage());
        }
		return jsonObject;
	}
	
	private static String getHttpMethod(String url){
		String json = null;
		String codification = CODIFICATION_UTF_8;
		String contentTypeHeader = CONTENT_TYPE_HEADER;
		String contentType = APPLICATION_CONTENT_TYPE;
		
		try (CloseableHttpClient httpClient = HttpClientBuilder.create().build()) {
			HttpGet request = new HttpGet(url);
            request.addHeader(contentTypeHeader, contentType);
            HttpResponse result = httpClient.execute(request);
            json = EntityUtils.toString(result.getEntity(), codification);
        } catch (IOException ex) {
        	TestLogger.error(ex.getMessage());
        }
		return json;
	}
	
	private static void deleteHttpMethod(String url){
		String contentTypeHeader = CONTENT_TYPE_HEADER;
		String contentType = APPLICATION_CONTENT_TYPE;
		String authorizationHeader = AUTHORIZATION_HEADER;
		String authorizationValue = PropertyReader.getBasicAuthentication();
		
		try (CloseableHttpClient httpClient = HttpClientBuilder.create().build()){
			HttpDelete request = new HttpDelete(url);
			request.addHeader(contentTypeHeader, contentType);
			request.addHeader(authorizationHeader, authorizationValue);
            
            CloseableHttpResponse response = httpClient.execute(request);
            try {
            	TestLogger.info("The Status of the DELETE Request to Room Manager API is: " + response.getStatusLine());
                EntityUtils.consume(response.getEntity());
            } finally {
                response.close();
            }
        } catch (IOException ex) {
        	TestLogger.error(ex.getMessage());
        }
	}
	
	private static void postHttpMethod(String url, String body){
		String contentTypeHeader = CONTENT_TYPE_HEADER;
		String contentType = APPLICATION_CONTENT_TYPE;
		String authorizationHeader = AUTHORIZATION_HEADER;
		String authorizationValue = PropertyReader.getBasicAuthentication();
		
		try (CloseableHttpClient httpClient = HttpClientBuilder.create().build()) {
			HttpPost request = new HttpPost(url);
            StringEntity params = new StringEntity(body);
            request.addHeader(contentTypeHeader, contentType);
            request.addHeader(authorizationHeader, authorizationValue);
            request.setEntity(params);
            CloseableHttpResponse response = httpClient.execute(request);
            try {
            	TestLogger.info("The Status of the POST Request to Room Manager API is: " + response.getStatusLine());
                EntityUtils.consume(response.getEntity());
            } finally {
                response.close();
            }
        } catch (IOException ex) {
        	TestLogger.error(ex.getMessage());
        }
	}
	
	private static String getObejctPropertyByGivenPropertyValue(String propertyToBeSearched, String property, String propertyValue, String url) {
		String responseProperty = null;
		String propertyName = property;
		
		String json = getHttpMethod(url);
		Object resourcesAsJson = parseStringToJSONObject(json);
		
		if (resourcesAsJson instanceof JSONArray) {
            JSONArray array=(JSONArray)resourcesAsJson;
            for (Object object : array) {
                JSONObject obj =(JSONObject)object;
                if(obj.get(propertyName).toString().equals(propertyValue)){
                	return obj.get(propertyToBeSearched).toString();
                }
            }
        }else if (resourcesAsJson instanceof JSONObject) {
            JSONObject obj =(JSONObject)resourcesAsJson;
            if(obj.get(propertyName).toString().equals(propertyValue)){
            	return obj.get(propertyToBeSearched).toString();
            }
        }
		return responseProperty;
    }
	
}
