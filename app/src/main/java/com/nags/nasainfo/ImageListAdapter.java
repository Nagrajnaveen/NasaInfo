package com.nags.nasainfo;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;


public class ImageListAdapter extends RecyclerView.Adapter<ImageListAdapter.ViewHolder> {


   private List<InfoModel> infoModels;
   Context mContext;
    private OnItemClickListener  mOnItemClickListener;

    public ImageListAdapter(List<InfoModel> infoModels,Context context){
        this.infoModels=infoModels;
        this.mContext=context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.image_item, parent, false);
        return new ViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, final int position) {

        holder.titleTv.setText(infoModels.get(position).getTitle());
        Picasso.get().load(infoModels.get(position).getUrl()).into(holder.imageView);

        holder.rl.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (mOnItemClickListener!=null){
                    mOnItemClickListener.onItemClick(view,position,infoModels.get(position));
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return  infoModels.size();
    }

     class ViewHolder extends RecyclerView.ViewHolder{
        private TextView titleTv;
        private ImageView imageView;
        private RelativeLayout rl;
        ViewHolder(View view){
            super(view);

            titleTv=view.findViewById(R.id.titleTV);
            imageView=view.findViewById(R.id.gridIV);
            rl=view.findViewById(R.id.rl);
        }
    }


    public void setOnItemClickListener(final OnItemClickListener mItemClickListener) {
        this.mOnItemClickListener = mItemClickListener;
    }

    public interface OnItemClickListener {
        void onItemClick(View view,  int position,InfoModel infoModel);
    }
}
