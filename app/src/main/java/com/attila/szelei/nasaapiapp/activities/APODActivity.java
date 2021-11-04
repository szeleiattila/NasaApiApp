package com.attila.szelei.nasaapiapp.activities;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import com.attila.szelei.nasaapiapp.R;
import com.attila.szelei.nasaapiapp.model.ApodPictures;
import com.attila.szelei.nasaapiapp.service.NodeRestService;
import com.attila.szelei.nasaapiapp.service.RetrofitClient;
import com.squareup.picasso.Picasso;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class APODActivity  extends AppCompatActivity {

    NodeRestService nodeRestService;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.apodpicture_item);
        Retrofit retrofitClient = RetrofitClient.getRetroFitClient();
        nodeRestService = retrofitClient.create(NodeRestService.class);

        getApodPicture();

    }
    private void getApodPicture() {
        final TextView textViewTitle = findViewById(R.id.title_textView);
        final TextView textViewExplanation = findViewById(R.id.earthDate_textView);
        final TextView textViewdate = findViewById(R.id.date_textView);
        final TextView textViewClickhere = findViewById(R.id.clickHereForHD);
        final ImageView imageViewApod = findViewById(R.id.apod_imageView);



        nodeRestService= RetrofitClient.getNodeRestService();
        Call<ApodPictures> call = nodeRestService.getAPODpicture();
        call.enqueue(new Callback<ApodPictures>() {
            @Override
            public void onResponse(Call<ApodPictures> call, Response<ApodPictures> response) {
                if(response.isSuccessful()){
                    textViewTitle.setText(response.body().getTitle());
                    textViewExplanation.setText(response.body().getExplanation());
                    textViewdate.setText("date: "+response.body().getDate());

                    Picasso.with(getApplicationContext())
                            .load(response.body().getUrl())
                            .into(imageViewApod);

                    textViewClickhere.setOnClickListener(new View.OnClickListener() {
                        @Override

                        public void onClick(View v) {
                            final Intent intent = new Intent(getApplicationContext(),ImageFullHdActivity.class);
                         startActivity(intent);

                        }
                    });

                }
            }

            @Override
            public void onFailure(Call<ApodPictures> call, Throwable t) {
                Toast.makeText(APODActivity.this,"Error " + t.getMessage(),Toast.LENGTH_SHORT).show();
            }
        });

    }
}
