package com.company.antoine.moodtracker.Controllers.Activities;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.company.antoine.moodtracker.Models.VerticalViewPager;
import com.company.antoine.moodtracker.R;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        configureViewPager();
    }

    protected void configureViewPager(){

        VerticalViewPager pPager = findViewById(R.id.activity_main_vertical_view_pager);

        pPager.setAdapter(new PageAdapter(getSupportFragmentManager(), getResources().getIntArray(R.array.ColorsViewPager)) {
        });
    }
}
