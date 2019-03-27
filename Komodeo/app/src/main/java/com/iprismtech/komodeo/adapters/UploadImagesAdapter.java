package com.iprismtech.komodeo.adapters;

import android.content.Context;
import android.graphics.Bitmap;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;


import com.iprismtech.komodeo.R;

import java.util.ArrayList;


public class UploadImagesAdapter extends RecyclerView.Adapter<UploadImagesAdapter.ViewHolder> {
    // private OnItemClickListener onItemClickListener;
    private ArrayList<Bitmap> images;

    private Context context;
    private String status;
    private int size;


    public UploadImagesAdapter(ArrayList<Bitmap> uploadimages, Context myStaffAlerts) {
        this.images = uploadimages;
        this.context = myStaffAlerts;
        //this.onItemClickListener = onItemClickListener;
        // status="phoneImages";
    }

    private UploadImagesAdapter.OnitemClickListener mListner;

    public void setOnItemClickListener(UploadImagesAdapter.OnitemClickListener onitemClickListener) {
        mListner = onitemClickListener;
    }

    public interface OnitemClickListener {
        void onItemClick(View view, int position);
    }
//    public UploadImagesAdapter(Context context, VendorShopDetailsPojo vendorShopDetailsPojo, OnItemClickListener onItemClickListener) {
//        this.vendorShopDetailsPojo=vendorShopDetailsPojo;
//        this.onItemClickListener = onItemClickListener;
//        this.context=context;
//       // status="serverImages";
//    }


    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(context).inflate(R.layout.indi_vendor_add_image, parent, false);
        ViewHolder viewHolder = new ViewHolder(itemView);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

//        if(status.equalsIgnoreCase("serverImages")) {
//            Picasso.with(context)
//                    .load(NetworkConstants.URL.Imagepath_URL + vendorShopDetailsPojo.getResponse().getImages().get(position).getImage())
//                    .error(R.drawable.no_image)
//                    .into(holder.uploaded_img);
//        }
//        else if(status.equalsIgnoreCase("phoneImages")){
//            holder.uploaded_img.setImageBitmap(images.get(position));
//        }
        holder.uploaded_img.setImageBitmap(images.get(position));
    }

    @Override
    public int getItemCount() {
        return images.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        ImageView uploaded_img, del_img;

        public ViewHolder(View itemView) {
            super(itemView);
            uploaded_img = itemView.findViewById(R.id.imageupload);
            del_img = itemView.findViewById(R.id.imgdelete);
           // del_img.setOnClickListener(this);
        }
        @Override
        public void onClick(View view) {
            if (mListner != null) {
                mListner.onItemClick(view, getAdapterPosition());
            }
        }
    }
}
