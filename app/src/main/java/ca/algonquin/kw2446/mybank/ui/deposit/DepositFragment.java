package ca.algonquin.kw2446.mybank.ui.deposit;

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

import ca.algonquin.kw2446.mybank.DepositActivity;
import ca.algonquin.kw2446.mybank.HistoryActivity;
import ca.algonquin.kw2446.mybank.R;
import ca.algonquin.kw2446.mybank.util.AppUtil;

public class DepositFragment extends Fragment {

    private DepositViewModel depositViewModel;
    View v;
    TextView tvDeposit, tvCheque, tvHistory;

    private static final int DEPOSIT_REQUEST_CODE = 10;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        depositViewModel =
                ViewModelProviders.of(this).get(DepositViewModel.class);
        View root = inflater.inflate(R.layout.fragment_deposit, container, false);
        final TextView textView = root.findViewById(R.id.tvTitle);
        depositViewModel.getText().observe(this, new Observer<String>() {
            @Override
            public void onChanged(@Nullable String s) {
                textView.setText(s);
            }
        });

        v=root;
        return v;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        tvDeposit=v.findViewById(R.id.tvDeposit);
        tvCheque=v.findViewById(R.id.tvCheque);
        tvHistory=v.findViewById(R.id.tvHistory);

        tvDeposit.setOnClickListener(v->{
            Intent intent=new Intent(getActivity(), DepositActivity.class);
            startActivityForResult(intent, DEPOSIT_REQUEST_CODE);
        });
        tvCheque.setOnClickListener(v->{
            AppUtil.showSnackbar(v,"It is preparing");
        });
        tvHistory.setOnClickListener(v->{
            Intent intent=new Intent(getActivity(), HistoryActivity.class);
            intent.putExtra("page",0);
            startActivity(intent);
        });
    }
}