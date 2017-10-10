package com.example.guest.mapsapp.model;

import android.content.Context;
import android.graphics.Bitmap;
import android.location.Address;
import android.location.Geocoder;
import android.net.Uri;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.guest.mapsapp.R;

import java.io.File;
import java.util.List;
import java.util.Locale;

/**
 * Created by guest on 10/10/17.
 */

public class PastRecordsAdapter extends RecyclerView.Adapter<PastRecordsAdapter.ViewHolder>{

    List<User> users;
    onListClick onListClickListner;
    Context context;
    Geocoder geocoder;

    public PastRecordsAdapter(List<User> users, onListClick onListClickListner, Context context){
        this.users = users;
        this.onListClickListner = onListClickListner;
        this.context = context;
        geocoder = new Geocoder(context, Locale.getDefault());
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.past_record_item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {

        final User user = users.get(position);


        Log.d("Image Path:", user.getImagePath());


        holder.cameraThumb.setImageURI(Uri.parse(user.getImagePath()));
        try {
            List<Address> addresses = geocoder.getFromLocation(user.getLocationLatitude(), user.getLocationLongitude(), 1);
            String address = addresses.get(0).getAddressLine(0);
            String city = addresses.get(0).getLocality();
            holder.recordDescription.setText("Location Information:" +address +"," +city);
        }
        catch (Exception e){

        }

        holder.container.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onListClickListner.onListItemClick(user);
            }
        });

    }

    @Override
    public int getItemCount() {
        return users.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder{

        private ImageView cameraThumb;
        private TextView recordDescription;
        private LinearLayout container;

        public ViewHolder(View itemView) {
            super(itemView);

            cameraThumb = (ImageView) itemView.findViewById(R.id.camera_thumb);
            recordDescription = (TextView) itemView.findViewById(R.id.record_description);
            container = (LinearLayout) itemView.findViewById(R.id.container);

        }
    }
}
