package com.ux.itamae;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;

public class SushiExtrasActivity extends AppCompatActivity implements GridExtraAdapter.ExtraClickCallBack {

    private Context context;
    private final String CONTEXT_INTENT = "context";

    ImageView sushiImage;
    ImageView extra1;
    ImageView extra2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.sushi_layout);

        context = this;

        if (findViewById(R.id.fragment_container) != null) {

            if (savedInstanceState != null) {
                return;
            }

            ExtrasFragmet extrasFragmet = new ExtrasFragmet();
            extrasFragmet.setSushiExtrasActivity(this);
            extrasFragmet.setArguments(getIntent().getExtras());
            getSupportFragmentManager().beginTransaction().add(R.id.fragment_container, extrasFragmet).commit();
        }

        sushiImage = findViewById(R.id.image_sushi);
        extra1 = findViewById(R.id.img_in_top_right);
        extra2 = findViewById(R.id.img_in_top_left);
    }


    @Override
    public void onExtraClick(ImageView imageView) {

    }
}
