package com.martian.aircraftwar.shop;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.TextView;

import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.utils.viewport.StretchViewport;
import com.martian.aircraftwar.R;
import com.martian.aircraftwar.shop.adapters.ShopAdapter;
import com.martian.aircraftwar.shop.adapters.SpaceItemDecoration;
import com.martian.aircraftwar.shop.goods.Good;
import com.martian.aircraftwar.shop.goods.HealGood;
import com.martian.aircraftwar.shop.goods.MaxHpGood;

import java.util.ArrayList;
import java.util.List;

public class ShopActivity extends AppCompatActivity
{
    private RecyclerView rcView;
    private List<Good> goodList;
    private TextView moneyView;
    public int money;
    SharedPreferences.Editor editor;
    ShopAdapter adapter;
    @SuppressLint("SetTextI18n")
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shop);
        moneyView = this.findViewById(R.id.recyclerview_money);
        SharedPreferences namePreference = this.getSharedPreferences("userInfo", MODE_PRIVATE);
        String name = namePreference.getString("username", null);
        SharedPreferences preferences = this.getSharedPreferences(name, MODE_PRIVATE);
        money = preferences.getInt("money", 0);
        editor = preferences.edit();
        moneyView.setText("余额："+ money +"元");
        this.setTitle("道具商城");
        rcView = this.findViewById(R.id.recyclerview_shop);
        goodList = new ArrayList<>();
        goodList.add(new MaxHpGood(this, this));
        goodList.add(new HealGood(this,this));
        adapter = new ShopAdapter(goodList);
        SpaceItemDecoration decoration = new SpaceItemDecoration(200);
        rcView.addItemDecoration(decoration);
        rcView.setLayoutManager(new LinearLayoutManager(this));
        rcView.setAdapter(adapter);
    }

    @SuppressLint("SetTextI18n")
    public boolean buy(int cost)
    {
        if(cost > money)
        {
            return false;
        }
        editor.remove("money");
        editor.commit();
        money -= cost;
        adapter.notifyDataSetChanged();
        moneyView.setText("余额："+ money +"元");
        editor.putInt("money", money);
        editor.commit();
        return true;
    }
}