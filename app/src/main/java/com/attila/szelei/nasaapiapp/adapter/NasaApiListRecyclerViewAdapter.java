package com.attila.szelei.nasaapiapp.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;
import com.attila.szelei.nasaapiapp.activities.APODActivity;
import com.attila.szelei.nasaapiapp.R;
import com.attila.szelei.nasaapiapp.activities.MarsRoverActivity;
import com.attila.szelei.nasaapiapp.model.NasaApiList;
import com.squareup.picasso.Picasso;

import java.util.List;

public class NasaApiListRecyclerViewAdapter extends RecyclerView.Adapter<NasaApiListRecyclerViewAdapter.MyViewHolder> {

    List<NasaApiList> nasaApiListList;
    Context context;

    public NasaApiListRecyclerViewAdapter(List<NasaApiList> nasaApiListList) {
        this.nasaApiListList = nasaApiListList;

    }

    public NasaApiListRecyclerViewAdapter(List<NasaApiList> nasaApiListList, Context context) {
        this.nasaApiListList = nasaApiListList;
        this.context = context;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.nasaapilist_item, parent, false);
        return new MyViewHolder(view);
    }


    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, final int position) {
        holder.endpoint.setText(nasaApiListList.get(position).getEndpoint());
        holder.description.setText(nasaApiListList.get(position).getDescription());

        holder.cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final Intent intent;
                switch (position){
                    case 0:
                        intent =  new Intent(context, APODActivity.class);
                        break;

                    case 1:
                        intent =  new Intent(context, MarsRoverActivity.class);
                        break;

                    default:
                        intent =  new Intent(context, NasaApiList.class);
                        break;
                }
                context.startActivity(intent);

                Toast.makeText(context, nasaApiListList.get(position).getEndpoint()+" endpoint called!", Toast.LENGTH_SHORT).show();
            }
        });
        Picasso.with(context)
                .load(nasaApiListList.get(position).getUrl()).into(holder.image);

    }


    @Override
    public int getItemCount() {
        return nasaApiListList.size();
    }

    public void setContacts(List<NasaApiList> nasaApiLists) {
        this.nasaApiListList =nasaApiLists;
        notifyDataSetChanged();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder{
        private TextView endpoint;
        private TextView description;
        private CardView cardView;
        private ImageView image;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            endpoint = itemView.findViewById(R.id.title_textView);
            description = itemView.findViewById(R.id.earthDate_textView);
            cardView = itemView.findViewById(R.id.cardView);
            image = itemView.findViewById(R.id.apod_imageView);
        }
    }
}
