package ca.algonquin.kw2446.mybank;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.material.snackbar.Snackbar;

import ca.algonquin.kw2446.mybank.model.Money;
import ca.algonquin.kw2446.mybank.persistence.BankRepository;
import ca.algonquin.kw2446.mybank.util.AppUtil;

public class TransferActivity extends AppCompatActivity {

    EditText etAmount, etMemo, etOpponent;
    BankRepository bankRepository;
    Button btnTransfer, btnCancel;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_transfer);

        ActionBar actionBar=getSupportActionBar();
//        //actionBar.setIcon(R.drawable.logo);
//        actionBar.setTitle(" Vocabulary");
//        actionBar.setDisplayShowHomeEnabled(true);
//        actionBar.setDisplayUseLogoEnabled(true);
        actionBar.setDisplayHomeAsUpEnabled(true);

        bankRepository=new BankRepository(this);

        etAmount=findViewById(R.id.etAmount);
        etMemo=findViewById(R.id.etMemo);
        etOpponent=findViewById(R.id.etOppopnent);
        btnTransfer =findViewById(R.id.btnTransfer);
        btnCancel=findViewById(R.id.btnCancel);

        btnTransfer.setOnClickListener((v -> {
            double amount=Double.parseDouble(etAmount.getText().toString().trim());
            String memo=etMemo.getText().toString().trim();
            int accountId=1;

            if(amount<=0 ||memo.isEmpty()){
                Toast.makeText(TransferActivity.this, "Please fill required fileds", Toast.LENGTH_SHORT).show();
            }else{
                Money transfer=new Money(accountId,"Transfer", amount*-1,true,memo, AppUtil.getCurrentDateTime());
                bankRepository.insertMoney(transfer);

                Snackbar.make(v, "Succeed to transfer your money", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        }));
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()){
            case android.R.id.home:
                TransferActivity.this.finish();
                break;
            default:
                return super.onOptionsItemSelected(item);
        }
        return super.onOptionsItemSelected(item);
    }
}
