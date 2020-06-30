package ca.algonquin.kw2446.mybank.adapter;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

import ca.algonquin.kw2446.mybank.R;
import ca.algonquin.kw2446.mybank.model.Money;

public class MoneyAdapter extends RecyclerView.Adapter<MoneyAdapter.ViewHolder> {


    private ArrayList<Money> list;
    private Context context;
    View v;

    public MoneyAdapter( Context context, ArrayList<Money> list) {
        this.list = list;
        this.context = context;
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        ImageView ivIcon;
        TextView tvOpponent, tvAmount, tvTime;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
           ivIcon=itemView.findViewById(R.id.ivIcon);
           tvOpponent=itemView.findViewById(R.id.tvOpponent);
           tvAmount=itemView.findViewById(R.id.tvAmount);
           tvTime=itemView.findViewById(R.id.tvTime);
        }
    }

    @NonNull
    @Override
    public MoneyAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        v= LayoutInflater.from(parent.getContext()).inflate(R.layout.history_items, parent, false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull MoneyAdapter.ViewHolder holder, int position) {
        Money money=list.get(position);

        holder.ivIcon.setImageResource(R.drawable.ic_cashout);
        holder.tvAmount.setText(String.format("$ %.2f",money.getAmount()));
        holder.tvTime.setText(money.getTimestamp());
        holder.tvOpponent.setText(money.getOpponent());
        if(money.isOut()){
            holder.ivIcon.setRotation(180);
            holder.tvAmount.setTextColor(Color.parseColor("#FF9C27B0"));
        }
        holder.itemView.setTag(money);
    }

    public ArrayList<Money> getList(){
        return list;
    }

    @Override
    public int getItemCount() {
        return list.size();
    }


}
