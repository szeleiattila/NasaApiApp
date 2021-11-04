package com.attila.szelei.nasaapiapp.activities;
import android.os.Bundle;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.attila.szelei.nasaapiapp.R;
import com.attila.szelei.nasaapiapp.adapter.MarsRoverRecycleViewAdapter;
import com.attila.szelei.nasaapiapp.model.MarsRoverPOJO;
import com.attila.szelei.nasaapiapp.service.NodeRestService;
import com.attila.szelei.nasaapiapp.service.RetrofitClient;
import java.util.List;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class MarsRoverActivity extends AppCompatActivity {

    NodeRestService nodeRestService;
    RecyclerView marsRoverRecView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.marsroverlist);

            getMarsRoverData();


    }

     private void getMarsRoverData() {
         marsRoverRecView= findViewById(R.id.marsRoverRecView);

         marsRoverRecView.setLayoutManager(new LinearLayoutManager(MarsRoverActivity.this));
         nodeRestService= RetrofitClient.getNodeRestService();
         Call<List<MarsRoverPOJO.Photo>> listCall = nodeRestService.getRoverData();
         listCall.enqueue(new Callback<List<MarsRoverPOJO.Photo>>() {
             @Override
             public void onResponse(Call<List<MarsRoverPOJO.Photo>> call, Response<List<MarsRoverPOJO.Photo>> response) {
                 PutDataIntoRecyclerView(response.body());
             }
             @Override
             public void onFailure(Call<List<MarsRoverPOJO.Photo>> call, Throwable t) {
                 Toast.makeText(MarsRoverActivity.this, "Throwable: "+t.getLocalizedMessage(), Toast.LENGTH_SHORT).show();

             }
         });

}
    private void PutDataIntoRecyclerView(List<MarsRoverPOJO.Photo> photoList) {
        MarsRoverRecycleViewAdapter adapter = new MarsRoverRecycleViewAdapter(photoList,this);
        marsRoverRecView.setAdapter(adapter);


    }
}
