package com.example.toasts_01;

import android.os.Bundle;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.Toast;

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
        Toast.makeText(
                this,
                "This is onCreate()",
                Toast.LENGTH_SHORT
        ).show();
    }

//    @Override
//    protected void onResume() {
//        super.onResume();
//        Toast.makeText(
//                this,
//                "This is onResume()",
//                Toast.LENGTH_SHORT
//        ).show();
//    }
//    @Override
//    protected void onPause() {
//        super.onPause();
//        Toast.makeText(
//                this,
//                "This is onPause()",
//                Toast.LENGTH_SHORT
//        ).show();
//    }
//
//    @Override
//    protected void onStop() {
//        super.onStop();
//        Toast.makeText(
//                this,
//                "This is onStop()",
//                Toast.LENGTH_SHORT
//        ).show();
//    }
//
//    @Override
//    protected void onDestroy() {
//        super.onDestroy();
//        Toast.makeText(
//                this,
//                "This is onDestroy()",
//                Toast.LENGTH_SHORT
//        ).show();
//    }
//
//    @Override
//    protected void onRestart() {
//        super.onRestart();
//        Toast.makeText(
//                this,
//                "This is onRestart()",
//                Toast.LENGTH_SHORT
//        ).show();
//    }
//
//    @Override
//    protected void onStart() {
//        super.onStart();
//        Toast.makeText(
//                this,
//                "This is onStart()",
//                Toast.LENGTH_SHORT
//        ).show();
//    }

    public void showText(View view){
        Toast.makeText(
                this,
                "This is on Click",
                Toast.LENGTH_SHORT
        ).show();
    }

    public void showCustomToast(View view){
        LayoutInflater li = getLayoutInflater();
        ViewGroup layout = findViewById(R.id.custom_toast);
        View layoutView = li.inflate(R.layout.custom_toast, layout);
        Toast toast = new Toast(this);
        toast.setDuration(Toast.LENGTH_SHORT);
        toast.setGravity(Gravity.BOTTOM, 0, 300);
        toast.setView(layoutView);
        toast.show();
    }


}

