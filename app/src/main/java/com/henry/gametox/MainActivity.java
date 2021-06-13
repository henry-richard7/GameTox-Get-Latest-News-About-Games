package com.henry.gametox;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager2.widget.ViewPager2;

import android.os.Bundle;

import com.google.android.material.tabs.TabLayout;
import com.google.android.material.tabs.TabLayoutMediator;

public class MainActivity extends AppCompatActivity {
    public static ViewPager2 viewPager2;
    private String[] titles = new String[]{"Upcoming Games", "Free Games", "Game Trailers"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ViewPagerAdapter pagerAdapter = new ViewPagerAdapter(this);

        TabLayout tabLayout = (TabLayout) findViewById(R.id.tabLayout1);
        viewPager2 = (ViewPager2) findViewById(R.id.viewPager1);

        viewPager2.setAdapter(pagerAdapter);
        new TabLayoutMediator(tabLayout, viewPager2, ((tab, position) -> tab.setText(titles[position]))).attach();

    }
}