package com.example.chatgpt_android_app;

import android.app.ProgressDialog;
import android.content.ClipData;
import android.content.ClipboardManager;
import android.graphics.Color;
import android.os.AsyncTask;
import android.os.Bundle;
import android.text.Spannable;
import android.text.SpannableString;
import android.text.style.ForegroundColorSpan;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;

import com.lilittlecat.chatgpt.offical.ChatGPT;

import java.util.Objects;

public class ChatgptActivity extends AppCompatActivity {

    private static final String API_KEY = "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";

    EditText question;
    TextView responseTextView;
    AppCompatButton question_post_button;

    ChatGPTTask chatGPTTask;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chatgpt);

        SpannableString s = new SpannableString("Ask.Me");
        s.setSpan(new ForegroundColorSpan(Color.RED), 0, s.length(), Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
        Objects.requireNonNull(getSupportActionBar()).setTitle(s);

        question = findViewById(R.id.question);
        responseTextView = findViewById(R.id.question_response);
        question_post_button = findViewById(R.id.question_post_btn);

        question_post_button.setOnClickListener(view -> {
            String input = question.getText().toString().trim();
            if (!input.isEmpty()) {
                chatGPTTask = new ChatGPTTask();
                chatGPTTask.execute(input);
            }
        });

        setCopyButtonClickListener();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (chatGPTTask != null) {
            chatGPTTask.cancel(true);
        }
    }

    private class ChatGPTTask extends AsyncTask<String, Void, String> {

        private ProgressDialog progressDialog;

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            progressDialog = new ProgressDialog(ChatgptActivity.this);
            progressDialog.setMessage("Please wait...");
            progressDialog.show();
        }

        @Override
        protected String doInBackground(String... params) {
            if (isCancelled()) {
                return null;
            }
            String input = params[0];
            ChatGPT chatGPT = new ChatGPT(API_KEY);
            return chatGPT.ask(input);
        }

        @Override
        protected void onPostExecute(String response) {
            super.onPostExecute(response);
            if (progressDialog != null && progressDialog.isShowing()) {
                progressDialog.dismiss();
            }
            responseTextView.setText(response);
        }
    }

    private void setCopyButtonClickListener() {
        TextView responseTextView = findViewById(R.id.question_response);
        TextView copyButton = findViewById(R.id.textView);
        copyButton.setOnClickListener(v -> {
            String response = responseTextView.getText().toString().trim();
            if (!response.isEmpty()) {
                ClipboardManager clipboardManager = (ClipboardManager) getSystemService(CLIPBOARD_SERVICE);
                ClipData clipData = ClipData.newPlainText("response", response);
                clipboardManager.setPrimaryClip(clipData);
                Toast.makeText(getApplicationContext(), "Copied to clipboard", Toast.LENGTH_SHORT).show();
            }
        });
    }

}
