package com.company.antoine.moodtracker.Controllers.Activities;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import java.util.Calendar;

import com.company.antoine.moodtracker.Models.SaveAlarm;
import com.company.antoine.moodtracker.Models.VerticalViewPager;
import com.company.antoine.moodtracker.R;

public class MainActivity extends AppCompatActivity {

    private static final String KEY_POSITION_SAVE = "position save";
    public VerticalViewPager pPager;

    /**
     * The MainActivity contains the viewPager and sets the adapter of the fragment to display the pages.
     * The alarm allows to record every day the mood and the comment at a fixed time.
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Call the method configureViewPager.
        configureViewPager();
        pPager.setOffscreenPageLimit(3);

        //Detects the page change to save the new position.
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

        //The alarm is programmed for which starts every day at a fixed time.
        Intent intent = new Intent(this, SaveAlarm.class);
        PendingIntent mPending = PendingIntent.getBroadcast(this, 0, intent, PendingIntent.FLAG_ONE_SHOT);
        AlarmManager manager = (AlarmManager) getSystemService(Context.ALARM_SERVICE);
        Calendar calendar = Calendar.getInstance();
        calendar.setTimeInMillis(System.currentTimeMillis());
        calendar.set(Calendar.HOUR_OF_DAY, 23);
        calendar.set(Calendar.MINUTE, 59);
        calendar.set(Calendar.SECOND, 59);
        manager.setRepeating(AlarmManager.RTC_WAKEUP, calendar.getTimeInMillis(), AlarmManager.INTERVAL_DAY, mPending);
    }

    //We recover the identifier of the view of the viewPager and configure the adapter of the fragment that will display the pages.
    protected void configureViewPager(){
        pPager = findViewById(R.id.activity_main_vertical_view_pager);
        pPager.setAdapter(new PageAdapter(getSupportFragmentManager(), getResources().getIntArray(R.array.ColorsViewPager)) {
        });
        int pPositionSave = getSharedPreferences("MyMood", MODE_PRIVATE).getInt(KEY_POSITION_SAVE, 3);
        pPager.setCurrentItem(pPositionSave);
    }

    //This method allows you to return to the last mood selected when you exit the background mode.
    @Override
    protected void onPostResume() {
        super.onPostResume();
        int pPosition = getSharedPreferences("MyMood", MODE_PRIVATE).getInt(KEY_POSITION_SAVE, 3);
        pPager.setCurrentItem(pPosition);
    }
}
