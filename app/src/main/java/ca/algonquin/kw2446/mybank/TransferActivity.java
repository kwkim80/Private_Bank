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
import android.widget.Toast;

import com.google.android.material.snackbar.Snackbar;

import ca.algonquin.kw2446.mybank.model.Money;
import ca.algonquin.kw2446.mybank.persistence.BankRepository;
import ca.algonquin.kw2446.mybank.util.AppUtil;
import ca.algonquin.kw2446.mybank.util.PreferenceManager;

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
                checkPwd(transfer);
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
                TransferActivity.this.finish();
                break;
            default:
                return super.onOptionsItemSelected(item);
        }
        return super.onOptionsItemSelected(item);
    }

    public void checkPwd(Money transfer){

        View pwdView = LayoutInflater.from(this).inflate(R.layout.password, null);

        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(
                TransferActivity.this);
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
                                    bankRepository.insertMoney(transfer);
                                    Intent intent=new Intent(TransferActivity.this, CompleteActivity.class);
                                    intent.putExtra("money",transfer);
                                    startActivity(intent);
                                }else{
                                    Toast.makeText(TransferActivity.this, "You password is not mached", Toast.LENGTH_SHORT).show();
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
