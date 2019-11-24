package com.ux.itamae;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

public class SushiExtrasActivity extends AppCompatActivity {

    private Context context;
    private final String CONTEXT_INTENT = "context";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.sushi_layout);

        context = this;

        if (findViewById(R.id.fragment_container) != null) {

            if (savedInstanceState != null) {
                return;
            }

            ExtrasFragmet firstFragment = new ExtrasFragmet();

            firstFragment.setArguments(getIntent().getExtras());

            getSupportFragmentManager().beginTransaction().add(R.id.fragment_container, firstFragment).commit();
        }
    }
}
