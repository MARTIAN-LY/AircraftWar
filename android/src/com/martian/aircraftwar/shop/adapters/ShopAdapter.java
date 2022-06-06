package com.martian.aircraftwar.shop.adapters;

import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.martian.aircraftwar.R;
import com.martian.aircraftwar.shop.goods.Good;

import java.util.List;

public class ShopAdapter extends RecyclerView.Adapter<ShopAdapter.InnerHolder>
{
    private final List<Good> goodList;

    public ShopAdapter(List<Good> goodList)
    {
        this.goodList = goodList;
    }

    @NonNull
    @Override
    public InnerHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType)
    {
        View view = View.inflate(parent.getContext(), R.layout.item_shop, null);
        return new InnerHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull InnerHolder holder, int position)
    {
        holder.setData(goodList.get(position));
    }

    @Override
    public int getItemCount()
    {
        if(goodList != null)
        {
            return goodList.size();
        }
        return 0;
    }

    public class InnerHolder extends RecyclerView.ViewHolder
    {
        private ImageView goodIcon;
        private TextView goodTitle;
        public InnerHolder(@NonNull View itemView)
        {
            super(itemView);
            goodIcon = itemView.findViewById(R.id.good_icon);
            goodTitle = itemView.findViewById(R.id.good_title);
        }

        public void setData(Good good)
        {
            goodIcon.setImageResource(good.icon);
            goodTitle.setText(good.title);
        }
    }
}
