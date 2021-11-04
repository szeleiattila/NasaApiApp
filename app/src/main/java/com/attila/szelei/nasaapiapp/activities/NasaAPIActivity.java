package com.attila.szelei.nasaapiapp.activities;
import android.os.Bundle;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.attila.szelei.nasaapiapp.R;
import com.attila.szelei.nasaapiapp.adapter.NasaApiListRecyclerViewAdapter;
import com.attila.szelei.nasaapiapp.model.NasaApiList;
import com.attila.szelei.nasaapiapp.service.NodeRestService;
import com.attila.szelei.nasaapiapp.service.RetrofitClient;
import java.util.List;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class NasaAPIActivity extends AppCompatActivity {
    RecyclerView nasaApiRecView;
    NodeRestService nodeRestService;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.nasaapilist);


        nasaApiRecView= findViewById(R.id.apiRecView);
        nasaApiRecView.setLayoutManager(new LinearLayoutManager(NasaAPIActivity.this));
        nodeRestService= RetrofitClient.getNodeRestService();
        Call<List<NasaApiList>> listCall = nodeRestService.getnasaApiList();
        listCall.enqueue(new Callback<List<NasaApiList>>() {
            @Override
            public void onResponse(Call<List<NasaApiList>> call, Response<List<NasaApiList>> response) {
                if(response.isSuccessful()) {
                    putNasaApiRecView(response.body());
                }
            }
            @Override
            public void onFailure(Call<List<NasaApiList>> call, Throwable t) {
                Toast.makeText(NasaAPIActivity.this, "Throwable: "+t.getLocalizedMessage(), Toast.LENGTH_SHORT).show();

            }
        });

    }

    private void putNasaApiRecView(List<NasaApiList> body) {
        NasaApiListRecyclerViewAdapter recyclerViewAdapter = new NasaApiListRecyclerViewAdapter(body, this);
        nasaApiRecView.setAdapter(recyclerViewAdapter);
    }
}

