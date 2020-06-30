package ca.algonquin.kw2446.mybank.ui.home;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.ViewModelProviders;
import androidx.navigation.Navigation;

import ca.algonquin.kw2446.mybank.DepositActivity;
import ca.algonquin.kw2446.mybank.HistoryPagerActivity;
import ca.algonquin.kw2446.mybank.MainActivity;
import ca.algonquin.kw2446.mybank.R;
import ca.algonquin.kw2446.mybank.TransferActivity;
import ca.algonquin.kw2446.mybank.databinding.FragmentHomeBinding;
import ca.algonquin.kw2446.mybank.persistence.BankRepository;

public class HomeFragment extends Fragment implements HomeViewModel.ItemViewClicked {

    private static final int DEPOSIT_REQUEST_CODE = 10;
    private static final int TRANSFER_REQUEST_CODE = 20;


    private HomeViewModel homeViewModel;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        FragmentHomeBinding binding = DataBindingUtil.inflate(inflater, R.layout.fragment_home, container, false);
        View root = binding.getRoot();
        //here data must be an instance of the class MarsDataProvider
        binding.setLifecycleOwner(this);

        homeViewModel =new HomeViewModel(this);
                //new ViewModelProvider(this).get(HomeViewModel.class);
                //ViewModelProviders.of(this).get(HomeViewModel.class);
        binding.setViewModel(homeViewModel);
        //View root = inflater.inflate(R.layout.fragment_home, container, false);
        final TextView textView = root.findViewById(R.id.tvGreeting);



        homeViewModel.getBalance().observe(getActivity(),v->{
            binding.tvBalance.setText(String.format("$ %.2f",v));
        });
      //  tvBalance.setText(String.format("$ %.2f",0));
        return root;
    }


    @Override
    public void onItemViewClicked(View v) {
        Intent intent;
        switch (v.getId()){
            case R.id.ivDeposit:

                intent=new Intent(getContext(), DepositActivity.class);
                startActivity(intent);
                break;
            case R.id.ivHistory:

                Intent intent2=new Intent(getActivity(), HistoryPagerActivity.class);
                startActivity(intent2);
                break;
            case R.id.ivTransfer:
                intent=new Intent(getActivity(), TransferActivity.class);
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
}