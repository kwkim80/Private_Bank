package ca.algonquin.kw2446.mybank.ui.withdrawal;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class WithdrawalViewModel extends ViewModel {

    private MutableLiveData<String> mText;

    public WithdrawalViewModel() {
        mText = new MutableLiveData<>();
        mText.setValue("This is Withdrawal fragment");
    }

    public LiveData<String> getText() {
        return mText;
    }
}