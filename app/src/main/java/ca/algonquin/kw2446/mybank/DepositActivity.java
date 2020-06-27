package ca.algonquin.kw2446.mybank;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.snackbar.Snackbar;

import ca.algonquin.kw2446.mybank.model.Account;
import ca.algonquin.kw2446.mybank.model.Money;
import ca.algonquin.kw2446.mybank.persistence.BankRepository;
import ca.algonquin.kw2446.mybank.util.AppUtil;

public class DepositActivity extends AppCompatActivity {

    EditText etAmount, etMemo;
    Spinner spAccount;
    BankRepository bankRepository;
    Button btnDeposit, btnCancel;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_deposit);

       ActionBar actionBar=getSupportActionBar();
//        //actionBar.setIcon(R.drawable.logo);
//        actionBar.setTitle(" Vocabulary");
//        actionBar.setDisplayShowHomeEnabled(true);
//        actionBar.setDisplayUseLogoEnabled(true);
        actionBar.setDisplayHomeAsUpEnabled(true);

        bankRepository=new BankRepository(this);

        spAccount=findViewById(R.id.spAccount);
        etAmount=findViewById(R.id.etAmount);
        etMemo=findViewById(R.id.etMemo);
        btnDeposit=findViewById(R.id.btnDeposit);
        btnCancel=findViewById(R.id.btnCancel);

        btnDeposit.setOnClickListener((v -> {
            double amount=Double.parseDouble(etAmount.getText().toString().trim());
            String memo=etMemo.getText().toString().trim();
            int accountId=1;

            if(amount<=0 ||memo.isEmpty()){
                Toast.makeText(DepositActivity.this, "Please fill required fileds", Toast.LENGTH_SHORT).show();
            }else{
                Money deposit=new Money(accountId,"Deposit", amount,false,memo, AppUtil.getCurrentDateTime());

                bankRepository.insertMoney(deposit);

                Snackbar.make(v, "Succeed to create Account", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        }));
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()){
            case android.R.id.home:
                DepositActivity.this.finish();
                break;
            default:
                return super.onOptionsItemSelected(item);
        }
        return super.onOptionsItemSelected(item);
    }
}
