package com.udacity.sandwichclub.model;

import java.util.List;

/**
 * POJO Data object representing Sandwich club details view.
 */
public class Sandwich {

	private List<String> alsoKnownAs = null;
	private String description;
	private String image;
	private List<String> ingredients = null;
	private String mainName;
	private String placeOfOrigin;

	/**
	 * No args constructor for use in serialization
	 */
	public Sandwich() {
	}

	public Sandwich(String mainName, List<String> alsoKnownAs, String placeOfOrigin, String description, String image, List<String> ingredients) {
		this.mainName = mainName;
		this.alsoKnownAs = alsoKnownAs;
		this.placeOfOrigin = placeOfOrigin;
		this.description = description;
		this.image = image;
		this.ingredients = ingredients;
	}

	public List<String> getAlsoKnownAs() {
		return alsoKnownAs;
	}

	public String getDescription() {
		return description;
	}

	public String getImage() {
		return image;
	}

	public List<String> getIngredients() {
		return ingredients;
	}

	public String getMainName() {
		return mainName;
	}

	public String getPlaceOfOrigin() {
		return placeOfOrigin;
	}

	public void setAlsoKnownAs(List<String> alsoKnownAs) {
		this.alsoKnownAs = alsoKnownAs;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public void setImage(String image) {
		this.image = image;
	}

	public void setIngredients(List<String> ingredients) {
		this.ingredients = ingredients;
	}

	public void setMainName(String mainName) {
		this.mainName = mainName;
	}

	public void setPlaceOfOrigin(String placeOfOrigin) {
		this.placeOfOrigin = placeOfOrigin;
	}
}
