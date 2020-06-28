package ca.algonquin.kw2446.mybank.adapter;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.viewpager2.adapter.FragmentStateAdapter;

import java.util.ArrayList;

public class MyPagerAdapter extends FragmentStateAdapter {

    private ArrayList<Fragment> mData;

    public MyPagerAdapter(@NonNull FragmentActivity fragmentActivity, ArrayList<Fragment> fragments) {
        super(fragmentActivity);
        mData=fragments;

    }

    @NonNull
    @Override
    public Fragment createFragment(int position) { return mData.get(position); }

    @Override
    public int getItemCount() { return mData.size(); }
}
