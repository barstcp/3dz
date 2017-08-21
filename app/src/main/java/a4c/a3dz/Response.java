package a4c.a3dz;

import com.google.gson.annotations.SerializedName;

import java.util.List;

import a4c.a3dz.api.Joke;

/**
 * Created by Ramil on 21.08.2017.
 */

public class Response {
    @SerializedName("type")
    String status;

    @SerializedName("value")
    List<Joke> jokes;

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public List<Joke> getJokes() {
        return jokes;
    }

    public void setJokes(List<Joke> jokes) {
        this.jokes = jokes;
    }

    public Response(String status, List<Joke> jokes) {

        this.status = status;
        this.jokes = jokes;
    }


}
