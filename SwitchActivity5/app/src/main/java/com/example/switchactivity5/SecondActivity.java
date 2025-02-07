package com.example.switchactivity5;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class SecondActivity extends AppCompatActivity {
    TextView profileUsername;
    Button goToPortfolio_btn;
    Button mailSupport_btn;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_second);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        profileUsername = findViewById(R.id.profileUsername);
        Intent intent = getIntent();
        String username = intent.getStringExtra("username");
        profileUsername.setText(username);
        goToPortfolio_btn = findViewById(R.id.goToLink);
        goToPortfolio_btn.setOnClickListener(v-> GoToPortfolio());
        mailSupport_btn = findViewById(R.id.mailSupport);
        mailSupport_btn.setOnClickListener(v-> mailSupport());

    }
    public void GoToPortfolio(){
        Uri portfolioLink = Uri.parse("https://pavanc.me");
        Intent intent = new Intent(Intent.ACTION_VIEW, portfolioLink);
        startActivity(intent);
    }
    public void mailSupport() {
        Intent intent = new Intent(Intent.ACTION_SENDTO);
        intent.setData(Uri.parse("mailto:pavankc005@gmail.com"));
        intent.putExtra(Intent.EXTRA_SUBJECT, "Support Request");
        intent.putExtra(Intent.EXTRA_TEXT, "Hello, I need help with...");
        startActivity(intent);
    }

}