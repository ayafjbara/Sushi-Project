package com.ux.itamae;

import android.content.Context;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

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
        void onExtraClick(int imageView);
    }

    private static class ViewHolder {
        ImageView imageView;
        TextView textView;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        final ViewHolder holder;
        final Integer image = getItem(position);
        if (convertView == null) {
            convertView = layoutInflater.inflate(layoutResource, parent, false);
            holder = new ViewHolder();
            holder.imageView = (ImageView) convertView.findViewById(R.id.extra_image);
            holder.textView = (TextView) convertView.findViewById(R.id.extraName);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }

        holder.imageView.setImageResource(image);
        holder.textView.setText(getExtraName(image));
        holder.imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (callBack != null) {
                    callBack.onExtraClick(image);
                }
            }
        });

        return convertView;
    }

    private String getExtraName(int imageID) {
        switch (imageID) {
            case R.drawable.ic_ex_avocado:
                return "Avocado";
            case R.drawable.ic_ex_salmon:
                return "Salmon";
            case R.drawable.ic_ex_redtuna:
                return "Red Tuna";
            case R.drawable.ic_ex_cucumber:
                return "Cucumber";
            case R.drawable.ic_ex_carrot:
                return "Carrot";
        }
        return "";
    }
}