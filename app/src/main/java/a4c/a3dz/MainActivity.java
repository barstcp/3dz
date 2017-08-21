package a4c.a3dz;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.appindexing.AppIndex;
import com.google.android.gms.common.api.GoogleApiClient;

import java.util.ArrayList;
import java.util.List;

import a4c.a3dz.api.ApiFactory;
import a4c.a3dz.api.Joke;
import a4c.a3dz.api.Service;
import butterknife.BindView;
import butterknife.ButterKnife;
import retrofit2.Call;
import retrofit2.Callback;

public class MainActivity extends AppCompatActivity {

    Service service;
    private GoogleApiClient client;
    @BindView(R.id.rv)
    RecyclerView recyclerView;
    JokeAdapder adapter;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ButterKnife.bind(this);

        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this);
        List<Joke> jokes = new ArrayList<>();

        recyclerView.setLayoutManager(layoutManager);

        adapter = new JokeAdapder(jokes);

        service = ApiFactory.getRetrofitInstance().create(Service.class);

        recyclerView.setAdapter(adapter);
        getJoke();




        client = new GoogleApiClient.Builder(this).addApi(AppIndex.API).build();

    }

    private void getJoke() {

        Call<Response> call = service.getJoke("Vasya", "pupkin");

        call.enqueue(new Callback<Response>() {
            @Override
            public void onResponse(Call<Response> call, retrofit2.Response<Response> response) {
                if (response.isSuccessful()) {
                    List<Joke> j = response.body().getJokes();

                    adapter.add(0, j);

                    // Если в ответ нам пришел код 2xx, то отображаем содержимое запроса
                    //weather.setText(String.valueOf(response.body().getCode()));
                    Toast.makeText(MainActivity.this, response.body().getJokes().get(0).getJoke(), Toast.LENGTH_SHORT).show();


                } else {
                    // Если пришел код ошибки, то обрабатываем её
                    Toast.makeText(MainActivity.this, String.valueOf(response.code()), Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<Response> call, Throwable t) {
                Toast.makeText(MainActivity.this, "error", Toast.LENGTH_SHORT).show();

            }
        });
    }

    class JokeAdapder extends RecyclerView.Adapter<jokesViewHolder> {

        List<Joke> jokes;

        public JokeAdapder(List<Joke> jokes) {
            this.jokes = jokes;
        }

        @Override
        public jokesViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
            View rowView = layoutInflater.inflate(R.layout.row_jokes,parent,false);
            return new jokesViewHolder(rowView);
        }

        @Override
        public void onBindViewHolder(jokesViewHolder holder, int position) {
            holder.textView.setText(jokes.get(position).getJoke());
        }

        @Override
        public int getItemCount() {
            return jokes.size();
        }

        public void add(int i, List<Joke> newJokes) {
            jokes.addAll(i, newJokes);
            notifyItemRangeInserted(i, newJokes.size());
        }
    }

    public class jokesViewHolder extends RecyclerView.ViewHolder {

        public TextView textView;


        public jokesViewHolder(View itemView) {
            super(itemView);
            textView = (TextView) itemView.findViewById(R.id.pl);
        }
    }
}
