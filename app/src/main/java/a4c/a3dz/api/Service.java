package a4c.a3dz.api;

import a4c.a3dz.Response;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * Created by Ramil on 21.08.2017.
 */

public interface Service {
    @GET("forecast/random/20")
    Call<Response> getJoke(@Query("firstName") String firstName, @Query("lastName") String lastName);
}
