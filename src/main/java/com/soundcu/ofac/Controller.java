package com.soundcu.ofac;

import static org.springframework.http.MediaType.APPLICATION_FORM_URLENCODED_VALUE;
import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

import java.net.URLDecoder;
import java.nio.charset.StandardCharsets;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.soundcu.ofac.model.Entry;
import com.soundcu.ofac.model.Requestor;
import com.soundcu.ofac.model.Response;
import com.soundcu.ofac.services.EntryBuilder;
import com.soundcu.ofac.services.EntryService;
import com.soundcu.ofac.services.JSONValidator;
import com.soundcu.ofac.services.OfacLogger;

@RestController
public class Controller 
{		
	private static Integer threshold = 90;
	
	@Autowired
	private EntryBuilder entryBuilder;
	
	@Autowired
	private EntryService entryService;
	
	@Autowired
	private JSONValidator validator;
	
	@Autowired
	private OfacLogger logger;
	
	@PostMapping(value = "/verify/ofac", 
			consumes = {APPLICATION_FORM_URLENCODED_VALUE, APPLICATION_JSON_VALUE}, 
			produces = {APPLICATION_JSON_VALUE})
	ResponseEntity<Object> ofac(@RequestHeader("Content-Type") String contentType,
			@RequestHeader(required = false, value = "minimum_matching_score") Integer minimumScore,
			@RequestHeader String user_id, 
			@RequestHeader String user_id_type, 
			@RequestBody String entity,
			HttpServletRequest request)
	{
		logger.logRequest(request);
		if (contentType.equalsIgnoreCase("application/x-www-form-urlencoded"))
			entity = parseForm(entity);
		
		validator.isValid(entity);
		if (minimumScore == null)
			minimumScore = threshold;
		
		JSONObject entityObj = new JSONObject(entity);
		List<Entry> entries = entryService.search(minimumScore, entityObj);
		Response response = buildResponse(entries, entityObj, user_id, user_id_type);
		ResponseEntity<Object> responseEntity = new ResponseEntity<Object>(response, HttpStatus.OK);
		logger.logResponse(responseEntity, request.getRequestURI());
		return responseEntity;
	}
	
	@PostMapping(value = "/verify/ofac/{type}", 
			consumes = {APPLICATION_FORM_URLENCODED_VALUE, APPLICATION_JSON_VALUE}, 
			produces = {APPLICATION_JSON_VALUE})
	ResponseEntity<Object> ofacType(@RequestHeader("Content-Type") String contentType,
			@RequestHeader(required = false, value = "minimum_matching_score") Integer minimumScore,
			@RequestHeader String user_id, 
			@RequestHeader String user_id_type, 
			@PathVariable("type") String type,
			@RequestBody String entity,
			HttpServletRequest request)
	{
		logger.logRequest(request);
		if (contentType.equalsIgnoreCase("application/x-www-form-urlencoded"))
			entity = parseForm(entity);
		
		validator.isValid(entity);
		if (minimumScore == null)
			minimumScore = threshold;
		
		JSONObject entityObj = new JSONObject(entity);
		List<Entry> entries = entryService.filterByType(minimumScore, entityObj, type);
		Response response = buildResponse(entries, entityObj, user_id, user_id_type);
		ResponseEntity<Object> responseEntity = new ResponseEntity<Object>(response, HttpStatus.OK);
		logger.logResponse(responseEntity, request.getRequestURI());
		return responseEntity;
	}
	
	private Response buildResponse(List<Entry> entries, JSONObject entity, String userId, String userType)
	{
		Response response = new Response(entries, entity.getString("name"));
		Requestor requestor = new Requestor();
		requestor.setUser_id(userId);
		requestor.setUser_id_type(userType);
		response.setSeach_performed_by(requestor);
		response.setSources_used(entryBuilder.getSourceData());
		return response;
	}
	
	private String parseForm(String body)
	{
		JSONObject entity = new JSONObject();
		String[] pairs = body.split("&");
		for (String pair : pairs)
		{
			String[] tokens = pair.split("=");
			String value = tokens.length > 0 ? URLDecoder.decode(tokens[1], StandardCharsets.UTF_8) : null;
			entity.put(tokens[0], value);
		}
		
		return entity.toString();
	}
}
