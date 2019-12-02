package com.ux.itamae;

import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import androidx.appcompat.app.AppCompatActivity;

import static com.ux.itamae.PaymentActivity.ORDER_KEY;

public class SushiExtrasActivity extends AppCompatActivity implements GridExtraAdapter.ExtraClickCallBack {

    private Context context;
    private final String CONTEXT_INTENT = "context";
    private final String SUSHI_TYPE_KEY = "SUSHI_TYPE";
    private final int ROLL_FINISHED = 666;

    ImageView sushiImage;
    ImageView extra1, extra2, extra3;
    private String rollType = "";
    private String rollOrder;
    int extras_counter = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.sushi_layout);

        context = this;
        Intent intent = getIntent();
        rollType = intent.getStringExtra(SUSHI_TYPE_KEY);
        rollOrder = handlePreviousOrder(intent);

        if (findViewById(R.id.fragment_container) != null) {

            if (savedInstanceState != null) {
                return;
            }

            ExtrasFragmet extrasFragmet = new ExtrasFragmet();
            extrasFragmet.setSushiExtrasActivity(this);
            extrasFragmet.setArguments(intent.getExtras());
            getSupportFragmentManager().beginTransaction().add(R.id.fragment_container, extrasFragmet).commit();
        }

        sushiImage = findViewById(R.id.image_sushi);
    }

    private String handlePreviousOrder(Intent intent) {
        String order = intent.getStringExtra(ORDER_KEY);
        if (order != null) {
            return order;
        } else {
            return "";
        }
    }

    private String rollToString(int[] values){
        String roll = "" + rollType + ":\n";
        for (int value : values) {
            switch (value) {
                case R.drawable.ic_ex_avocado:
                    roll += "\t1x Avocado\n";
                    break;
                case R.drawable.ic_ex_salmon:
                    roll += "\t1x Salmon\n";
                    break;
                case R.drawable.ic_ex_redtuna:
                    roll += "\t1x Tuna\n";
                    break;
            }
        }
        return roll;
    }

    public void onFinishRollClick(View view) {
        // TODO insert roll to basket
        int[] tags = new int[3];
        tags[0] = (int) extra1.getTag();
        tags[1] = (int) extra2.getTag();
        tags[2] = (int) extra3.getTag();

        String currentRoll = rollToString(tags);
        String updatedOrder = rollOrder + currentRoll;

        // TODO move to Main Activity screen
        Intent intent = new Intent(this, MainActivity.class);
        intent.putExtra(ORDER_KEY, updatedOrder);
//        startActivityForResult(intent, ROLL_FINISHED);
        startActivity(intent);
    }

    private void updateCounter(Boolean isIncrease) {
        if (isIncrease && (extras_counter < 3)) {
            extras_counter++;

        } else if (!isIncrease && (extras_counter > 0)) {
            extras_counter--;
        }

        if (extras_counter == 3) {
            // TODO instead of 3 should be variable derived from roll type
            // set 'go to payment' button visible
            RelativeLayout finishRollLay = findViewById(R.id.finishRollLay);
            finishRollLay.setVisibility(View.VISIBLE);
        } else {
            // set 'go to payment' button invisible
            RelativeLayout finishRollLay = findViewById(R.id.finishRollLay);
            finishRollLay.setVisibility(View.INVISIBLE);
        }
    }

    public void onInExtraClick(View view) {
        updateCounter(false);
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
        updateCounter(true);
        if (extra1 == null) {
            extra1 = findViewById(R.id.img_in_top_right);
            extra1.setImageDrawable(getInExtra(image));
            extra1.setTag(image);
            extra1.setVisibility(View.VISIBLE);

        } else if (extra2 == null) {
            extra2 = findViewById(R.id.img_in_top_left);
            extra2.setImageDrawable(getInExtra(image));
            extra2.setTag(image);
            extra2.setVisibility(View.VISIBLE);

        } else if (extra3 == null) {
            extra3 = findViewById(R.id.img_in_bottom);
            extra3.setImageDrawable(getInExtra(image));
            extra3.setTag(image);
            extra3.setVisibility(View.VISIBLE);
        }
    }

    private Drawable getInExtra(int image) {
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
