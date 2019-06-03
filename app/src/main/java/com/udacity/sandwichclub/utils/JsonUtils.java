package com.udacity.sandwichclub.utils;

import java.util.ArrayList;
import java.util.List;

import com.udacity.sandwichclub.model.Sandwich;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class JsonUtils {

	public static Sandwich parseSandwichJson(String json) {
		try {
			final JSONObject obj = new JSONObject(json);

			final JSONObject sandWichObject = obj.getJSONObject("name");
			final String name = sandWichObject.getString("mainName");
			final String placeOfOrig = obj.getString("placeOfOrigin");
			final String description = obj.getString("description");
			final String imagePath = obj.getString("image");
			final List<String> aliasList = extractDetailsList(sandWichObject, "alsoKnownAs");
			final List<String> ingredientsList = extractDetailsList(obj, "ingredients");

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
