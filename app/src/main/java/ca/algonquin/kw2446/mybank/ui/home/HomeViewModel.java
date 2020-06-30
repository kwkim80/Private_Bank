package ca.algonquin.kw2446.mybank.ui.home;

import android.app.Application;
import android.content.Context;
import android.content.Intent;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import ca.algonquin.kw2446.mybank.DepositActivity;
import ca.algonquin.kw2446.mybank.HistoryPagerActivity;
import ca.algonquin.kw2446.mybank.MainActivity;
import ca.algonquin.kw2446.mybank.R;
import ca.algonquin.kw2446.mybank.TransferActivity;
import ca.algonquin.kw2446.mybank.persistence.BankRepository;
import ca.algonquin.kw2446.mybank.util.PreferenceManager;

public class HomeViewModel extends ViewModel  {

    interface ItemViewClicked{
        void onItemViewClicked(View v);
    }
    private MutableLiveData<String> mText;
    private BankRepository bankRepository;
    private LiveData<Double> balance;
    private ItemViewClicked act;
//

    public HomeViewModel(HomeFragment fragment) {
        act= (ItemViewClicked) fragment;
        //  MainActivity m=(MainActivity)context;

        mText = new MutableLiveData<>();
        mText.setValue(String.format("Welcome to %s ",PreferenceManager.getString(fragment.getContext(),"Name")));
        bankRepository=new BankRepository(fragment.getContext());
    }

    public MutableLiveData<String> getText() { return mText; }
    public void setText(MutableLiveData<String> mText) { this.mText = mText; }

    public LiveData<Double> getBalance(){
        return bankRepository.getBalance();
    }

    public void imageViewClick(View v){
            act.onItemViewClicked(v);

    }
}