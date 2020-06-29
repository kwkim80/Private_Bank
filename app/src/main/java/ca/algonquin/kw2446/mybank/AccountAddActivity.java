package ca.algonquin.kw2446.mybank;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Switch;
import android.widget.Toast;

import ca.algonquin.kw2446.mybank.model.Account;
import ca.algonquin.kw2446.mybank.persistence.BankRepository;
import ca.algonquin.kw2446.mybank.util.AppUtil;

public class AccountAddActivity extends AppCompatActivity {

    EditText etTitle, etNumber, etOwner;
    Button btnCreate, btnCancel;
    BankRepository bankRepository;

    Switch swcIsUse;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_account_add);

        ActionBar actionBar=getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);

        etTitle=findViewById(R.id.etTitle);
        etNumber=findViewById(R.id.etNumber);
        etOwner=findViewById(R.id.etOwner);

        btnCreate=findViewById(R.id.btnCreate);
        btnCancel=findViewById(R.id.btnCancel);
        swcIsUse=findViewById(R.id.swcIsUse);
        bankRepository=new BankRepository(this);

        btnCancel.setOnClickListener(v->{
            Intent intent=new Intent();
            setResult(RESULT_CANCELED,intent);
            this.finish();
        });


    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()){
            case android.R.id.home:
                AccountAddActivity.this.finish();
                break;
            default:
                return super.onOptionsItemSelected(item);
        }
        return super.onOptionsItemSelected(item);
    }

    public void addAccount(View view) {
        String title=etTitle.getText().toString().trim();
        String number=etNumber.getText().toString().trim();
        String owner=etOwner.getText().toString().trim();
        boolean isUse=swcIsUse.isChecked();

        if(title.isEmpty() ||number.isEmpty()){
            Toast.makeText(AccountAddActivity.this, "Please fill required fileds", Toast.LENGTH_SHORT).show();
        }else{
          // Account account=new Account(number,owner,title, AppUtil.getCurrentDateTime(),true);
            Account account=new Account(number,owner,title, AppUtil.getCurrentDateTime(),true);

            bankRepository.insertAccount(account);

//            etTitle.setText("");
//            etOwner.setText("");
//            etNumber.setText("");
//            etTitle.requestFocus();
//            //keyboard
//            InputMethodManager imm= (InputMethodManager) getSystemService(this.INPUT_METHOD_SERVICE);
//            imm.hideSoftInputFromWindow(view.getWindowToken(),0);
//            AppUtil.showSnackbar(view,"Succeed to create a new account");
               Intent intent=new Intent();
                setResult(RESULT_OK,intent);
                AccountAddActivity.this.finish();

        }
    }
}
