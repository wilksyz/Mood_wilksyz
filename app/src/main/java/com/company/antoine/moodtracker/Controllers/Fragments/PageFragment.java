package com.company.antoine.moodtracker.Controllers.Fragments;


import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import com.company.antoine.moodtracker.Controllers.Activities.HistoricActivity;
import com.company.antoine.moodtracker.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class PageFragment extends Fragment {

    private static final String KEY_POSITION = "position";
    private static final String KEY_COLOR = "color";
    private static final String KEY_IMAGE = "image";
    private static final int[] mResImage = {R.drawable.smiley_sad,
                                            R.drawable.smiley_disappointed,
                                            R.drawable.smiley_normal,
                                            R.drawable.smiley_happy,
                                            R.drawable.smiley_super_happy};



    public PageFragment() {

    }

    public static PageFragment newInstance(int pPosition, int pColor) {

        PageFragment mFrag = new PageFragment();

        Bundle args = new Bundle();
        args.putInt(KEY_POSITION, pPosition);
        args.putInt(KEY_COLOR, pColor);
        args.putInt(KEY_IMAGE,mResImage[pPosition]);
        mFrag.setArguments(args);

        return(mFrag);
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
       View result = inflater.inflate(R.layout.fragment_page, container, false);

        RelativeLayout mColorLayout;
        ImageView mSmileyMood;
        ImageButton mButtonComment;
        ImageButton mButtonHistoric;

        mColorLayout = result.findViewById(R.id.fragment_page_relative_layout);
        mSmileyMood = result.findViewById(R.id.fragment_page_smiley_view);
        mButtonComment = result.findViewById(R.id.fragment_page_btn_comment);
        mButtonHistoric = result.findViewById(R.id.fragment_page_btn_historic);

        int position = getArguments().getInt(KEY_POSITION, -1);
        int color = getArguments().getInt(KEY_COLOR, -1);
        int  image = getArguments().getInt(KEY_IMAGE,-1);

        mColorLayout.setBackgroundColor(color);
        mSmileyMood.setImageResource(image);

        mButtonHistoric.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), HistoricActivity.class);
                startActivity(intent);
            }
        });

        mButtonComment.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());

                LayoutInflater factory = LayoutInflater.from(getActivity());
                final View alertDialogView = factory.inflate(R.layout.alert_dialog_comment, null);

                builder.setTitle("comment :")
                        .setView(alertDialogView)
                        .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                EditText pText = alertDialogView.findViewById(R.id.alert_dialog_edit_comment);
                            }
                        })
                        .setNegativeButton("Cancel",
                                new DialogInterface.OnClickListener() {
                                    public void onClick(DialogInterface dialog, int whichButton) {

                                    }
                                })
                        .create()
                        .show();
            }
        });
        return result;
    }
}
