package com.example.messmanagement;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;

public class Notification extends AppCompatActivity {
    private TextView textView;
    String message;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notification);

        message = getIntent().getStringExtra("Message");

        textView = findViewById(R.id.notification);
        textView.setText(message);
    }
}
