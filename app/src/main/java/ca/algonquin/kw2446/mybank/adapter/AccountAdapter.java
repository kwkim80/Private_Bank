package ca.algonquin.kw2446.mybank.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.lifecycle.ViewModel;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.List;

import ca.algonquin.kw2446.mybank.R;
import ca.algonquin.kw2446.mybank.model.Account;
import ca.algonquin.kw2446.mybank.model.AccountBalance;

public class AccountAdapter extends ArrayAdapter<AccountBalance> {

    private ArrayList<AccountBalance> list;
    private Context context;

    private static class ViewHolder{
        TextView tvTitle, tvNumber, tvBalance;
    }
    public AccountAdapter(@NonNull Context context,  ArrayList<AccountBalance> list) {
        super(context, R.layout.account_items, list);
        this.context=context;
        this.list = list;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {

        ViewHolder holder;
        AccountBalance accountBalance=list.get(position);
        if(convertView==null) {
            convertView= LayoutInflater.from(parent.getContext()).inflate(R.layout.account_items,parent,false);
            holder = new ViewHolder();
            holder.tvTitle = convertView.findViewById(R.id.tvTitle);
            holder.tvNumber = convertView.findViewById(R.id.tvNumber);
            holder.tvBalance = convertView.findViewById(R.id.tvBalance);
            convertView.setTag(holder);
        } else holder= (ViewHolder) convertView.getTag();

        holder.tvNumber.setText(accountBalance.accountNumber);
        holder.tvTitle.setText(accountBalance.title);
        holder.tvBalance.setText(String.format("$ %.2f",accountBalance.balance));

        return  convertView;
    }

    @Override
    public View getDropDownView(int position, View convertView, ViewGroup parent)
    {
        ViewHolder holder;
        AccountBalance accountBalance=list.get(position);
        if(convertView==null) {
            convertView= LayoutInflater.from(parent.getContext()).inflate(R.layout.account_items,parent,false);
            holder = new ViewHolder();
            holder.tvTitle = convertView.findViewById(R.id.tvTitle);
            holder.tvNumber = convertView.findViewById(R.id.tvNumber);
            holder.tvBalance = convertView.findViewById(R.id.tvBalance);
            convertView.setTag(holder);
        } else holder= (ViewHolder) convertView.getTag();

        holder.tvNumber.setText(accountBalance.accountNumber);
        holder.tvTitle.setText(accountBalance.title);
        holder.tvBalance.setText(String.format("$ %.2f",accountBalance.balance));

        return  convertView;
    }
}
