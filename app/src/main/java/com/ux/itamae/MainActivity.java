package com.ux.itamae;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;

public class MainActivity extends AppCompatActivity {
    public final String SUSHI_TYPE_KEY = "SUSHI_TYPE";
    final String ORDER_KEY = "order";

    private LinearLayout maki;
    private LinearLayout futomaki;
    private LinearLayout orderFrameBtn;
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
        final String orderDetails = orderIntent.getStringExtra(ORDER_KEY);
//        if (orderDetails != null && !orderDetails.isEmpty()){
//            orderFrameBtn.setVisibility(View.VISIBLE);
//        }

        maki.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                Toast.makeText(context, "order my maki", Toast.LENGTH_SHORT).show();
                makeRollIntent.putExtra(SUSHI_TYPE_KEY, "Maki");
                makeRollIntent.putExtra(ORDER_KEY, orderDetails);
                startActivity(makeRollIntent);
            }
        });

        futomaki.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                Toast.makeText(context, "order my Futomaki", Toast.LENGTH_SHORT).show();
                makeRollIntent.putExtra(SUSHI_TYPE_KEY, "Futomaki");
                makeRollIntent.putExtra(ORDER_KEY, orderDetails);
                startActivity(makeRollIntent);
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

}
