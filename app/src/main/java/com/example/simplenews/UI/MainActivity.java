package com.example.simplenews.UI;

import android.os.Bundle;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;
import com.example.simplenews.Api.CountryCodeProvider;
import com.example.simplenews.R;
import com.google.android.material.tabs.TabLayout;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        SectionsPagerAdapter sectionsPagerAdapter = new SectionsPagerAdapter(this, getSupportFragmentManager());
        ViewPager viewPager = findViewById(R.id.view_pager);
        viewPager.setAdapter(sectionsPagerAdapter);
        TabLayout tabs = findViewById(R.id.tabs);
        tabs.setupWithViewPager(viewPager);
        CountryCodeProvider countryCodeProvider = new CountryCodeProvider();

        if(!countryCodeProvider.containsCountryCode()){
            Toast.makeText(this, "Your country is not supported, United States news is shown",Toast.LENGTH_SHORT).show();
        }
    }

}