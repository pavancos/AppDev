package com.example.firebase7;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import com.example.firebase7.Student;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class MainActivity extends AppCompatActivity {
    EditText rollNumber, name, cgpa;
    Button insert;
    FirebaseDatabase studentDatabase;

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
        studentDatabase = FirebaseDatabase.getInstance("https://fir-7-a8d21-default-rtdb.asia-southeast1.firebasedatabase.app/");
        rollNumber = (EditText)this.findViewById(R.id.rollNumber);
        name = (EditText)this.findViewById(R.id.name);
        cgpa = (EditText)this.findViewById(R.id.cgpa);
        insert = (Button)this.findViewById(R.id.insert);
        insert.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                insertStudent(v);
            }
        });

    }

    public void insertStudent(View vw){
        String rollNumber = this.rollNumber.getText().toString();
        String name = this.name.getText().toString();
        double cgpa = Double.parseDouble(this.cgpa.getText().toString());
        Student student = new Student(rollNumber, name, cgpa);
        DatabaseReference studentRef = studentDatabase.getReference("students");
        studentRef.push().setValue(student);
    }
    
}