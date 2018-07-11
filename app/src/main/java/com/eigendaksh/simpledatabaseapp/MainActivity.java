package com.eigendaksh.simpledatabaseapp;

import android.app.LoaderManager;
import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.content.Intent;
import android.content.Loader;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Toast;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    private TodoEntryViewModel viewModel;
    private RecyclerView todoRecyclerView;
    private FloatingActionButton addToDoButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        viewModel = ViewModelProviders.of(this).get(TodoEntryViewModel.class);

        // Initialize UI
        initUI();
    }

    private void initUI() {
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setTitle(R.string.todo_list);
            actionBar.setDisplayHomeAsUpEnabled(true);
        }

        addToDoButton = findViewById(R.id.addButton);
        addToDoButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, AddTaskActivity.class);
                startActivity(intent);
            }
        });

        viewModel.getTodoList().observe(MainActivity.this, new Observer<List<TodoEntry>>() {
            @Override
            public void onChanged(@Nullable List<TodoEntry> todoEntries) {
                // TODO: create your RecyclerView and Adapter and load your data here
                //todoRecyclerView.getAdapter().setData(todoEntries);
                Toast.makeText(MainActivity.this, todoEntries.size() + "", Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }

}
