package ca.algonquin.kw2446.mybank.ui.faq;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import ca.algonquin.kw2446.mybank.R;

public class FAQFragment extends Fragment {

    private FAQViewModel FAQViewModel;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        FAQViewModel =
                ViewModelProviders.of(this).get(FAQViewModel.class);
        View root = inflater.inflate(R.layout.fragment_faq, container, false);
        final TextView textView = root.findViewById(R.id.text_slideshow);
        FAQViewModel.getText().observe(this, new Observer<String>() {
            @Override
            public void onChanged(@Nullable String s) {
                textView.setText(s);
            }
        });
        return root;
    }
}