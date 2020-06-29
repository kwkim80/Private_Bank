package ca.algonquin.kw2446.mybank;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.snackbar.Snackbar;

import java.io.Serializable;
import java.util.Random;

import ca.algonquin.kw2446.mybank.model.Account;
import ca.algonquin.kw2446.mybank.model.Money;
import ca.algonquin.kw2446.mybank.persistence.BankRepository;
import ca.algonquin.kw2446.mybank.util.AppUtil;
import ca.algonquin.kw2446.mybank.util.PreferenceManager;

public class DepositActivity extends AppCompatActivity {

    EditText etAmount, etMemo;
    Spinner spAccount;
    BankRepository bankRepository;
    Button btnDeposit, btnCancel;
    private static final int DEPOSIT_REQUEST_CODE = 10;
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

                checkPwd(deposit);

            }
        }));

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
                DepositActivity.this.finish();
                break;
            default:
                return super.onOptionsItemSelected(item);
        }
        return super.onOptionsItemSelected(item);
    }

    public void checkPwd(Money deposit){

        View pwdView = LayoutInflater.from(this).inflate(R.layout.password, null);

        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(
                DepositActivity.this);
        // set prompts.xml to alertdialog builder
        alertDialogBuilder.setView(pwdView);

        final EditText userInput = (EditText) pwdView
                .findViewById(R.id.editTextDialogUserInput);
        // set dialog message
        alertDialogBuilder
                .setCancelable(false)
                .setPositiveButton("OK",
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                if(userInput.getText().toString().trim().equalsIgnoreCase(PreferenceManager.getString(getApplicationContext(),"Pwd"))){
                                    bankRepository.insertMoney(deposit);
                                    Intent intent=new Intent(DepositActivity.this, CompleteActivity.class);
                                    intent.putExtra("money",deposit);
                                    startActivity(intent);
                                }else{
                                    Toast.makeText(DepositActivity.this, "You password is not mached", Toast.LENGTH_SHORT).show();
                                }
                            }
                        })
                .setNegativeButton("Cancel",
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog,int id) {
                                dialog.cancel();
                            }
                        });
        // create alert dialog
        AlertDialog alertDialog = alertDialogBuilder.create();
        // show it
        alertDialog.show();
    }
}
