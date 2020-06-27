package ca.algonquin.kw2446.mybank.ui.deposit;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class DepositViewModel extends ViewModel {

    private MutableLiveData<String> mText;

    public DepositViewModel() {
        mText = new MutableLiveData<>();
        mText.setValue("Deposit");
    }

    public LiveData<String> getText() {
        return mText;
    }
}