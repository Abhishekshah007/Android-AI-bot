package com.example.chatgpt_android_app;

import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;

public class MainActivity extends AppCompatActivity {

    private AppCompatButton mLoginButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setTitle("Authentication");

        mLoginButton = findViewById(R.id.login_button);

        mLoginButton.setOnClickListener(view -> {
            Intent intent = new Intent(MainActivity.this, ChatgptActivity.class);
            startActivity(intent);
            finish();
        });
    }
}
