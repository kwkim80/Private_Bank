package ca.algonquin.kw2446.mybank.ui.transfer;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class TransferViewModel extends ViewModel {

    private MutableLiveData<String> mText;

    public TransferViewModel() {
        mText = new MutableLiveData<>();
        mText.setValue("Transfer Menu");
    }

    public LiveData<String> getText() {
        return mText;
    }
}