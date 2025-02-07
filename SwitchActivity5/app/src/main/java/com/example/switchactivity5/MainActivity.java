package com.example.switchactivity5;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {
    Button switchToProfile_btn;
    EditText usernameInput;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        switchToProfile_btn = findViewById(R.id.switchToProfile);
        usernameInput = findViewById(R.id.usernameInput);
        switchToProfile_btn.setOnClickListener(v->{
            String username = usernameInput.getText().toString();
            Intent intent = new Intent(this, SecondActivity.class);
            intent.putExtra("username", username);
            startActivity(intent);
        });
    }
}