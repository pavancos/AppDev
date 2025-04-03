package com.example.recycler;

import android.os.Bundle;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class MainActivity extends AppCompatActivity {
    RecyclerView rv;
    PersonAdapter personAdapter;

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
        rv = findViewById(R.id.rV);

        List<Person>p = List.of(
                new Person("Pavan",R.drawable.pavan),
                new Person("Kumar",R.drawable.pavan),
                new Person("Chennupati",R.drawable.pavan),
                new Person("Pavan",R.drawable.pavan),
                new Person("Pavan",R.drawable.pavan),
                new Person("Pavan",R.drawable.pavan),
                new Person("Pavan",R.drawable.pavan),
                new Person("Pavan",R.drawable.pavan),
                new Person("Pavan",R.drawable.pavan),
                new Person("Pavan",R.drawable.pavan),
                new Person("Pavan",R.drawable.pavan),
                new Person("Pavan",R.drawable.pavan),
                new Person("Pavan",R.drawable.pavan),
                new Person("Pavan",R.drawable.pavan),
                new Person("Pavan",R.drawable.pavan),
                new Person("Pavan",R.drawable.pavan),
                new Person("Pavan",R.drawable.pavan),
                new Person("Pavan",R.drawable.pavan),
                new Person("Pavan",R.drawable.pavan),
                new Person("Pavan",R.drawable.pavan),
                new Person("Pavan",R.drawable.pavan),
                new Person("Pavan",R.drawable.pavan)
        );
        personAdapter = new PersonAdapter();
        personAdapter.persons = p;
        rv.setLayoutManager(new LinearLayoutManager(this));
        rv.setAdapter(personAdapter);
    }
}