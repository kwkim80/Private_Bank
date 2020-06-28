package ca.algonquin.kw2446.mybank;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import ca.algonquin.kw2446.mybank.persistence.BankRepository;
import ca.algonquin.kw2446.mybank.util.AppUtil;
import ca.algonquin.kw2446.mybank.util.PreferenceManager;

public class ProfileActivity extends AppCompatActivity {

    TextView tvName, tvEmail;
    EditText etName, etPwd, etNewPwd, etEmail;
    String name, email, pwd,newPwd;
    Button btnUpdate, btnCancel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pofile);

        ActionBar actionBar=getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);



        tvName=findViewById(R.id.tvName);
        tvEmail=findViewById(R.id.tvEmail);

        etName=findViewById(R.id.etName);
        etPwd=findViewById(R.id.etPwd);
        etNewPwd=findViewById(R.id.etNewPwd);
        etEmail=findViewById(R.id.etEmail);

        name= PreferenceManager.getString(this,"Name");
        pwd=PreferenceManager.getString(getApplicationContext(), "Pwd");
        email=PreferenceManager.getString(getApplicationContext(), "Email");

        tvName.setText(name);
        tvEmail.setText(email);

        btnUpdate=findViewById(R.id.btnUpdate);
        btnCancel=findViewById(R.id.btnCancel);

        etName.setText(name);
        etEmail.setText(email);
        if(pwd.equalsIgnoreCase("0000")){
            etPwd.setHint("                         (Initial Password is 0000)");
        }



        btnUpdate.setOnClickListener(v->{
            name=etName.getText().toString().trim();

            newPwd=etNewPwd.getText().toString().trim();
            email=etEmail.getText().toString().trim();
            String chkPwd=etPwd.getText().toString().trim();

            if(name.isEmpty()|| email.isEmpty() ||chkPwd.isEmpty() ||email.isEmpty()){
                Toast.makeText(this, "Please fill all fields", Toast.LENGTH_SHORT).show();
            }else{
                if(pwd.equalsIgnoreCase(chkPwd)){
                     PreferenceManager.setValue(getApplicationContext(),"Name",name);
                    PreferenceManager.setValue(getApplicationContext(),"Pwd",newPwd);
                    PreferenceManager.setValue(getApplicationContext(),"Email",email);
                    Intent intent=new Intent();
                    setResult(RESULT_OK,intent);
                    ProfileActivity.this.finish();
                }
            }

        });

        btnCancel.setOnClickListener(v->this.finish());
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()){
            case android.R.id.home:
                ProfileActivity.this.finish();
                break;
            default:
                return super.onOptionsItemSelected(item);
        }
        return super.onOptionsItemSelected(item);
    }


}
