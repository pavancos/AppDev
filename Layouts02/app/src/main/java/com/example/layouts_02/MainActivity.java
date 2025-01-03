package com.example.layouts_02;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {

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

    }

    public void onLinearClick(View vw) {
        Intent intent = new Intent(this, Linear.class);
        startActivity(intent);
    }

    public void onTableClick(View vw) {
        Intent intent = new Intent(this, Table.class);
        startActivity(intent);
    }

    public  void onFrameClick(View vw) {
        Intent intent = new Intent(this, Frame.class);
        startActivity(intent);
    }

    public void onRelativeClick(View vw){
        Intent intent = new Intent(this, Relative.class);
        startActivity(intent);;
    }

    public void onTabClick(View vw){
        Intent intent = new Intent(this, Tab.class);
        startActivity(intent);
    }

}