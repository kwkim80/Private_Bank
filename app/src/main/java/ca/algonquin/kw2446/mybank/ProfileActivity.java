package ca.algonquin.kw2446.mybank;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;
import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.ViewModelProviders;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import ca.algonquin.kw2446.mybank.databinding.ActivityPofileBinding;
import ca.algonquin.kw2446.mybank.persistence.BankRepository;
import ca.algonquin.kw2446.mybank.util.AppUtil;
import ca.algonquin.kw2446.mybank.util.PreferenceManager;
import ca.algonquin.kw2446.mybank.viewmodel.ProfileVM;

public class ProfileActivity extends AppCompatActivity {


    String name, email, pwd,newPwd;
    ProfileVM vm;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
       // setContentView(R.layout.activity_pofile);
        ActivityPofileBinding binding =DataBindingUtil.setContentView(this,R.layout.activity_pofile);

        ActionBar actionBar=getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);

        vm= new ViewModelProvider(this).get(ProfileVM.class); //ViewModelProviders.of(this).get(ProfileVM.class);
        binding.setViewModel(vm);
        binding.setLifecycleOwner(this);

        binding.tvName.setText(vm.getName());
        binding.tvEmail.setText(vm.getEmail());

        if(vm.getOriPwd().equalsIgnoreCase("0000")){
            binding.etPwd.setHint("                         (Initial Password is 0000)");
        }

        binding.btnUpdate.setOnClickListener((v)->{
            if(vm.setProfile()){
                Intent intent=new Intent();
                setResult(RESULT_OK,intent);
                ProfileActivity.this.finish();
            }else Toast.makeText(this, "The password is not matched!", Toast.LENGTH_SHORT).show();
        });
        binding.btnCancel.setOnClickListener(v->this.finish());
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()){
            case android.R.id.home:
                ProfileActivity.this.finish();
                break;
        }
        return super.onOptionsItemSelected(item);
    }


}
