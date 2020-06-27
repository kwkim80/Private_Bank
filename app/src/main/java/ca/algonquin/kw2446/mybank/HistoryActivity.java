package ca.algonquin.kw2446.mybank;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.LiveData;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.MenuItem;

import java.util.ArrayList;
import java.util.List;

import ca.algonquin.kw2446.mybank.adapter.MoneyAdapter;
import ca.algonquin.kw2446.mybank.model.Account;
import ca.algonquin.kw2446.mybank.model.Money;
import ca.algonquin.kw2446.mybank.persistence.BankRepository;

public class HistoryActivity extends AppCompatActivity {

    BankRepository bankRepository;
    RecyclerView rvList;
    MoneyAdapter adapter;
    ArrayList<Money> list;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_history);

        ActionBar actionBar=getSupportActionBar();
//        //actionBar.setIcon(R.drawable.logo);
//        actionBar.setTitle(" Vocabulary");
//        actionBar.setDisplayShowHomeEnabled(true);
//        actionBar.setDisplayUseLogoEnabled(true);
        actionBar.setDisplayHomeAsUpEnabled(true);

        list=new ArrayList<>();
        //insertFakeNotes();

        initialRecyclerView();
        bankRepository=new BankRepository(this);

        this.retrieveMonenyList();
        retrieveMonenyList();



    }

    public void initialRecyclerView(){
       rvList=findViewById(R.id.rvList);
       rvList.setHasFixedSize(true);
        RecyclerView.LayoutManager layoutManager=new LinearLayoutManager(this);
        rvList.setLayoutManager(layoutManager);
        adapter=new MoneyAdapter(this, list);
        rvList.setAdapter(adapter);

    }

    public void retrieveMonenyList(){
       LiveData<List<Money>> result=bankRepository.getMoneyList();
  //      LiveData<List<Account>> result2=bankRepository.getAccountList();
       result.observe(this, monies -> {
            if(list.size() > 0){
                list.clear();
            }
            if(monies != null){
                list.addAll(monies);
            }
            adapter.notifyDataSetChanged();
        });
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()){
            case android.R.id.home:
                HistoryActivity.this.finish();
                break;
            default:
                return super.onOptionsItemSelected(item);
        }
        return super.onOptionsItemSelected(item);
    }
}
