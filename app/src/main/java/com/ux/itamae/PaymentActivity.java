package com.ux.itamae;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;

public class PaymentActivity extends AppCompatActivity {

    public static final String ORDER_KEY = "order";
    private TextView orderContent;
    private Button orderBtn;
    private Intent intent;
    private String rollOrder;
    private ConstraintLayout orderLayout;
    private ConstraintLayout finishOrderLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_payment);

        // bind views
        orderContent = findViewById(R.id.order_content);
        orderBtn = findViewById(R.id.order_btn);
        orderLayout = findViewById(R.id.order_layout);
        finishOrderLayout = findViewById(R.id.finish_order_layout);

        // set up toolbar and back arrow
        Toolbar mToolbar = findViewById(R.id.toolbar);
        setSupportActionBar(mToolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setTitle("Order");

        // set up UI
        setUpOrderBtn();

        // handle intent
        intent = getIntent();
        handleIntent(intent);

        Toolbar myChildToolbar =
                (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(myChildToolbar);
        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);

        rollOrder = intent.getStringExtra(ORDER_KEY);
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
            }
        });
    }

    private void handleIntent(Intent intent) {
        String order = intent.getStringExtra(ORDER_KEY);
        updateUI(order);
    }

    private void updateUI(String order) {
        orderContent.setText(order);
    }

}
