package com.eigendaksh.simpledatabaseapp;

import android.annotation.SuppressLint;
import android.arch.persistence.room.util.StringUtil;
import android.content.Context;
import android.os.AsyncTask;
import android.support.design.widget.TextInputEditText;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class AddTaskActivity extends AppCompatActivity {

    private TextInputEditText taskTitleEditText;
    private TextInputEditText taskDescriptionEditText;
    private Button addTaskButton;
    private InsertToDoIntoDb mInsertTask;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_task);

        initUI();
    }

    private void initUI() {
        ActionBar supportActionBar = getSupportActionBar();
        if(supportActionBar != null) {
            supportActionBar.setTitle(R.string.add_new_task);
            supportActionBar.setDisplayHomeAsUpEnabled(true);
        }

        taskTitleEditText = findViewById(R.id.taskTitle);
        taskDescriptionEditText = findViewById(R.id.taskDescription);
        addTaskButton = findViewById(R.id.addTask);

        addTaskButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addTaskButtonPressed();
            }
        });
    }

    private void addTaskButtonPressed() {
        String taskTitle = taskTitleEditText.getText().toString();
        String taskDescription = taskDescriptionEditText.getText().toString();

        if(TextUtils.isEmpty(taskTitle) || TextUtils.isEmpty(taskDescription)) {
            showToast(getString(R.string.task_title_msg));
        } else {
            mInsertTask = new InsertToDoIntoDb(this);
            String[] taskStrings = {taskTitle, taskDescription};
            mInsertTask.execute(taskStrings);
        }
    }

    @SuppressLint("StaticFieldLeak")
    private class InsertToDoIntoDb extends AsyncTask<String, Void, Long> {

        private final Context mContext;

        public InsertToDoIntoDb(Context context) {
            this.mContext = context;
        }

        @Override
        protected Long doInBackground(String... strings) {
            String title = strings[0];
            String description = strings[1];
            TodoEntry todoEntry = new TodoEntry(title, description);
            return MyApplication.getDB(mContext).toDoDao().insertToDo(todoEntry);
        }

        @Override
        protected void onPostExecute(Long aLong) {
            super.onPostExecute(aLong);
            if(aLong > -1) {
                showToast("Task successfully inserted into the database");
                finish();
            } else {
                showToast("Error in inserting task into the database");
            }

        }
    }

    private void showToast(String message) {
        Toast toast = Toast.makeText(this, message, Toast.LENGTH_SHORT);
        toast.setGravity(Gravity.CENTER, 0, 0);
        toast.show();
    }

    @Override
    protected void onPause() {
        if(mInsertTask != null && mInsertTask.getStatus() != AsyncTask.Status.FINISHED) {
            mInsertTask.cancel(true);
        }
        super.onPause();
    }

    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }
}
