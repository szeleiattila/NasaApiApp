package com.attila.szelei.nasaapiapp.activities;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.attila.szelei.nasaapiapp.R;
import com.attila.szelei.nasaapiapp.model.ApodPictures;
import com.attila.szelei.nasaapiapp.service.NodeRestService;
import com.attila.szelei.nasaapiapp.service.RetrofitClient;
import com.squareup.picasso.MemoryPolicy;
import com.squareup.picasso.Picasso;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class ImageFullHdActivity extends AppCompatActivity {

    NodeRestService nodeRestService;
    ProgressBar progressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.imagefullhd);
        Retrofit retrofitClient = RetrofitClient.getRetroFitClient();
        nodeRestService = retrofitClient.create(NodeRestService.class);



        getFullHDimage();

    }
    private void getFullHDimage(){
       final ImageView imgFullHD = findViewById(R.id.imageFullHD);
       progressBar= findViewById(R.id.progressBar);
       Picasso .with(getApplicationContext()).setIndicatorsEnabled(true); //green triangle memory cache, blue triangle disk cache

        nodeRestService= RetrofitClient.getNodeRestService();
        Call<ApodPictures> call = nodeRestService.getAPODpicture();
        call.enqueue(new Callback<ApodPictures>() {
            @Override
            public void onResponse(Call<ApodPictures> call, Response<ApodPictures> response) {

                final String hdurl=response.body().getHdUrl();
                if(response.isSuccessful()){
                    Picasso.with(getApplicationContext())
                            .load(response.body().getHdUrl())
                            .fetch(new com.squareup.picasso.Callback() {
                                @Override
                                public void onSuccess() {
                                    progressBar.setVisibility(View.GONE);
                                    Picasso.with(getApplicationContext())
                                            .load(hdurl)
                                            .memoryPolicy(MemoryPolicy.NO_CACHE) //skips the memory cache
                                            .into(imgFullHD);
                                }

                                @Override
                                public void onError() {

                                }
                            });
                }
            }

            @Override
            public void onFailure(Call<ApodPictures> call, Throwable t) {
                Toast.makeText(ImageFullHdActivity.this,"Error " + t.getMessage(),Toast.LENGTH_SHORT).show();
            }
        });

    }
}