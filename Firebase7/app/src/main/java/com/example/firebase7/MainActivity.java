package com.example.firebase7;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import com.example.firebase7.Student;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class MainActivity extends AppCompatActivity {
    EditText rollNumber, name, cgpa;
    Button insert, get;
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
        insert.setOnClickListener(v -> insertStudent(v));
        get = (Button)this.findViewById(R.id.get);
        get.setOnClickListener(v -> getStudent(v));
    }

    public void insertStudent(View vw){
        String rollNumber = this.rollNumber.getText().toString();
        String name = this.name.getText().toString();
        double cgpa = Double.parseDouble(this.cgpa.getText().toString());
        Student student = new Student(rollNumber, name, cgpa);
        DatabaseReference studentRef = studentDatabase.getReference("students");
        studentRef.push().setValue(student);
        this.rollNumber.setText("");
        this.name.setText("");
        this.cgpa.setText("");
    }

    public void getStudent(View vw) {
        DatabaseReference studentRef = studentDatabase.getReference("students");
        studentRef.get().addOnCompleteListener(new OnCompleteListener<DataSnapshot>() {
            @Override
            public void onComplete(@NonNull Task<DataSnapshot> task) {
                if (task.isSuccessful()) {
                    DataSnapshot snapshot = task.getResult();
                    LinearLayout studentsLayout = findViewById(R.id .students);
                    studentsLayout.removeAllViews();

                    for (DataSnapshot child : snapshot.getChildren()) {
                        Student student = child.getValue(Student.class);
                        if (student != null) {
                            TextView studentView = new TextView(MainActivity.this);
                            studentView.setText("Roll: " + student.getRollNumber() +
                                    ", Name: " + student.getName() +
                                    ", CGPA: " + student.getCgpa());
                            studentView.setTextSize(18);
                            studentView.setPadding(10, 10, 10, 10);

                            studentsLayout.addView(studentView);
                        }
                    }
                } else {
                    System.out.println(task.getException());
                }
            }
        });
    }

}