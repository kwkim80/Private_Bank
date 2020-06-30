package ca.algonquin.kw2446.mybank;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import ca.algonquin.kw2446.mybank.databinding.ActivityCompleteBinding;
import ca.algonquin.kw2446.mybank.model.Money;

public class CompleteActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //setContentView(R.layout.activity_complete);
       // ViewDataBinding binding=DataBindingUtil.setContentView(this,R.layout.activity_complete);
        ActivityCompleteBinding binding= DataBindingUtil.setContentView(this,R.layout.activity_complete);
        binding.setLifecycleOwner(this); // to use viewmodel

        Money money=getIntent().getParcelableExtra("money");

        binding.tvTitle.setText(String.format("Complete %s the money",money.isOut()?"to transfer":"to deposit"));
        binding.tvOpponent.setText(money.getOpponent());
        binding.tvAmount.setText(String.format("$ %.2f",Math.abs(money.getAmount())));
        binding.tvMemo.setText(money.getMemo());
        binding.ivResult.setRotation(money.isOut()?180:0);
        binding.btnOk.setOnClickListener(v->{
            Intent intent=new Intent(CompleteActivity.this, MainActivity.class);
            startActivity(intent);
        });

    }
}
