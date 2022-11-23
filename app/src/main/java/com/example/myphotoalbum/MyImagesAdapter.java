package com.example.myphotoalbum;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.text.Layout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.example.myphotoalbum.database.MyImages;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;

public class MyImagesAdapter extends RecyclerView.Adapter<MyImagesAdapter.MyViewHolder> {

    List<MyImages> mImagesList = new ArrayList();


    public MyImages getPosition(int position){
        return mImagesList.get(position);
    }

    @NonNull
    @NotNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull @NotNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.image_card, parent, false);


        return new MyViewHolder(view);
    }

    public void  setImagesList(List<MyImages> imagesList) {
        mImagesList = imagesList;
        notifyDataSetChanged();
    }

    @Override
    public void onBindViewHolder(@NonNull @NotNull MyViewHolder holder, int position) {
//        MyImages myImage = mImagesList.get(position);
//        holder.image;
//        holder.title.setText(myImage.getImage_title().toString());
//        holder.description.setText(myImage.getImage_description().toString());

        MyImages myImages = mImagesList.get(position);
        holder.title.setText(myImages.getImage_title());
        holder.description.setText(myImages.getImage_description());
        holder.image.setImageBitmap(
                BitmapFactory.decodeByteArray(
                myImages.getImage(),
                0, myImages.getImage().length));



    }

    @Override
    public int getItemCount() {
        return mImagesList.size();
    }

    class MyViewHolder extends RecyclerView.ViewHolder {
        ImageView image;
        TextView title, description;

        public MyViewHolder(@NonNull @NotNull View itemView) {
            super(itemView);

            image = itemView.findViewById(R.id.imageView_cardvw);
            title = itemView.findViewById(R.id.title_txvw_cardvw);
            description = itemView.findViewById(R.id.description_txvw_cardvw);
        }
    }
}
