package a4c.a3dz.api;

import com.google.gson.annotations.SerializedName;

/**
 * Created by Ramil on 21.08.2017.
 */

public class Joke{
    @SerializedName("id")
    int id;
    @SerializedName("joke")
    String joke;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getJoke() {
        return joke;
    }

    public void setJoke(String joke) {
        this.joke = joke;
    }

    public Joke(int id, String joke) {

        this.id = id;
        this.joke = joke;
    }
}