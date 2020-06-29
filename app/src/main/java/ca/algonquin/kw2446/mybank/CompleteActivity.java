package ca.algonquin.kw2446.mybank;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

import ca.algonquin.kw2446.mybank.model.Money;

public class CompleteActivity extends AppCompatActivity {

    TextView tvOpponent, tvAmount, tvMemo, tvTitle;
    Button btnOk, btnHistory;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_complete);

        Money money=getIntent().getParcelableExtra("money");

        tvTitle=findViewById(R.id.tvTitle);
        tvOpponent=findViewById(R.id.tvOpponent);
        tvAmount=findViewById(R.id.tvAmount);
        tvMemo=findViewById(R.id.tvMemo);
        btnOk=findViewById(R.id.btnOk);
        //btnHistory=findViewById(R.id.btnHistory);

        tvTitle.setText(String.format("Complete %s the money",money.isOut()?"to transfer":"to deposit"));
        tvOpponent.setText(money.getOpponent());
        tvAmount.setText(String.format("$ %.2f",Math.abs(money.getAmount())));
        tvMemo.setText(money.getMemo());

        btnOk.setOnClickListener(v->{
            Intent intent=new Intent(CompleteActivity.this, MainActivity.class);
            startActivity(intent);
        });

//        btnHistory.setOnClickListener(v->{
//            Intent intent=new Intent(CompleteActivity.this, HistoryPagerActivity.class);
//            startActivity(intent);
//        });
    }
}
