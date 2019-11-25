package com.ux.itamae;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        LinearLayout maki = findViewById(R.id.maki_frame);
        LinearLayout futomaki = findViewById(R.id.futomaki_frame);

        final Context context = this;
        final Intent intent = new Intent(context, SushiExtrasActivity.class);

        maki.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                Toast.makeText(context, "order my maki", Toast.LENGTH_SHORT).show();
                intent.putExtra("SUSHI_TYPE", "Maki");
                startActivity(intent);
            }
        });

        futomaki.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                Toast.makeText(context, "order my Futomaki", Toast.LENGTH_SHORT).show();
                intent.putExtra("SUSHI_TYPE", "Futomaki");
                startActivity(intent);
            }
        });
    }
}
