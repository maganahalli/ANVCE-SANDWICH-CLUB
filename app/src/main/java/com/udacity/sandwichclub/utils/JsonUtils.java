package com.udacity.sandwichclub.utils;

import java.util.ArrayList;
import java.util.List;

import com.udacity.sandwichclub.model.Sandwich;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/**
 * Helper Class to parse JSON response for Sandwich Club project.
 */
public class JsonUtils {

	private static final String ALSO_KNOWN_AS = "alsoKnownAs";
	private static final String DESCRIPTION = "description";
	private static final String FALL_BACK_TEXT = "Data not available";
	private static final String IMAGE = "image";
	private static final String INGREDIENTS = "ingredients";
	private static final String MAIN_NAME = "mainName";
	private static final String NAME = "name";
	private static final String PLACE_OF_ORIGIN = "placeOfOrigin";

	public static Sandwich parseSandwichJson(String json) {
		try {
			final JSONObject obj = new JSONObject(json);

			final JSONObject sandWichObject = obj.getJSONObject(NAME);
			final String name = sandWichObject.optString(MAIN_NAME, FALL_BACK_TEXT);
			final String placeOfOrig = obj.optString(PLACE_OF_ORIGIN, FALL_BACK_TEXT);
			final String description = obj.optString(DESCRIPTION, FALL_BACK_TEXT);
			final String imagePath = obj.optString(IMAGE);
			final List<String> aliasList = extractDetailsList(sandWichObject, ALSO_KNOWN_AS);
			final List<String> ingredientsList = extractDetailsList(obj, INGREDIENTS);


			return new Sandwich(name, aliasList, placeOfOrig, description, imagePath, ingredientsList);

		} catch (JSONException e) {
			return null;
		}
	}

	private static List<String> extractDetailsList(JSONObject sandWichObject, String key) throws JSONException {
		List<String> list = new ArrayList<>();
		final JSONArray keyDetailsArray = sandWichObject.getJSONArray(key);
		final int index = keyDetailsArray.length();
		for (int i = 0; i < index; ++i) {
			final String alias = (String) keyDetailsArray.get(i);
			list.add(alias);
		}
		return list;
	}
}
