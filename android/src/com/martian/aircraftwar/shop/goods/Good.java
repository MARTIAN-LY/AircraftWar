package com.martian.aircraftwar.shop.goods;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.view.View;
import android.widget.Toast;


import com.martian.aircraftwar.R;
import com.martian.aircraftwar.shop.ShopActivity;

public abstract class Good
{
    public String title;
    public int cost, icon;
    public int las;
    public class goodButtonListener implements View.OnClickListener{
        public Context c;
        public ShopActivity a;
        @Override
        public void onClick(View v)
        {
            AlertDialog.Builder alterDiaglog = new AlertDialog.Builder(c);
            alterDiaglog.setIcon(R.drawable.bg_good);//图标
            alterDiaglog.setTitle("确认购买吗？(还可购买" + las + "个)");//文字
            alterDiaglog.setMessage("价格为"+cost+"元");//提示消息
            alterDiaglog.setPositiveButton("购买", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    if(las == 0)
                    {
                        Toast.makeText(c,"购买数量已达上限！",Toast.LENGTH_SHORT).show();
                    }
                    else if(a.buy(cost))
                    {
                        Toast.makeText(c,"购买成功！",Toast.LENGTH_SHORT).show();
                        effect();
                    }
                    else
                    {
                        Toast.makeText(c,"余额不足！",Toast.LENGTH_SHORT).show();
                    }
                }
            });
            alterDiaglog.setNegativeButton("取消", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    Toast.makeText(c,"已取消购买！",Toast.LENGTH_SHORT).show();
                }
            });

            //显示
            alterDiaglog.show();

        }
    }
    public goodButtonListener listener = new goodButtonListener();
    abstract public void effect();
    Good(String title, int cost, int icon)
    {
        this.title = title;
        this.cost = cost;
        this.icon = icon;
    }
}
