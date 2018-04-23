package com.company.antoine.moodtracker.Controllers.Activities;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import java.util.Calendar;
import java.util.Date;

import com.company.antoine.moodtracker.Models.SaveAlarm;
import com.company.antoine.moodtracker.Models.VerticalViewPager;
import com.company.antoine.moodtracker.R;

public class MainActivity extends AppCompatActivity {

    private static final String KEY_MOOD_SAVE = "comment save";
    private static final String KEY_COMMENT_SAVE = "comment save";
    private static final String KEY_POSITION_SAVE = "position save";
    public VerticalViewPager pPager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        configureViewPager();
        pPager.setOffscreenPageLimit(3);

        pPager.addOnPageChangeListener(new VerticalViewPager.OnPageChangeListener() {
                @Override
                public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

                }

                @Override
                public void onPageSelected(int position) {
                    SharedPreferences preferences = getSharedPreferences("MyMood", MODE_PRIVATE);
                    preferences.edit().putInt(KEY_POSITION_SAVE, position)
                            .apply();
                }

                @Override
                public void onPageScrollStateChanged(int state) {

                }
            });

        Intent intent = new Intent(this, SaveAlarm.class);
        PendingIntent mPending = PendingIntent.getBroadcast(this, 0, intent, PendingIntent.FLAG_ONE_SHOT);
        AlarmManager manager = (AlarmManager) getSystemService(Context.ALARM_SERVICE);
        Calendar calendar = Calendar.getInstance();
        calendar.set(2018, 3,19,0,0,0);
        manager.setRepeating(AlarmManager.RTC_WAKEUP, calendar.getTimeInMillis(), AlarmManager.INTERVAL_DAY, mPending);
    }

    protected void configureViewPager(){
        pPager = findViewById(R.id.activity_main_vertical_view_pager);
        pPager.setAdapter(new PageAdapter(getSupportFragmentManager(), getResources().getIntArray(R.array.ColorsViewPager)) {
        });
        int pPositionSave = getSharedPreferences("MyMood", MODE_PRIVATE).getInt(KEY_POSITION_SAVE, 3);
        pPager.setCurrentItem(pPositionSave);
    }

    public void moodSave(){
        String pComment = getPreferences(MODE_PRIVATE).getString(KEY_COMMENT_SAVE, null);
        String pPosition = String.valueOf(pPager.getCurrentItem());
        Date dateNow = new Date();
        final String date = String.valueOf(dateNow);
        String pMood = (pPosition+","+pComment+","+date);
        String pMoodHistoric = getPreferences(MODE_PRIVATE).getString(KEY_MOOD_SAVE, null);
        String pNewMoodHistoric = (pMood+";"+pMoodHistoric);
        SharedPreferences preferences = getPreferences(MODE_PRIVATE);
        preferences.edit().putString(KEY_MOOD_SAVE, pNewMoodHistoric)
                .apply();
    }
}
