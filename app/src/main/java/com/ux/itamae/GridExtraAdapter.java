package com.ux.itamae;

import android.content.Context;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;

import java.util.ArrayList;


public class GridExtraAdapter extends ArrayAdapter<Integer> {
    private Context context;
    private LayoutInflater layoutInflater;
    private int layoutResource;
    private String mAppend;
    private ArrayList<Integer> allImages;
    public ExtraClickCallBack callBack;

    public GridExtraAdapter(Context context, int layoutResource, String mAppend, ArrayList<Integer> images) {
        super(context, layoutResource, images);
        this.layoutInflater = (LayoutInflater) context.getSystemService(context.LAYOUT_INFLATER_SERVICE);
        this.context = context;
        this.layoutResource = layoutResource;
        this.mAppend = mAppend;
        this.allImages = images;
    }

    public interface ExtraClickCallBack {
        void onExtraClick(ImageView imageView);
    }

    private static class ViewHolder {
        ImageView imageView;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        final ViewHolder holder;
        Integer image = getItem(position);
        if (convertView == null) {
            convertView = layoutInflater.inflate(layoutResource, parent, false);
            holder = new ViewHolder();
            holder.imageView = (ImageView) convertView.findViewById(R.id.extra_image);
            holder.imageView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if (callBack != null) {
                        callBack.onExtraClick(holder.imageView);
                    }
                }
            });
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }

        holder.imageView.setImageResource(image);

        return convertView;
    }
}