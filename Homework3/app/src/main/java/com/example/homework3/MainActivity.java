package com.example.homework3;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    boolean isShowName = false;
    String idName = "李天勤 2018080106";
    GridView coursesGV;
    ExpandableHeightGridView tencentServices;
    GridView thirdPartyServices;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Edit Toolbar
//        Toolbar myToolbar = (Toolbar) findViewById(R.id.my_toolbar);
//        setSupportActionBar(myToolbar);

        // 收付款 and 钱包
        coursesGV = findViewById(R.id.payment_grid_view);
        ArrayList<CourseModel> courseModelArrayList = new ArrayList<CourseModel>();

        courseModelArrayList.add(new CourseModel("收付款", R.drawable.baseline_attach_money_24));
        courseModelArrayList.add(new CourseModel("钱包", R.drawable.baseline_account_balance_wallet_24));

        CourseGVAdapter adapter = new CourseGVAdapter(this, courseModelArrayList);
        coursesGV.setAdapter(adapter);
        coursesGV.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long l) {
                Toast.makeText(getApplicationContext(), Integer.toString(position), Toast.LENGTH_LONG).show();
            }
        });


        // 腾讯服务
        tencentServices = (ExpandableHeightGridView) findViewById(R.id.tencent_services_grid_view);
        tencentServices.setExpanded(true);
        ArrayList<CourseModel> tencentServiceArrayList = new ArrayList<CourseModel>();
        tencentServiceArrayList.add(new CourseModel("信用卡还款", R.drawable.baseline_credit_card_24));
        tencentServiceArrayList.add(new CourseModel("手机充值", R.drawable.baseline_phone_android_24));
        tencentServiceArrayList.add(new CourseModel("理财同", R.drawable.baseline_auto_graph_24));
        tencentServiceArrayList.add(new CourseModel("生活缴费", R.drawable.baseline_water_drop_24));
        tencentServiceArrayList.add(new CourseModel("Q币充值", R.drawable.baseline_currency_bitcoin_24));
        tencentServiceArrayList.add(new CourseModel("城市服务", R.drawable.baseline_location_city_24));
        tencentServiceArrayList.add(new CourseModel("腾讯公益", R.drawable.baseline_public_24));
        ServiceGridViewAdapter adapter1 = new ServiceGridViewAdapter(this, tencentServiceArrayList);
        tencentServices.setAdapter(adapter1);

        // 第三方服务
        thirdPartyServices = findViewById(R.id.third_party_services_grid_view);
        ArrayList<CourseModel> thirdPartyArrayList = new ArrayList<CourseModel>();
        thirdPartyArrayList.add(new CourseModel("信用卡还款", R.drawable.baseline_credit_card_24));
        thirdPartyArrayList.add(new CourseModel("手机充值", R.drawable.baseline_phone_android_24));
        thirdPartyArrayList.add(new CourseModel("理财同", R.drawable.baseline_auto_graph_24));
        ServiceGridViewAdapter adapter2 = new ServiceGridViewAdapter(this, thirdPartyArrayList);
        thirdPartyServices.setAdapter(adapter2);
    }
}