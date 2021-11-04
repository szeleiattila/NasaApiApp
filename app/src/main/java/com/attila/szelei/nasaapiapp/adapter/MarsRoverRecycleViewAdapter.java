package com.attila.szelei.nasaapiapp.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.attila.szelei.nasaapiapp.model.MarsRoverPOJO;
import com.attila.szelei.nasaapiapp.R;
import com.squareup.picasso.Picasso;

import java.util.List;



public class MarsRoverRecycleViewAdapter extends RecyclerView.Adapter<MarsRoverRecycleViewAdapter.MyViewHolder> {

    private Context context;
    private List<MarsRoverPOJO.Photo> photoList;

    public MarsRoverRecycleViewAdapter(MarsRoverPOJO.Photo marsRoverList) {
        this.context = context;
    }


    public MarsRoverRecycleViewAdapter(Context context) {
        this.context = context;
    }

    public MarsRoverRecycleViewAdapter(List<MarsRoverPOJO.Photo> photoList, Context context) {
        this.photoList = photoList;
        this.context = context;
    }




    @Override
    public MarsRoverRecycleViewAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        LayoutInflater layoutInflater = LayoutInflater.from(context);
        View v= layoutInflater.inflate(R.layout.marsrover_item,parent, false);

        return new MyViewHolder(v);

    }

    @Override
    public void onBindViewHolder(@NonNull MarsRoverRecycleViewAdapter.MyViewHolder holder, int position) {
        holder.cam_name.setText(photoList.get(position).getCamera().getFullName());
        holder.sol.setText("sol: "+String.valueOf(photoList.get(position).getSol()));
        holder.photo_earth_date.setText(photoList.get(position).getEarthDate());
        Picasso.with(context)
                .load(photoList.get(position).getImgSrc())
                .placeholder(R.mipmap.ic_launcher)
                .into(holder.img);
        holder.mr_name.setText("MARS ROVER: "+photoList.get(position).getRover().getName());


}


    @Override
    public int getItemCount() {
        return photoList.size();
    }
  
    public static class MyViewHolder extends RecyclerView.ViewHolder{


        ImageView img;
        TextView photo_earth_date;
        TextView cam_name;
        TextView mr_name;
        TextView sol;


        public MyViewHolder(@NonNull View itemView) {
            super(itemView);

            img = itemView.findViewById(R.id.apod_imageView);
            photo_earth_date =itemView.findViewById(R.id.earthDate_textView);
            cam_name=itemView.findViewById(R.id.title_textView);
            mr_name =itemView.findViewById(R.id.roverName_textView);
            sol = itemView.findViewById(R.id.sol_textView);


        }
    }
}
