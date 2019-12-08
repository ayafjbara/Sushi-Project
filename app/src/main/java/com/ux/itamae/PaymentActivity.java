package com.ux.itamae;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class PaymentActivity extends AppCompatActivity {

    public static final String ORDER_KEY = "order";
    private TextView orderContent;
    private Button orderBtn;
    private Button newOrderBtn;
    private Intent intent;
    private String rollOrder;
    private ConstraintLayout orderLayout;
    private ConstraintLayout finishOrderLayout;

    private RecyclerView rollRecyclerView;

    private HashMap<SushiRoll, Integer> sushiRolls;
    private RollRecyclerUtils.RollAdapter adapter = new RollRecyclerUtils.RollAdapter();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_payment);

        // bind views
        orderBtn = findViewById(R.id.order_btn);
        finishOrderLayout = findViewById(R.id.finish_order_layout);
        newOrderBtn = findViewById(R.id.new_order_btn);
        rollRecyclerView = findViewById(R.id.roll_recycler);

        final Context context = this;

        // handle intent
        intent = getIntent();
        handleIntent(intent);

        // set up UI
        setUpOrderBtn();
        setUpToolbar();
        setUpNewOrderBtn(context);
        setUpRecycler();
    }

    private void setUpRecycler() {
        rollRecyclerView.setLayoutManager(new LinearLayoutManager(
                this, LinearLayoutManager.VERTICAL, false));
        adapter.setUpAmounts(sushiRolls);
        rollRecyclerView.setAdapter(adapter);
//        adapter.callback = this;  // uncomment and add delete roll option
        adapter.submitList(new ArrayList<>(sushiRolls.keySet()));
    }

    private void setUpNewOrderBtn(final Context context) {
        newOrderBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                rollOrder = "";
                Intent backIntent = new Intent(context, MainActivity.class);
                startActivity(backIntent);
            }
        });
    }

    private void setUpToolbar() {
        // set up toolbar and back arrow
        Toolbar mToolbar = findViewById(R.id.toolbar);
        setSupportActionBar(mToolbar);
        setSupportActionBar(mToolbar);
        ActionBar actionBar = getSupportActionBar();
        actionBar.setTitle(getString(R.string.order_sum_title));
        actionBar.setDisplayHomeAsUpEnabled(true);

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {// go to back activity from here
            Intent backIntent = new Intent(this, MainActivity.class);
            backIntent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK);
            if (rollOrder != null && !rollOrder.isEmpty()) {
                backIntent.putExtra(ORDER_KEY, rollOrder);
            }
            startActivity(backIntent);
            finish();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    private void setUpOrderBtn() {
        orderBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                orderLayout.setVisibility(View.GONE);
                finishOrderLayout.setVisibility(View.VISIBLE);
                rollOrder = "";
            }
        });
    }

    private void handleIntent(Intent intent) {
//        rollOrder = intent.getStringExtra(ORDER_KEY);
        sushiRolls = (HashMap<SushiRoll, Integer>) intent.getSerializableExtra(ORDER_KEY);
    }

//    private void updateOrderText(String order) {
//        orderContent.setText(order);
//    }

}
