package ca.algonquin.kw2446.mybank.ui.history;

import android.content.Intent;
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

import ca.algonquin.kw2446.mybank.AccountActivity;
import ca.algonquin.kw2446.mybank.HistoryActivity;
import ca.algonquin.kw2446.mybank.R;

public class HistoryFragment extends Fragment {

    private HistoryViewModel historyViewModel;

    TextView tvAll, tvDeposit, tvWithdrawal;
    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        historyViewModel =
                ViewModelProviders.of(this).get(HistoryViewModel.class);
        View root = inflater.inflate(R.layout.fragment_history, container, false);
        final TextView textView = root.findViewById(R.id.text_gallery);
        historyViewModel.getText().observe(this, new Observer<String>() {
            @Override
            public void onChanged(@Nullable String s) {
                textView.setText(s);
            }
        });

        tvAll=root.findViewById(R.id.tvAll);
        tvDeposit=root.findViewById(R.id.tvDeposit);
        tvWithdrawal=root.findViewById(R.id.tvWithdrawal);

        tvAll.setOnClickListener(v->{
            Intent intent=new Intent(getActivity(), HistoryActivity.class);
            startActivity(intent);
        });
        return root;
    }
}