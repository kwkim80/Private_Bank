package ca.algonquin.kw2446.mybank;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.material.snackbar.Snackbar;

import ca.algonquin.kw2446.mybank.model.Account;
import ca.algonquin.kw2446.mybank.persistence.BankRepository;
import ca.algonquin.kw2446.mybank.persistence.MoneyDB;
import ca.algonquin.kw2446.mybank.util.AppUtil;

public class AccountActivity extends AppCompatActivity {

    EditText etTitle, etNumber, etOwner;
    Button btnCreate, btnCancel;
    BankRepository bankRepository;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_account);

        ActionBar actionBar=getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);

        etTitle=findViewById(R.id.etTitle);
        etNumber=findViewById(R.id.etNumber);
        etOwner=findViewById(R.id.etOwner);

        btnCreate=findViewById(R.id.btnCreate);
        btnCancel=findViewById(R.id.btnCancel);

        bankRepository=new BankRepository(this);

        btnCreate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String title=etTitle.getText().toString().trim();
                String number=etNumber.getText().toString().trim();
                String owner=etOwner.getText().toString().trim();

                if(title.isEmpty() ||number.isEmpty()){
                    Toast.makeText(AccountActivity.this, "Please fill required fileds", Toast.LENGTH_SHORT).show();
                }else{
                    Account account=new Account(number,owner,title, AppUtil.getCurrentDateTime());

                    bankRepository.insertAccount(account);

                    Snackbar.make(v, "Succeed to create Account", Snackbar.LENGTH_LONG)
                            .setAction("Action", null).show();
                }
            }
        });


    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()){
            case android.R.id.home:
                AccountActivity.this.finish();
                break;
            default:
                return super.onOptionsItemSelected(item);
        }
        return super.onOptionsItemSelected(item);
    }
}
