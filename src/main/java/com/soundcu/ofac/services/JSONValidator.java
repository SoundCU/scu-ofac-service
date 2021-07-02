package com.soundcu.ofac.services;

import java.io.IOException;
import java.io.InputStream;

import org.everit.json.schema.Schema;
import org.everit.json.schema.ValidationException;
import org.everit.json.schema.loader.SchemaLoader;
import org.json.JSONException;
import org.json.JSONObject;
import org.json.JSONTokener;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.soundcu.ofac.exceptions.BadRequestException;
import com.soundcu.ofac.exceptions.ServiceException;

@Service
public class JSONValidator
{
	public boolean isValid(String payload)
	{
		try (InputStream inputStream = JSONValidator.class.getResourceAsStream("/schemas/request.json"))
		{
			JSONObject entity = new JSONObject(payload);
			JSONObject rawSchema = new JSONObject(new JSONTokener(inputStream));
			Schema schema = SchemaLoader.load(rawSchema);
			schema.validate(entity);
			return true;

		}
		catch (ValidationException e)
		{
			throw new BadRequestException(144, e.getMessage());
		}
		catch (JSONException e)
		{
			throw new BadRequestException(141, e.getMessage());
		}
		catch (IOException e)
		{
			throw new ServiceException(500, HttpStatus.INTERNAL_SERVER_ERROR, "Failed to validate request payload.");
		}
	}
}
