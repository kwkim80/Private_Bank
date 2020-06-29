package ca.algonquin.kw2446.mybank;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

import ca.algonquin.kw2446.mybank.adapter.AccountAdapter;
import ca.algonquin.kw2446.mybank.model.AccountBalance;
import ca.algonquin.kw2446.mybank.persistence.BankRepository;
import ca.algonquin.kw2446.mybank.viewmodel.AccountListActivityModel;
import ca.algonquin.kw2446.mybank.viewmodel.HistoryActivityModel;

public class AccountListActivity extends AppCompatActivity {

    private static final int ACCOUNTADD_REQUEST_CODE=101;
    ArrayList<AccountBalance> list;
    ListView lvAccounts;
    AccountAdapter adapter;
    private AccountListActivityModel model;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_account_list);
        list=new ArrayList<>();
        model=ViewModelProviders.of(this).get(AccountListActivityModel.class);
        lvAccounts=findViewById(R.id.lvAccounts);
        adapter=new AccountAdapter(this, list);
        lvAccounts.setAdapter(adapter);

        retrieveAccountsList();


    }

    public void retrieveAccountsList(){
        model.getAccountBalances().observe(this, monies -> {
            if(list.size() > 0)list.clear();
            if(monies != null)list.addAll(monies);

            adapter.notifyDataSetChanged();
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.add,menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()){
            case R.id.add:
                if(list.size()<10){
                    Intent intent=new Intent(AccountListActivity.this,AccountAddActivity.class);
                    startActivityForResult(intent,ACCOUNTADD_REQUEST_CODE);
                }else{
                    Toast.makeText(this, "You have maximum Account.", Toast.LENGTH_SHORT).show();
                }
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if(requestCode==ACCOUNTADD_REQUEST_CODE && resultCode==RESULT_OK){
            Toast.makeText(this, "Succeed to add a new account", Toast.LENGTH_SHORT).show();
        }
    }
}
