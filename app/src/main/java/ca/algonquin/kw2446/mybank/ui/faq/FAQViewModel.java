package ca.algonquin.kw2446.mybank.ui.faq;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class FAQViewModel extends ViewModel {

    private MutableLiveData<String> mText;

    public FAQViewModel() {
        mText = new MutableLiveData<>();
        mText.setValue("This is faq fragment");
    }

    public LiveData<String> getText() {
        return mText;
    }
}