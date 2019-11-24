package com.ux.itamae;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;

// todo: (IN SUSHI SCREEN)
// Intent intent = new Intent(mContext, PaymentActivity.class);
// intent.putExtra(ORDER_KEY, "1x Maki:\n1x Salmon\n1x Avocado\n1x Cucumber");
// startActivity(intent);
/////////////////////////////////////////////////

public class PaymentActivity extends AppCompatActivity {

    public static final String ORDER_KEY = "order";
    private TextView orderContent;
    private Button orderBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_payment);

        // bind views
        orderContent = findViewById(R.id.order_content);
        orderBtn = findViewById(R.id.order_btn);

        // set up toolbar and back arrow
        Toolbar mToolbar = findViewById(R.id.toolbar);
        setSupportActionBar(mToolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setTitle("Order");

        // set up UI
        setUpOrderBtn();

        // handle intent
        Intent intent = getIntent();
        handleIntent(intent);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {// go to back activity from here
            Intent intent = new Intent(this, MainActivity.class);
            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK);
            startActivity(intent);
            finish();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    private void setUpOrderBtn() {
        orderBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // do something on place order click todo
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
