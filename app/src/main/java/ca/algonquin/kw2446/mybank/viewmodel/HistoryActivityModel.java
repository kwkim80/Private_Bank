package ca.algonquin.kw2446.mybank.viewmodel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import java.util.List;

import ca.algonquin.kw2446.mybank.model.Money;
import ca.algonquin.kw2446.mybank.persistence.BankRepository;

public class HistoryActivityModel extends AndroidViewModel {

    private BankRepository bankRepository;

    public HistoryActivityModel(@NonNull Application application) {
        super(application);
        bankRepository=new BankRepository(application);
    }

    public LiveData<List<Money>> getList(int type){
        return bankRepository.getMoneyList(type);
    }
}

