package ca.algonquin.kw2446.mybank;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.LifecycleOwner;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.MenuItem;

import java.util.ArrayList;
import java.util.List;

import ca.algonquin.kw2446.mybank.adapter.MoneyAdapter;
import ca.algonquin.kw2446.mybank.model.Account;
import ca.algonquin.kw2446.mybank.model.AccountBalance;
import ca.algonquin.kw2446.mybank.model.Money;
import ca.algonquin.kw2446.mybank.persistence.BankRepository;
import ca.algonquin.kw2446.mybank.ui.setting.SettingViewModel;
import ca.algonquin.kw2446.mybank.viewmodel.HistoryActivityModel;

public class HistoryActivity extends AppCompatActivity {

    private HistoryActivityModel historyActivityModel;
    BankRepository bankRepository;
    RecyclerView rvList;
    MoneyAdapter adapter;
    ArrayList<Money> list;
    private int page;
    private AccountBalance accountBalance;
    @Override

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_history);

        ActionBar actionBar=getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);

        historyActivityModel =
                ViewModelProviders.of(this).get(HistoryActivityModel.class);

        accountBalance= (AccountBalance) getIntent().getSerializableExtra("account");
        page=getIntent().getIntExtra("page",0);
        list=new ArrayList<>();
        //insertFakeNotes();
        initialRecyclerView();
        bankRepository=new BankRepository(this);
        this.retrieveMonenyList();

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


  //      LiveData<List<Account>> result2=bankRepository.getAccountList();
        historyActivityModel.getList(accountBalance.id, page).observe(this, monies -> {
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
