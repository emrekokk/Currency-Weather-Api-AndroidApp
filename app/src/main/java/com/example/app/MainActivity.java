package com.example.app;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.google.android.material.tabs.TabLayout;

import androidx.viewpager.widget.ViewPager;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {
    SectionsPageAdapter sectionsPageAdapter;
    private ViewPager viewPager;
    TextView textView;
    EditText textAy,textGun,textYil;
    Button button;
    @Override

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        sectionsPageAdapter = new SectionsPageAdapter(getSupportFragmentManager());
        viewPager = (ViewPager) findViewById(R.id.container);
        setupPager(viewPager);

        TabLayout tabLayout = (TabLayout) findViewById(R.id.tabs);
        tabLayout.setupWithViewPager(viewPager);
    }
    private void setupPager(ViewPager viewPager) {

        SectionsPageAdapter adapter = new SectionsPageAdapter(getSupportFragmentManager());
        adapter.addFragment(new FragmentPreferences(), "Tercihler");
        adapter.addFragment(new FragmentWeather(), "Hava Durumu");
        adapter.addFragment(new FragmentCurrency(),"Döviz");
        adapter.addFragment(new FragmentTimer(),"Geri Sayım");
        viewPager.setAdapter(adapter);
    }
}

