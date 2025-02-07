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

    }
    public void GoToPortfolio(){
        Uri portfolioLink = Uri.parse("https://pavanc.me");
        Intent intent = new Intent(Intent.ACTION_VIEW, portfolioLink);
        Log.d("DebugUsername", "Hello");
        startActivity(intent);
    }
}