package com.example.task_2;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    private Button startButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);  // Assuming page1.xml is named activity_main.xml

        // Initialize the button
        startButton = findViewById(R.id.start_btn);

        // Set an OnClickListener to the button
        startButton.setOnClickListener(v -> {
            // Create an Intent to start the Page2Activity
            Intent intent = new Intent(MainActivity.this, page2.class);
            startActivity(intent);  // Start the new activity
        });
    }
}