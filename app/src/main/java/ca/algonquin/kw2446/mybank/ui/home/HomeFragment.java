package ca.algonquin.kw2446.mybank.ui.home;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import ca.algonquin.kw2446.mybank.DepositActivity;
import ca.algonquin.kw2446.mybank.MainActivity;
import ca.algonquin.kw2446.mybank.R;

public class HomeFragment extends Fragment {

    public static final int DEPOSIT_REQUEST_CODE = 10;
    private HomeViewModel homeViewModel;

    ImageView ivDeposit,ivHistory, ivSetting, ivWithdrawal;
    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        homeViewModel =
                ViewModelProviders.of(this).get(HomeViewModel.class);
        View root = inflater.inflate(R.layout.fragment_home, container, false);
        final TextView textView = root.findViewById(R.id.tvGreeting);
        homeViewModel.getText().observe(this, new Observer<String>() {
            @Override
            public void onChanged(@Nullable String s) {
                textView.setText(s);
            }
        });

        View.OnClickListener listener = new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                switch (v.getId()){
                    case R.id.ivDeposit:
                       // ((MainActivity)getActivity()).fragmentNavigate(R.id.nav_deposit);
                        Intent intent=new Intent(getActivity(), DepositActivity.class);
                        startActivityForResult(intent, DEPOSIT_REQUEST_CODE);

                        break;
                    case R.id.ivHistory:
                        ((MainActivity)getActivity()).fragmentNavigate(R.id.nav_history);
                        break;
                    case R.id.ivWidrawal:
                        ((MainActivity)getActivity()).fragmentNavigate(R.id.nav_withdrawal);
                        break;
                    case R.id.ivSetting:
                        ((MainActivity)getActivity()).fragmentNavigate(R.id.nav_setting);
                        break;

                }
            }
        };

        ivDeposit=root.findViewById(R.id.ivDeposit);
        ivDeposit.setOnClickListener(listener);
        ivHistory=root.findViewById(R.id.ivHistory);
        ivHistory.setOnClickListener(listener);
        ivSetting=root.findViewById(R.id.ivSetting);
        ivSetting.setOnClickListener(listener);
        ivWithdrawal=root.findViewById(R.id.ivWidrawal);
        ivWithdrawal.setOnClickListener(listener);

        return root;
    }


}