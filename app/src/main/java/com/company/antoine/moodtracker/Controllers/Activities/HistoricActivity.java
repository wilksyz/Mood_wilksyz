package com.company.antoine.moodtracker.Controllers.Activities;

import android.graphics.drawable.Drawable;
import android.support.percent.PercentLayoutHelper;
import android.support.percent.PercentRelativeLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.company.antoine.moodtracker.R;

public class HistoricActivity extends AppCompatActivity implements View.OnClickListener{

    private Button mButtonDay1;
    private Button mButtonDay2;
    private Button mButtonDay3;
    private Button mButtonDay4;
    private Button mButtonDay5;
    private Button mButtonDay6;
    private Button mButtonDay7;
    private String mMoodHistoric;
    private static final String KEY_MOOD_SAVE = "mood save";
    private String[] mSplit;
    private String[] mDay1;
    private String[] mDay2;
    private String[] mDay3;
    private String[] mDay4;
    private String[] mDay5;
    private String[] mDay6;
    private String[] mDay7;
    private int mLongBoard = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_historic);

        extractBackup();
        int[] colors = getResources().getIntArray(R.array.ColorsViewPager);
        Drawable mImage = getResources().getDrawable(R.drawable.ic_comment_black_48px);

        mButtonDay1 = findViewById(R.id.activity_historic_1);
        mButtonDay2 = findViewById(R.id.activity_historic_2);
        mButtonDay3 = findViewById(R.id.activity_historic_3);
        mButtonDay4 = findViewById(R.id.activity_historic_4);
        mButtonDay5 = findViewById(R.id.activity_historic_5);
        mButtonDay6 = findViewById(R.id.activity_historic_6);
        mButtonDay7 = findViewById(R.id.activity_historic_7);

        mButtonDay1.setOnClickListener(this);
        mButtonDay2.setOnClickListener(this);
        mButtonDay3.setOnClickListener(this);
        mButtonDay4.setOnClickListener(this);
        mButtonDay5.setOnClickListener(this);
        mButtonDay6.setOnClickListener(this);
        mButtonDay7.setOnClickListener(this);

        if (mMoodHistoric != null) {
            mSplit = mMoodHistoric.split(";");
            mLongBoard = mSplit.length;
        }
        if (mLongBoard >= 1){
            mDay1 = mSplit[0].split(",");
            int position = Integer.parseInt(mDay1[0]);
            mButtonDay1.setBackgroundColor(colors[position]);
            if (!mDay1[1].equals("null")){
                mButtonDay1.setCompoundDrawablesWithIntrinsicBounds(null,null,mImage,null);
            }
            PercentLayoutHelper.PercentLayoutParams params = (PercentRelativeLayout.LayoutParams) mButtonDay1.getLayoutParams();
            PercentLayoutHelper.PercentLayoutInfo info = params.getPercentLayoutInfo();
            info.widthPercent = (position+1)*0.20f ;
            mButtonDay1.requestLayout();
            mButtonDay1.setTag(mDay1[1]);
        }else {
            mButtonDay1.setEnabled(false);
        }
        if (mLongBoard >= 2){
            mDay2 = mSplit[1].split(",");
            int position = Integer.parseInt(mDay2[0]);
            mButtonDay2.setBackgroundColor(colors[position]);
            if (!mDay2[1].equals("null")){
                mButtonDay2.setCompoundDrawablesWithIntrinsicBounds(null,null,mImage,null);
            }
            PercentLayoutHelper.PercentLayoutParams params = (PercentRelativeLayout.LayoutParams) mButtonDay2.getLayoutParams();
            PercentLayoutHelper.PercentLayoutInfo info = params.getPercentLayoutInfo();
            info.widthPercent = (position+1)*0.20f ;
            mButtonDay2.requestLayout();
            mButtonDay2.setTag(mDay2[1]);
        }else {
            mButtonDay2.setEnabled(false);
        }
        if (mLongBoard >= 3){
            mDay3 = mSplit[2].split(",");
            int position = Integer.parseInt(mDay3[0]);
            mButtonDay3.setBackgroundColor(colors[position]);
            if (!mDay3[1].equals("null")){
                mButtonDay3.setCompoundDrawablesWithIntrinsicBounds(null,null,mImage,null);
            }
            PercentLayoutHelper.PercentLayoutParams params = (PercentRelativeLayout.LayoutParams) mButtonDay3.getLayoutParams();
            PercentLayoutHelper.PercentLayoutInfo info = params.getPercentLayoutInfo();
            info.widthPercent = (position+1)*0.20f ;
            mButtonDay3.requestLayout();
            mButtonDay3.setTag(mDay3[1]);
        }else {
            mButtonDay3.setEnabled(false);
        }
        if (mLongBoard >= 4){
            mDay4 = mSplit[3].split(",");
            int position = Integer.parseInt(mDay4[0]);
            mButtonDay4.setBackgroundColor(colors[position]);
            if (!mDay4[1].equals("null")){
                mButtonDay4.setCompoundDrawablesWithIntrinsicBounds(null,null,mImage,null);
            }
            PercentLayoutHelper.PercentLayoutParams params = (PercentRelativeLayout.LayoutParams) mButtonDay4.getLayoutParams();
            PercentLayoutHelper.PercentLayoutInfo info = params.getPercentLayoutInfo();
            info.widthPercent = (position+1)*0.20f ;
            mButtonDay4.requestLayout();
            mButtonDay4.setTag(mDay4[1]);
        }else {
            mButtonDay4.setEnabled(false);
        }
        if (mLongBoard >= 5){
            mDay5 = mSplit[4].split(",");
            int position = Integer.parseInt(mDay5[0]);
            mButtonDay5.setBackgroundColor(colors[position]);
            if (!mDay5[1].equals("null")){
                mButtonDay5.setCompoundDrawablesWithIntrinsicBounds(null,null,mImage,null);
            }
            PercentLayoutHelper.PercentLayoutParams params = (PercentRelativeLayout.LayoutParams) mButtonDay5.getLayoutParams();
            PercentLayoutHelper.PercentLayoutInfo info = params.getPercentLayoutInfo();
            info.widthPercent = (position+1)*0.20f ;
            mButtonDay5.requestLayout();
            mButtonDay5.setTag(mDay5[1]);
        }else {
            mButtonDay5.setEnabled(false);
        }
        if (mLongBoard >= 6){
            mDay6 = mSplit[5].split(",");
            int position = Integer.parseInt(mDay6[0]);
            mButtonDay6.setBackgroundColor(colors[position]);
            if (!mDay6[1].equals("null")){
                mButtonDay6.setCompoundDrawablesWithIntrinsicBounds(null,null,mImage,null);
            }
            PercentLayoutHelper.PercentLayoutParams params = (PercentRelativeLayout.LayoutParams) mButtonDay6.getLayoutParams();
            PercentLayoutHelper.PercentLayoutInfo info = params.getPercentLayoutInfo();
            info.widthPercent = (position+1)*0.20f ;
            mButtonDay6.requestLayout();
            mButtonDay6.setTag(mDay6[1]);
        }else {
            mButtonDay6.setEnabled(false);
        }
        if (mLongBoard >= 7){
            mDay7 = mSplit[6].split(",");
            int position = Integer.parseInt(mDay7[0]);
            mButtonDay7.setBackgroundColor(colors[position]);
            if (!mDay7[1].equals("null")){
                mButtonDay7.setCompoundDrawablesWithIntrinsicBounds(null,null,mImage,null);
            }
            PercentLayoutHelper.PercentLayoutParams params = (PercentRelativeLayout.LayoutParams) mButtonDay7.getLayoutParams();
            PercentLayoutHelper.PercentLayoutInfo info = params.getPercentLayoutInfo();
            info.widthPercent = (position+1)*0.20f ;
            mButtonDay7.requestLayout();
            mButtonDay7.setTag(mDay7[1]);
        }else {
            mButtonDay7.setEnabled(false);
        }
    }

    @Override
    public void onClick(View v) {
        String commentIndex = (String) v.getTag();
        if (!commentIndex.equals("null")) {
            Toast.makeText(this, commentIndex, Toast.LENGTH_LONG).show();
        }
    }
    protected void extractBackup(){
            mMoodHistoric = getSharedPreferences("MyMood", MODE_PRIVATE).getString(KEY_MOOD_SAVE, null);
    }
}
