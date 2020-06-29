package ca.algonquin.kw2446.mybank.ui.transfer;

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
import ca.algonquin.kw2446.mybank.TransferActivity;
import ca.algonquin.kw2446.mybank.util.AppUtil;

public class TransferFragment extends Fragment {

    private TransferViewModel transferViewModel;
    View v;
    TextView tvTransfer, tvMove, tvHistory;
    private static final int TRANSFER_REQUEST_CODE = 20;
    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        transferViewModel =
                ViewModelProviders.of(this).get(TransferViewModel.class);
        View root = inflater.inflate(R.layout.fragment_transfer, container, false);
        final TextView textView = root.findViewById(R.id.tvTitle);
        transferViewModel.getText().observe(this, new Observer<String>() {
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

        tvTransfer=v.findViewById(R.id.tvTransfer);
        tvMove=v.findViewById(R.id.tvMove);
        tvHistory=v.findViewById(R.id.tvHistory);

        tvTransfer.setOnClickListener(v->{
            Intent intent=new Intent(getActivity(), TransferActivity.class);
            startActivityForResult(intent, TRANSFER_REQUEST_CODE);
        });
        tvMove.setOnClickListener(v->{
            AppUtil.showSnackbar(v,"It is preparing");
        });
        tvHistory.setOnClickListener(v->{
            Intent intent=new Intent(getActivity(), HistoryActivity.class);
            intent.putExtra("page",1);
            startActivity(intent);
        });
    }
}