package ca.algonquin.kw2446.mybank.ui.setting;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import ca.algonquin.kw2446.mybank.AccountAddActivity;
import ca.algonquin.kw2446.mybank.AccountListActivity;
import ca.algonquin.kw2446.mybank.MainActivity;
import ca.algonquin.kw2446.mybank.ProfileActivity;
import ca.algonquin.kw2446.mybank.R;

public class SettingFragment extends Fragment {

    private static final int PROFILE_REQUEST_CODE = 30;
    private static final int ACCOUNT_REQUEST_CODE = 31;
    private SettingViewModel settingViewModel;

    TextView tvAccount, tvProfile;
    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        settingViewModel =
                ViewModelProviders.of(this).get(SettingViewModel.class);
        View root = inflater.inflate(R.layout.fragment_setting, container, false);
        final TextView textView = root.findViewById(R.id.text_tools);
        settingViewModel.getText().observe(this, new Observer<String>() {
            @Override
            public void onChanged(@Nullable String s) {
                textView.setText(s);
            }
        });

        tvAccount=root.findViewById(R.id.tvAccount);
        tvProfile=root.findViewById(R.id.tvProfile);

        tvAccount.setOnClickListener(v->{
            Intent intent=new Intent(getActivity(), AccountListActivity.class);
            startActivity(intent);
        });

        tvProfile.setOnClickListener(v->{
            Intent intent=new Intent(getActivity(), ProfileActivity.class);
            startActivityForResult(intent, PROFILE_REQUEST_CODE);
        });
        return root;
    }



        @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        MainActivity mainActivity= (MainActivity) getActivity();
        if(requestCode == PROFILE_REQUEST_CODE && resultCode == Activity.RESULT_OK) {
            mainActivity.setProfileInHeader();
            Toast.makeText(mainActivity, "Succedd to save your profile", Toast.LENGTH_SHORT).show();
        }

    }
  
  
  
  
  
}