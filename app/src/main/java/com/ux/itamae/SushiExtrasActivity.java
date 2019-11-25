package com.ux.itamae;

import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;

public class SushiExtrasActivity extends AppCompatActivity implements GridExtraAdapter.ExtraClickCallBack {

    private Context context;
    private final String CONTEXT_INTENT = "context";

    ImageView sushiImage;
    ImageView extra1, extra2, extra3;

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
    }

    public void onInExtraClick(View view) {
        switch (view.getId()) {
            case R.id.img_in_top_right:
                extra1.setVisibility(View.INVISIBLE);
                extra1=null;
                break;
            case R.id.img_in_top_left:
                extra2.setVisibility(View.INVISIBLE);
                extra2 = null;
                break;
            case R.id.img_in_bottom:
                extra3.setVisibility(View.INVISIBLE);
                extra3 = null;
                break;
        }
    }

    @Override
    public void onExtraClick(int image) {
        if (extra1 == null) {
            extra1 = findViewById(R.id.img_in_top_right);
            extra1.setImageDrawable(geInExtra(image));
            extra1.setVisibility(View.VISIBLE);
        } else if (extra2 == null) {
            extra2 = findViewById(R.id.img_in_top_left);
            extra2.setImageDrawable(geInExtra(image));
            extra2.setVisibility(View.VISIBLE);
        } else if (extra3 == null) {
            extra3 = findViewById(R.id.img_in_bottom);
            extra3.setImageDrawable(geInExtra(image));
            extra3.setVisibility(View.VISIBLE);
        }
    }

    private Drawable geInExtra(int image) {
        switch (image) {
            case R.drawable.ic_ex_avocado:
                return getDrawable(R.drawable.ic_in_avocado);
            case R.drawable.ic_ex_salmon:
                return getDrawable(R.drawable.ic_in_salmon);
            case R.drawable.ic_ex_redtuna:
                return getDrawable(R.drawable.ic_in_tuna);
        }
        return getDrawable(R.drawable.ic_in_tuna);
    }
}
