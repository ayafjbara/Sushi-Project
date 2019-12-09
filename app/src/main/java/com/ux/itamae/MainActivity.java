package com.ux.itamae;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;

import java.util.HashMap;

public class MainActivity extends AppCompatActivity {
    public final String SUSHI_TYPE_KEY = "SUSHI_TYPE";
    private int NEW_ROLL_REQUEST = 1;
    private String ORDER_KEY = "order";

    private LinearLayout maki;
    private LinearLayout futomaki;
    private LinearLayout orderFrameBtn;
    private HashMap orderDetails;

//    private Context context;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        maki = findViewById(R.id.maki_frame);
        futomaki = findViewById(R.id.futomaki_frame);
        orderFrameBtn = findViewById(R.id.order_frame_btn);

        final Context context = this;
        final Intent makeRollIntent = new Intent(context, SushiExtrasActivity.class);
        // get current order details
        Intent orderIntent = getIntent();
        orderDetails = (HashMap) orderIntent.getSerializableExtra(ORDER_KEY);
        if (orderDetails != null && !orderDetails.isEmpty()) {
            orderFrameBtn.setVisibility(View.VISIBLE);
        }

        maki.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                makeRollIntent.putExtra(SUSHI_TYPE_KEY, "Maki");
                makeRollIntent.putExtra(ORDER_KEY, orderDetails);
                startActivityForResult(makeRollIntent, NEW_ROLL_REQUEST);
            }
        });

        futomaki.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                makeRollIntent.putExtra(SUSHI_TYPE_KEY, "Maki I/O");
                makeRollIntent.putExtra(ORDER_KEY, orderDetails);
                startActivityForResult(makeRollIntent, NEW_ROLL_REQUEST);
            }
        });

        orderFrameBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent paymentIntent = new Intent(context, PaymentActivity.class);
                paymentIntent.putExtra(ORDER_KEY, orderDetails);
                startActivity(paymentIntent);
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        if (requestCode == NEW_ROLL_REQUEST && resultCode == RESULT_OK) {
            orderDetails = (HashMap) data.getSerializableExtra(ORDER_KEY);
            orderFrameBtn.setVisibility(View.VISIBLE);
        }
    }
}
