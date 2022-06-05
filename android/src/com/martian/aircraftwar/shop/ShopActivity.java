package com.martian.aircraftwar.shop;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import com.martian.aircraftwar.R;
import com.martian.aircraftwar.shop.adapters.ShopAdapter;
import com.martian.aircraftwar.shop.goods.Good;
import com.martian.aircraftwar.shop.goods.MaxHpGood;

import java.util.ArrayList;
import java.util.List;

public class ShopActivity extends AppCompatActivity
{
    private RecyclerView rcView;
    private List<Good> goodList;
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shop);
        this.setTitle("道具商城");
        rcView = this.findViewById(R.id.recyclerview_shop);
        goodList = new ArrayList<>();
        goodList.add(new MaxHpGood());
        ShopAdapter adapter = new ShopAdapter(goodList);
        rcView.setLayoutManager(new LinearLayoutManager(this));
        rcView.setAdapter(adapter);
    }
}