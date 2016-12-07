package pl.marchuck.androidrestexample;

import java.util.List;

import pl.marchuck.androidrestexample.model.Task;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Query;

/**
 * Project "AndroidRESTExample"
 * <p>
 * Created by Lukasz Marczak
 * on 07.12.2016.
 */

public interface RestAPI {

    @GET("tasks")
    Call<List<Task>> getTasks();

    @GET("tasks")
    Call<Task> deleteTask(@Query("id") String id);

    @POST("tasks")
    Call<Task> createTask(@Body Task task);

    @POST("tasks")
    Call<Task> updateTask(@Body Task task);

}
