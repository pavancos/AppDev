package com.example.firebase7;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class MainActivity extends AppCompatActivity {
    EditText rollNumber, name, cgpa;
    Button insert, get, update, delete;
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

        // Initialize Firebase with correct database URL
        studentDatabase = FirebaseDatabase.getInstance("https://fir-7-a8d21-default-rtdb.asia-southeast1.firebasedatabase.app/");

        // Initialize UI components
        rollNumber = findViewById(R.id.rollNumber);
        name = findViewById(R.id.name);
        cgpa = findViewById(R.id.cgpa);
        insert = findViewById(R.id.insert);
        get = findViewById(R.id.get);
        update = findViewById(R.id.update);
        delete = findViewById(R.id.delete);

        // Set button click listeners
        insert.setOnClickListener(this::insertStudent);
        get.setOnClickListener(this::getStudent);
        update.setOnClickListener(this::updateStudent);
        delete.setOnClickListener(this::deleteStudent);
    }

    public void insertStudent(View vw) {
        String roll = rollNumber.getText().toString().trim();
        String studentName = name.getText().toString().trim();
        String cgpaText = cgpa.getText().toString().trim();

        if (roll.isEmpty() || studentName.isEmpty() || cgpaText.isEmpty()) {
            Toast.makeText(this, "All fields are required", Toast.LENGTH_SHORT).show();
            return;
        }

        double studentCgpa = Double.parseDouble(cgpaText);

        // Store the student with roll number as the key
        DatabaseReference studentRef = studentDatabase.getReference("students").child(roll);
        Student student = new Student(roll, studentName, studentCgpa);

        studentRef.setValue(student).addOnCompleteListener(task -> {
            if (task.isSuccessful()) {
                Toast.makeText(this, "Student added successfully", Toast.LENGTH_SHORT).show();
                rollNumber.setText("");
                name.setText("");
                cgpa.setText("");
            } else {
                Toast.makeText(this, "Failed to add student", Toast.LENGTH_SHORT).show();
            }
        });
    }

    public void getStudent(View vw) {
        DatabaseReference studentRef = studentDatabase.getReference("students");
        studentRef.get().addOnCompleteListener(task -> {
            if (task.isSuccessful() && task.getResult().exists()) {
                DataSnapshot snapshot = task.getResult();
                LinearLayout studentsLayout = findViewById(R.id.students);
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
                Toast.makeText(this, "No students found", Toast.LENGTH_SHORT).show();
            }
        });
    }

    public void updateStudent(View vw) {
        String roll = rollNumber.getText().toString().trim();
        String studentName = name.getText().toString().trim();
        String cgpaText = cgpa.getText().toString().trim();

        if (roll.isEmpty() || studentName.isEmpty() || cgpaText.isEmpty()) {
            Toast.makeText(this, "All fields are required", Toast.LENGTH_SHORT).show();
            return;
        }

        double studentCgpa = Double.parseDouble(cgpaText);

        DatabaseReference studentRef = studentDatabase.getReference("students").child(roll);
        studentRef.get().addOnCompleteListener(task -> {
            if (task.isSuccessful() && task.getResult().exists()) {
                studentRef.setValue(new Student(roll, studentName, studentCgpa))
                        .addOnCompleteListener(updateTask -> {
                            if (updateTask.isSuccessful()) {
                                Toast.makeText(this, "Student updated successfully", Toast.LENGTH_SHORT).show();
                            } else {
                                Toast.makeText(this, "Failed to update student", Toast.LENGTH_SHORT).show();
                            }
                        });
            } else {
                Toast.makeText(this, "Student not found", Toast.LENGTH_SHORT).show();
            }
        });
    }

    public void deleteStudent(View vw) {
        String roll = rollNumber.getText().toString().trim();

        if (roll.isEmpty()) {
            Toast.makeText(this, "Roll number is required", Toast.LENGTH_SHORT).show();
            return;
        }

        DatabaseReference studentRef = studentDatabase.getReference("students").child(roll);
        studentRef.get().addOnCompleteListener(task -> {
            if (task.isSuccessful() && task.getResult().exists()) {
                studentRef.removeValue().addOnCompleteListener(deleteTask -> {
                    if (deleteTask.isSuccessful()) {
                        Toast.makeText(this, "Student deleted successfully", Toast.LENGTH_SHORT).show();
                    } else {
                        Toast.makeText(this, "Failed to delete student", Toast.LENGTH_SHORT).show();
                    }
                });
            } else {
                Toast.makeText(this, "Student not found", Toast.LENGTH_SHORT).show();
            }
        });
    }
}
