package com.example.task_2;

import android.os.Bundle;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.RatingBar;
import android.widget.SeekBar;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;

public class page2 extends AppCompatActivity {

    // Declare UI components
    private CheckBox pizza, burger, pasta;
    private RadioGroup spiceLevelGroup;
    private RadioButton selectedSpiceLevel;
    private RatingBar foodRatingBar;
    private TextView ratingText, customizationText;
    private SeekBar customizationSeekBar;
    private Switch extraCheeseSwitch;
    private Button placeOrderButton;

    // ArrayList to store selected food categories
    private ArrayList<String> selectedFoodCategories = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_page2);

        // Initialize UI components
        pizza = findViewById(R.id.pizza);
        burger = findViewById(R.id.burger);
        pasta = findViewById(R.id.pasta);
        spiceLevelGroup = findViewById(R.id.spice_level_group);
        foodRatingBar = findViewById(R.id.food_rating_bar);
        ratingText = findViewById(R.id.rating_text);
        customizationSeekBar = findViewById(R.id.customization_seekbar);
        customizationText = findViewById(R.id.seekbar_value);
        extraCheeseSwitch = findViewById(R.id.extra_cheese_switch);
        placeOrderButton = findViewById(R.id.place_order_button);

        // CheckBox listeners for food selection
        pizza.setOnCheckedChangeListener(this::onFoodCategoryChecked);
        burger.setOnCheckedChangeListener(this::onFoodCategoryChecked);
        pasta.setOnCheckedChangeListener(this::onFoodCategoryChecked);

        // RadioGroup listener for spice level selection
        spiceLevelGroup.setOnCheckedChangeListener((group, checkedId) -> {
            selectedSpiceLevel = findViewById(checkedId);
        });

        // RatingBar listener for real-time rating feedback
        foodRatingBar.setOnRatingBarChangeListener((ratingBar, rating, fromUser) -> {
            ratingText.setText("Rating: " + rating);
        });

        // SeekBar listener for customization level
        customizationSeekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                customizationText.setText("Customization: " + progress + "%");
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {
                // Optional: Do something when the user starts interacting with the SeekBar
            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
                // Optional: Do something when the user stops interacting with the SeekBar
            }
        });

        // Switch listener for extra cheese
        extraCheeseSwitch.setOnCheckedChangeListener((buttonView, isChecked) -> {
            if (isChecked) {
                Toast.makeText(getApplicationContext(), "Extra cheese added", Toast.LENGTH_SHORT).show();
            } else {
                Toast.makeText(getApplicationContext(), "Extra cheese removed", Toast.LENGTH_SHORT).show();
            }
        });

        // Place order button click listener
        placeOrderButton.setOnClickListener(v -> {
            if (selectedFoodCategories.isEmpty()) {
                Toast.makeText(getApplicationContext(), "Please select at least one food category!", Toast.LENGTH_SHORT).show();
                return;
            }

            int selectedSpiceId = spiceLevelGroup.getCheckedRadioButtonId();
            if (selectedSpiceId == -1) {
                Toast.makeText(getApplicationContext(), "Please select a spiciness level!", Toast.LENGTH_SHORT).show();
                return;
            }

            String spiceLevel = selectedSpiceLevel.getText().toString();
            String extraCheese = extraCheeseSwitch.isChecked() ? "Yes" : "No";
            float rating = foodRatingBar.getRating();
            int customization = customizationSeekBar.getProgress();

            // Create order summary
            StringBuilder orderSummary = new StringBuilder();
            orderSummary.append("Order Summary:\n");
            orderSummary.append("Food Categories: ").append(selectedFoodCategories).append("\n");
            orderSummary.append("Spice Level: ").append(spiceLevel).append("\n");
            orderSummary.append("Customization: ").append(customization).append("%\n");
            orderSummary.append("Extra Cheese: ").append(extraCheese).append("\n");
            orderSummary.append("Rating: ").append(rating).append("\n");

            // Show order summary in a Toast
            Toast.makeText(getApplicationContext(), orderSummary.toString(), Toast.LENGTH_LONG).show();
        });
    }

    // Method to handle CheckBox selections
    private void onFoodCategoryChecked(CompoundButton buttonView, boolean isChecked) {
        if (isChecked) {
            selectedFoodCategories.add(buttonView.getText().toString());
        } else {
            selectedFoodCategories.remove(buttonView.getText().toString());
        }
    }
}
