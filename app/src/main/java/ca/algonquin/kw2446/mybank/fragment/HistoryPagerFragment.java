package ca.algonquin.kw2446.mybank.fragment;


import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

import ca.algonquin.kw2446.mybank.R;
import ca.algonquin.kw2446.mybank.adapter.MoneyAdapter;
import ca.algonquin.kw2446.mybank.model.Money;
import ca.algonquin.kw2446.mybank.viewmodel.HistoryActivityModel;


/**
 * A simple {@link Fragment} subclass.
 */
public class HistoryPagerFragment extends Fragment {


    private HistoryActivityModel viewModel;
    ArrayList<Money> list;
    View v;
    RecyclerView recyclerView;
    RecyclerView.Adapter moneyAdapter;
    RecyclerView.LayoutManager layoutManager;

    int type;
    public HistoryPagerFragment(int type) {
        // Required empty public constructor
       this.type=type;
    }



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        v= inflater.inflate(R.layout.fragment_history_pager, container, false);
        return v;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        list=new ArrayList<>();

        viewModel =
                ViewModelProviders.of(this.getActivity()).get(HistoryActivityModel.class);
        initialRecyclerView();
        retrieveMonenyList( );
    }

    public void initialRecyclerView() {

        recyclerView=v.findViewById(R.id.rvPagerList);
        //recyclerView.setHasFixedSize(true);
        layoutManager=new LinearLayoutManager(getContext());
        recyclerView.setLayoutManager(layoutManager);
        moneyAdapter=new MoneyAdapter(getContext(),list);
        recyclerView.setAdapter(moneyAdapter);
    }

    public void retrieveMonenyList( ){

        viewModel.getList(type).observe(getActivity(), monies -> {
            if(list.size() > 0){
                list.clear();
            }
            if(monies != null){
                list.addAll(monies);
            }
            moneyAdapter.notifyDataSetChanged();
        });
    }

}
