package com.udacity.sandwichclub;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.Html;
import android.text.TextUtils;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.squareup.picasso.Picasso;
import com.udacity.sandwichclub.model.Sandwich;
import com.udacity.sandwichclub.utils.JsonUtils;

/**
 * Manages Details of Sandwich club.
 */
public class DetailActivity extends AppCompatActivity {

	public static final String DATA_NOT_AVAILABLE = "Data not available";
	public static final String EXTRA_POSITION = "extra_position";
	private static final int DEFAULT_POSITION = -1;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_detail_constraint_layout);

		ImageView ingredientsIv = findViewById(R.id.image_iv);

		Intent intent = getIntent();
		if (intent == null) {
			closeOnError();
		}

		int position = intent.getIntExtra(EXTRA_POSITION, DEFAULT_POSITION);
		if (position == DEFAULT_POSITION) {
			// EXTRA_POSITION not found in intent
			closeOnError();
			return;
		}

		String[] sandwiches = getResources().getStringArray(R.array.sandwich_details);
		String json = sandwiches[position];
		Sandwich sandwich = JsonUtils.parseSandwichJson(json);
		if (sandwich == null) {
			// Sandwich data unavailable
			closeOnError();
			return;
		}

		populateUI(sandwich);
		Picasso.with(this)
				.load(sandwich.getImage())
				.error(R.drawable.error_downloading)
				.into(ingredientsIv);

		setTitle(sandwich.getMainName());
	}

	private void closeOnError() {
		finish();
		Toast.makeText(this, R.string.detail_error_message, Toast.LENGTH_SHORT).show();
	}

	private void populateUI(Sandwich sandwich) {

		TextView originTextView = findViewById(R.id.origin_tv);
		originTextView.setText(sandwich.getPlaceOfOrigin().isEmpty() ? DATA_NOT_AVAILABLE : sandwich.getPlaceOfOrigin());

		TextView alsoKnownAsView = findViewById(R.id.also_known_tv);
		String aliasName = sandwich.getAlsoKnownAs().isEmpty() ? DATA_NOT_AVAILABLE : TextUtils.join(",", sandwich.getAlsoKnownAs());
		alsoKnownAsView.setText(aliasName);

		TextView ingredientsView = findViewById(R.id.ingredients_tv);
		String ingredients = sandwich.getIngredients().isEmpty() ? DATA_NOT_AVAILABLE : sandwich.getIngredients().toString();
		ingredientsView.setText(ingredients);

		TextView description = findViewById(R.id.description_tv);
		description.setText(Html.fromHtml(sandwich.getDescription()));

	}
}
