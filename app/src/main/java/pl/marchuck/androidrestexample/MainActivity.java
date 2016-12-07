package pl.marchuck.androidrestexample;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.Toast;

import java.util.List;

import pl.marchuck.androidrestexample.model.Task;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.content_main);

        Button createTaskButton = (Button) findViewById(R.id.createButton);
        Button readTasksButton = (Button) findViewById(R.id.readButton);
        Button updateTaskButton = (Button) findViewById(R.id.updateButton);
        Button deleteTaskButton = (Button) findViewById(R.id.deleteButton);
        RestClient restClient = new RestClient();

        final RestAPI restAPI = restClient.getRestApi();

        createTaskButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                final Task task = new Task();

                task.description = "vsvvdsvsfvfdvd";
                task.id = "0";
                task.title = "XDXDXD";

                restAPI.createTask(task).enqueue(new Callback<Task>() {
                    @Override
                    public void onResponse(Call<Task> call, Response<Task> response) {

                        //task created
                        Toast.makeText(MainActivity.this, "TASK CREATED", Toast.LENGTH_SHORT).show();
                    }

                    @Override
                    public void onFailure(Call<Task> call, Throwable t) {
                        Toast.makeText(MainActivity.this, "FAILED TO CREATE TASK " + task.title, Toast.LENGTH_SHORT).show();
                    }
                });
            }
        });

        readTasksButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                restAPI.getTasks().enqueue(new Callback<List<Task>>() {
                    @Override
                    public void onResponse(Call<List<Task>> call, Response<List<Task>> response) {
                        List<Task> tasksReceived = response.body();
                        //do someething with created tasks
                        Toast.makeText(MainActivity.this, "TASKS RECEIVED", Toast.LENGTH_SHORT).show();

                    }

                    @Override
                    public void onFailure(Call<List<Task>> call, Throwable t) {
                        Toast.makeText(MainActivity.this, "ERROR READING TASKS", Toast.LENGTH_SHORT).show();

                    }
                });
            }
        });

        deleteTaskButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                restAPI.deleteTask("0").enqueue(new Callback<Task>() {
                    @Override
                    public void onResponse(Call<Task> call, Response<Task> response) {
                        //task with id 0 deleted
                        Toast.makeText(MainActivity.this, "TASK DELETED", Toast.LENGTH_SHORT).show();
                    }

                    @Override
                    public void onFailure(Call<Task> call, Throwable t) {
                        //error during deleting task
                        Toast.makeText(MainActivity.this, "ERROR DELETING TASK", Toast.LENGTH_SHORT).show();

                    }
                });
            }
        });

        updateTaskButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Task taskToUpdate = new Task();
                taskToUpdate.id = "0";
                taskToUpdate.title = "updatedTitle";

                restAPI.updateTask(taskToUpdate).enqueue(new Callback<Task>() {
                    @Override
                    public void onResponse(Call<Task> call, Response<Task> response) {
                        //task with id 0 updated
                    }

                    @Override
                    public void onFailure(Call<Task> call, Throwable t) {
                        //error during deleting task
                        Toast.makeText(MainActivity.this, "ERROR UPDATING TASK", Toast.LENGTH_SHORT).show();

                    }
                });
            }
        });
    }

}
