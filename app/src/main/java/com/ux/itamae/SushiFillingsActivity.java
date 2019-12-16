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

import static com.ux.itamae.Constants.MAKI_INTENT_KEY;
import static com.ux.itamae.PaymentActivity.ORDER_KEY;

public class SushiFillingsActivity extends AppCompatActivity implements GridFillingAdapter.GridClickCallBack {

    private final String SUSHI_TYPE_KEY = "SUSHI_TYPE";
    private String rollType = "";
    private int extras_counter = 0;

    private ImageView filling1, filling2, filling3;
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

            FillingsFragment fillingsFragment = new FillingsFragment();
            fillingsFragment.setSushiFillingsActivity(this);
            fillingsFragment.setArguments(intent.getExtras());
            getSupportFragmentManager().beginTransaction().add(R.id.fragment_container, fillingsFragment).commit();
        }
    }

    public void updateSushiTypeImage(String type) {
        if (type.equals(MAKI_INTENT_KEY)) {
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

    public void OnInnerFillingClick(View view) {
        updateCounter(false);
        switch (view.getId()) {
            case R.id.img_in_top_right:
                filling1.setVisibility(View.INVISIBLE);
                filling1 = null;
                break;
            case R.id.img_in_top_left:
                filling2.setVisibility(View.INVISIBLE);
                filling2 = null;
                break;
            case R.id.img_in_bottom:
                filling3.setVisibility(View.INVISIBLE);
                filling3 = null;
                break;
        }
    }

    @Override
    public void onFillingClick(int image) {
        updateCounter(true);
        if (filling1 == null) {
            filling1 = findViewById(R.id.img_in_top_right);
            addFilling(filling1, image);
            myRoll.setRoll_filling1(image);
        } else if (filling2 == null) {
            filling2 = findViewById(R.id.img_in_top_left);
            addFilling(filling2, image);
            myRoll.setRoll_filling2(image);
        } else if (filling3 == null) {
            filling3 = findViewById(R.id.img_in_bottom);
            addFilling(filling3, image);
            myRoll.setRoll_filling3(image);
        }
    }

    private void addFilling(ImageView filling, int image) {
        filling.setImageDrawable(getInFilling(image));
        filling.setVisibility(View.VISIBLE);
    }

    /**
     * Gets an external image of the filling (the menu image), and return the image for the same
     * filling in the sushi.
     * @param image - an external image of the filling (the menu image).
     * @return a drawable for the image of the inner filling - in the sushi.
     */
    private Drawable getInFilling(int image) {
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
