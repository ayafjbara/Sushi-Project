package com.ux.itamae;

import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import java.util.HashMap;
import java.util.Map;

import static com.ux.itamae.PaymentActivity.ORDER_KEY;

public class SushiExtrasActivity extends AppCompatActivity implements GridExtraAdapter.ExtraClickCallBack {

    private Context context;
    private final String SUSHI_TYPE_KEY = "SUSHI_TYPE";
    private String rollType = "";
    private int extras_counter = 0;

    private ImageView extra1, extra2, extra3;
    private ImageView sushi;
    private HashMap<SushiRoll, Integer> rollOrder;
    private SushiRoll myRoll;
    private TextView rollToCheckoutButton;
    private ImageView rollToMenuBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.sushi_layout);

        rollToMenuBtn = findViewById(R.id.add_roll_btn);
        rollToCheckoutButton = findViewById(R.id.checkout_btn);
        sushi = findViewById(R.id.image_sushi);

        rollToMenuBtn.setEnabled(false);
        rollToCheckoutButton.setEnabled(false);

        context = this;
        Intent intent = getIntent();
        rollType = intent.getStringExtra(SUSHI_TYPE_KEY);
        rollOrder = (HashMap) intent.getSerializableExtra(ORDER_KEY);
        myRoll = new SushiRoll(rollType);

        // update UI
        updateSushiTypeImage(rollType);

        if (findViewById(R.id.fragment_container) != null) {

            if (savedInstanceState != null) {
                return;
            }

            ExtrasFragment extrasFragment = new ExtrasFragment();
            extrasFragment.setSushiExtrasActivity(this);
            extrasFragment.setArguments(intent.getExtras());
            getSupportFragmentManager().beginTransaction().add(R.id.fragment_container, extrasFragment).commit();
        }
    }

    public void updateSushiTypeImage(String type) {
        if (type.equals("Maki")) {
            sushi.setImageDrawable(getDrawable(R.drawable.ic_sushi));
        } else {
            sushi.setImageDrawable(getDrawable(R.drawable.ic_sushi_io));
        }
    }


    public void onFinishRollClick(View view) {

        addRollToOrder();
        if (view.getId() == R.id.checkout_btn) {
            Intent intent = new Intent(this, PaymentActivity.class);
            intent.putExtra(ORDER_KEY, rollOrder);
            startActivity(intent);
        } else {
            Intent intent = new Intent(this, MainActivity.class);
            intent.putExtra(ORDER_KEY, rollOrder);
            setResult(RESULT_OK, intent);
            finish();
        }
    }

    private void addRollToOrder() {
        if (rollOrder != null) {
            int count = 1;
            SushiRoll eqRoll = myRoll;
            for (Map.Entry<SushiRoll, Integer> sushiRoll : rollOrder.entrySet()) {
                if (sushiRoll.getKey().equals(myRoll)) {
                    eqRoll = sushiRoll.getKey();
                    count = sushiRoll.getValue() + 1;
                    break;
                }
            }
            rollOrder.put(eqRoll, count);
        } else {
            rollOrder = new HashMap<>();
            rollOrder.put(myRoll, 1);
        }
    }


    private void updateCounter(Boolean isIncrease) {
        if (isIncrease && (extras_counter < 3)) {
            extras_counter++;
        } else if (!isIncrease && (extras_counter > 0)) {
            extras_counter--;
        }

        ConstraintLayout finishRollLay = findViewById(R.id.finishRollLay);
        if (extras_counter == 3) {
            // set 'go to payment' button visible
            finishRollLay.setAlpha(1f);
            rollToMenuBtn.setEnabled(true);
            rollToCheckoutButton.setEnabled(true);
        } else {
            // set 'go to payment' button invisible
            finishRollLay.setAlpha(0.5f);
            rollToMenuBtn.setEnabled(false);
            rollToCheckoutButton.setEnabled(false);
        }
    }

    public void onInExtraClick(View view) {
        updateCounter(false);
        switch (view.getId()) {
            case R.id.img_in_top_right:
                extra1.setVisibility(View.INVISIBLE);
                extra1 = null;
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
            addExtra(extra1, image);
            myRoll.setExtra1(image);
        } else if (extra2 == null) {
            extra2 = findViewById(R.id.img_in_top_left);
            addExtra(extra2, image);
            myRoll.setExtra2(image);
        } else if (extra3 == null) {
            extra3 = findViewById(R.id.img_in_bottom);
            addExtra(extra3, image);
            myRoll.setExtra3(image);
        }
    }

    private void addExtra(ImageView extra, int image) {
        extra.setImageDrawable(getInExtra(image));
        extra.setVisibility(View.VISIBLE);
    }

    private Drawable getInExtra(int image) {
        switch (image) {
            case R.drawable.ic_ex_avocado:
                return getDrawable(R.drawable.ic_in_avocado);
            case R.drawable.ic_ex_salmon:
                return getDrawable(R.drawable.ic_in_salmon);
            case R.drawable.ic_ex_redtuna:
                return getDrawable(R.drawable.ic_in_tuna);
            case R.drawable.ic_ex_cucumber:
                return getDrawable(R.drawable.ic_in_cucumber);
            case R.drawable.ic_ex_carrot:
                return getDrawable(R.drawable.ic_in_carrot);
        }
        return getDrawable(R.drawable.ic_in_tuna);
    }
}
