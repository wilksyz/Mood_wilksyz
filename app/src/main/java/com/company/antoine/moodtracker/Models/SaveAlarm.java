package com.company.antoine.moodtracker.Models;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.util.Log;

import java.util.Date;

import static android.content.Context.MODE_PRIVATE;

public class SaveAlarm extends BroadcastReceiver{

    private static final String KEY_MOOD_SAVE = "mood save";
    private static final String KEY_COMMENT_SAVE = "comment save";
    private static final String KEY_POSITION_SAVE = "position save";
    private int mNumberSave = 0;
    protected String pNewMoodHistoric;

    @Override
    public void onReceive(Context context, Intent intent) {
        Log.e(getClass().getSimpleName(),"broadcast");
        SharedPreferences preferences = context.getSharedPreferences("MyMood", MODE_PRIVATE);
        String pComment = context.getSharedPreferences("MyMood", MODE_PRIVATE).getString(KEY_COMMENT_SAVE, null);
        String pPosition = String.valueOf(context.getSharedPreferences("MyMood", MODE_PRIVATE).getInt(KEY_POSITION_SAVE, 3));
        Date dateNow = new Date();
        final String date = String.valueOf(dateNow);
        String pMood = (pPosition+","+pComment+","+date);
        String pMoodHistoric = context.getSharedPreferences("MyMood", MODE_PRIVATE).getString(KEY_MOOD_SAVE, null);
        if (pMoodHistoric != null){
            pNewMoodHistoric = (pMood+";"+pMoodHistoric);
        }else{
            pNewMoodHistoric = pMood;
        }
        preferences.edit().putString(KEY_MOOD_SAVE, pNewMoodHistoric).apply();
        preferences.edit().remove(KEY_POSITION_SAVE).apply();
        preferences.edit().remove(KEY_COMMENT_SAVE).apply();
        mNumberSave++;
        if (mNumberSave > 7){
            String[] pReorganization = pNewMoodHistoric.split(";");
           String pMoodReorganization = ( pReorganization[0] + ";" + pReorganization[1] + ";" + pReorganization[2] + ";" + pReorganization[3] + ";" + pReorganization[4] + ";" + pReorganization[5] + ";" + pReorganization[6]);
            preferences.edit().putString(KEY_MOOD_SAVE, pMoodReorganization).apply();
        }
    }
}