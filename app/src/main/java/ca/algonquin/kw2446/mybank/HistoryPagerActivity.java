package ca.algonquin.kw2446.mybank;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModelProviders;
import androidx.viewpager2.widget.ViewPager2;

import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;

import com.google.android.material.tabs.TabLayout;
import com.google.android.material.tabs.TabLayoutMediator;

import java.util.ArrayList;
import java.util.List;

import ca.algonquin.kw2446.mybank.adapter.MyPagerAdapter;
import ca.algonquin.kw2446.mybank.fragment.HistoryPagerFragment;
import ca.algonquin.kw2446.mybank.model.Money;
import ca.algonquin.kw2446.mybank.persistence.BankRepository;
import ca.algonquin.kw2446.mybank.viewmodel.HistoryActivityModel;
import ca.algonquin.kw2446.mybank.viewmodel.HistoryPagerActivityModel;

public class HistoryPagerActivity extends AppCompatActivity {

    private HistoryPagerActivityModel pagerActivityModel;
    private ArrayList<Fragment> mData;
    BankRepository bankRepository;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_history_pager);

        pagerActivityModel=    ViewModelProviders.of(this).get(HistoryPagerActivityModel.class);
        ActionBar actionBar=getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);

        ViewPager2 viewPager=findViewById(R.id.vpPager);

       // bankRepository=new BankRepository(this);



//        mData.add(new HistoryPagerFragment(1));
//        mData.add(new HistoryPagerFragment(0));

        mData=pagerActivityModel.getFrags();
        MyPagerAdapter adapter=new MyPagerAdapter(this,mData);
        viewPager.setAdapter(adapter);

        TabLayout tabLayout = findViewById(R.id.tab_layout);

//       viewPager
//    tabLayout.setupWithViewPager(viewPager);

        //viewPager2
        new TabLayoutMediator(tabLayout, viewPager,
                (tab, position) -> {
            tab.setText("OBJECT " + (position + 1));
            switch (position){
                case 0:
                    tab.setText("All");
                    break;
                case 1:
                    tab.setText("Deposit");
                    break;
                case 2:
                    tab.setText("Transfer");
                    break;
            }
        }).attach();


    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()){
            case android.R.id.home:
                HistoryPagerActivity.this.finish();
                break;
            default:
                return super.onOptionsItemSelected(item);
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putSerializable("mData", mData);

    }
    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);



    }

}
