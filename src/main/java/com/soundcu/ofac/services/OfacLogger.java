package com.soundcu.ofac.services;

import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.Enumeration;
import java.util.Iterator;
import java.util.List;
import java.util.Map.Entry;

import javax.servlet.http.HttpServletRequest;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

@Service
public class OfacLogger
{
	private static final ObjectMapper objectMapper = new ObjectMapper();
	private static final String ENTITY_NAME = "OFAC";
	
	@Value("${logger.name}")
	private String loggerName;
	
	private Logger logger;
	
	private JSONArray extractRequestHeaders(HttpServletRequest request)
	{
		JSONArray headers = new JSONArray();
		Enumeration<String> headerNames = request.getHeaderNames();
		while(headerNames.hasMoreElements())
		{
			String name = headerNames.nextElement();
			headers.put(name + ": " + request.getHeader(name));
		}
		
		return headers;
	}
	
	private String extractResponseBody(ResponseEntity<Object> response)
	{
		if (response.hasBody())
        {
            try
			{
				return objectMapper.writeValueAsString(response.getBody());
			}
			catch (JSONException | JsonProcessingException e)
			{
				
			}
        }
		
		return "";
	}

	private JSONArray extractResponseHeaders(ResponseEntity<Object> response)
	{
		JSONArray responseHeaders = new JSONArray();
        HttpHeaders headers = response.getHeaders();
        Iterator<Entry<String, List<String>>> it = headers.entrySet().iterator();
        while (it.hasNext())
        {
            Entry<String, List<String>> entry = it.next();
            StringBuilder builder = new StringBuilder();
            for (String s : entry.getValue())
                builder.append(s);
            
            responseHeaders.put(entry.getKey() + ": " + builder.toString());
        }
        
        return responseHeaders;
	}

	private String getTimestamp()
    {
		String timestamp = ZonedDateTime.now(ZoneId.of("America/Los_Angeles"))
			.truncatedTo(ChronoUnit.SECONDS)
			.format(DateTimeFormatter.ISO_DATE_TIME);
		return timestamp.substring(0, timestamp.lastIndexOf("-"));
    }
	
	private void initLogger()
	{
		logger = LoggerFactory.getLogger(loggerName);
	}

	public void logRequest(HttpServletRequest request)
    {
		if (logger == null)
			initLogger();
		
        JSONObject message = new JSONObject();
        message.put("entityName", ENTITY_NAME);
        message.put("messageBody", "");
        message.put("messageId", "");
        message.put("messageType", "REQUEST");
        message.put("priority", "VERBOSE");
        message.put("requestHeaders", extractRequestHeaders(request).toString());
        message.put("requestPath", request.getRequestURI());
        message.put("requestQueryParams", "");
        message.put("timestamp", getTimestamp());
        logger.info(message.toString());
    }
	
	public void logResponse(ResponseEntity<Object> response, String path)
    {
		if (logger == null)
			initLogger();
		
        JSONObject message = new JSONObject();
        message.put("entityName", ENTITY_NAME);
        message.put("messageBody", extractResponseBody(response));
        message.put("messageId", "");
        message.put("messageType", "RESPONSE");
        message.put("priority", "VERBOSE");
        message.put("requestHeaders", extractResponseHeaders(response).toString());
        message.put("requestPath", path);
        message.put("requestQueryParams", "");
        message.put("timestamp", getTimestamp());
        logger.info(message.toString());
    }
}
