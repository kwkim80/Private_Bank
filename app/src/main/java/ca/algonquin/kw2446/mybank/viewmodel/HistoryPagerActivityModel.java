package ca.algonquin.kw2446.mybank.viewmodel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import java.util.ArrayList;
import java.util.List;

import ca.algonquin.kw2446.mybank.fragment.HistoryPagerFragment;
import ca.algonquin.kw2446.mybank.model.Money;
import ca.algonquin.kw2446.mybank.persistence.BankRepository;

public class HistoryPagerActivityModel extends AndroidViewModel {
    private BankRepository bankRepository;
   // private MutableLiveData<List<Fragment>> mData;
    private ArrayList<Fragment> fragmentList;
    public HistoryPagerActivityModel(@NonNull Application application) {
        super(application);
        bankRepository = new BankRepository(application);

        fragmentList = new ArrayList<>();


    }


    public LiveData<List<Money>> getList(int type) {
        return bankRepository.getMoneyList(type);
    }

    public ArrayList<Fragment> getFrags(int id){
        fragmentList.add(new HistoryPagerFragment( id,2));
        fragmentList.add(new HistoryPagerFragment(id,0));
        fragmentList.add(new HistoryPagerFragment(id,1));
        return fragmentList;
    }
}
