package com.ux.itamae;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ImageButton maki_btn = findViewById(R.id.maki_button);
        ImageButton photomaki_btn = findViewById(R.id.photomaki_button);

        final Context context = this;

        maki_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //TODO implement this
                Toast.makeText(context, "order my maki", Toast.LENGTH_SHORT).show();

            }
        });

        photomaki_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //TODO implement this
                Toast.makeText(context, "order my Photo-maki", Toast.LENGTH_SHORT).show();
            }
        });
    }

}
