package ca.algonquin.kw2446.mybank.ui.home;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;

import java.util.ArrayList;

import ca.algonquin.kw2446.mybank.DepositActivity;
import ca.algonquin.kw2446.mybank.HistoryPagerActivity;
import ca.algonquin.kw2446.mybank.R;
import ca.algonquin.kw2446.mybank.TransferActivity;
import ca.algonquin.kw2446.mybank.adapter.AccountSpinnerAdapter;
import ca.algonquin.kw2446.mybank.databinding.FragmentHomeBinding;
import ca.algonquin.kw2446.mybank.model.AccountBalance;

public class HomeFragment extends Fragment implements HomeViewModel.ItemViewClicked {

    private static final int DEPOSIT_REQUEST_CODE = 10;
    private static final int TRANSFER_REQUEST_CODE = 20;
    public static final String TAG = "HomeFragment";


    private HomeViewModel homeViewModel;
    private ArrayList<AccountBalance> balances;
    private AccountBalance selectedAccount;
    private AccountSpinnerAdapter accountAdapter;
    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        FragmentHomeBinding binding = DataBindingUtil.inflate(inflater, R.layout.fragment_home, container, false);
        View root = binding.getRoot();// //View root = inflater.inflate(R.layout.fragment_home, container, false);
        //here data must be an instance of the class MarsDataProvider
        binding.setLifecycleOwner(this);

        balances=new ArrayList<>();
        homeViewModel =new HomeViewModel(this);
                //new ViewModelProvider(this).get(HomeViewModel.class);
                //ViewModelProviders.of(this).get(HomeViewModel.class);
        binding.setViewModel(homeViewModel);
        final TextView textView = root.findViewById(R.id.tvGreeting);

        homeViewModel.getBalance().observe(getActivity(),v->{
            binding.tvBalance.setText(String.format("$ %.2f",v));
        });

        accountAdapter=new AccountSpinnerAdapter(getActivity(), balances);
        binding.spAccount.setAdapter(accountAdapter);

        binding.spAccount.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener()
        {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int position, long id)
            { selectedAccount=(AccountBalance) adapterView.getItemAtPosition(position); }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) { }
        });

        retrieveList();
        return root;
    }

    @Override
    public void onItemViewClicked(View v) {
        Intent intent;
        switch (v.getId()){
            case R.id.ivDeposit:
                intent=new Intent(getContext(), DepositActivity.class);
                intent.putExtra("account",selectedAccount);
                startActivity(intent);
                break;
            case R.id.ivHistory:
                Intent intent2=new Intent(getActivity(), HistoryPagerActivity.class);
                intent2.putExtra("account",selectedAccount);
                startActivity(intent2);
                break;
            case R.id.ivTransfer:
                intent=new Intent(getActivity(), TransferActivity.class);
                intent.putExtra("account",selectedAccount);
                startActivity(intent );
                break;
            case R.id.ivSetting:
                Navigation.findNavController(v).navigate(R.id.nav_setting);
                //NavHostFragment.findNavController(Fragment)
                //Navigation.findNavController(Activity, @IdRes int viewId)
                //((MainActivity)getActivity()).fragmentNavigate(R.id.nav_setting);
                break;
        }
    }

    public void retrieveList(){
        homeViewModel.getAccountBalances().observe(getActivity(), accountSimples -> {
            if(balances.size() > 0)balances.clear();
            if(accountSimples != null)balances.addAll(accountSimples);
            accountAdapter.notifyDataSetChanged();
        });
    }


}