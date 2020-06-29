package ca.algonquin.kw2446.mybank.viewmodel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import java.util.List;

import ca.algonquin.kw2446.mybank.model.AccountBalance;
import ca.algonquin.kw2446.mybank.persistence.BankRepository;

public class AccountListActivityModel extends AndroidViewModel {

    private BankRepository bankRepository;
    public AccountListActivityModel(@NonNull Application application) {
        super(application);
        bankRepository=new BankRepository(getApplication());
    }

    public LiveData<List<AccountBalance>> getAccountBalances(){
      return   bankRepository.getAccountsWithBalance();
    }
}
