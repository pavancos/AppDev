package com.example.recycler4;

import android.os.Bundle;
import android.util.Log;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import java.util.List;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {
    private RecyclerView recyclerView;
    private TodoAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        recyclerView = findViewById(R.id.todoList);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        fetchTodos();
    }
    private void fetchTodos(){
        List<TodoItem> todoList = List.of(
                new TodoItem("Todo 1"),
                new TodoItem("Todo 2"),
                new TodoItem("Todo 3"),
                new TodoItem("Todo 4"),
                new TodoItem("Todo 5"),
                new TodoItem("Todo 6"),
                new TodoItem("Todo 7"),
                new TodoItem("Todo 8"),
                new TodoItem("Todo 9"),
                new TodoItem("Todo 10"),
                new TodoItem("Todo 11"),
                new TodoItem("Todo 12"),
                new TodoItem("Todo 13"),
                new TodoItem("Todo 14")
        );
        adapter = new TodoAdapter(todoList);
        recyclerView.setAdapter(adapter);
    }
}
