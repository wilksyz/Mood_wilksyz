package com.company.antoine.moodtracker.Controllers.Fragments;

import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.AlertDialog;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import com.company.antoine.moodtracker.Controllers.Activities.HistoricActivity;
import com.company.antoine.moodtracker.R;
import static android.content.Context.MODE_PRIVATE;

/**
 * A simple {@link Fragment} subclass.
 * this fragment allows to create the pages displayed in the viewpager
 */
public class PageViewPager extends Fragment {

    private static final String KEY_POSITION_SAVE = "position save";
    private static final String KEY_COLOR = "color";
    private static final String KEY_IMAGE = "image";
    private static final String KEY_COMMENT_SAVE = "comment save";
    private static final int[] mResImage = {R.drawable.smiley_sad,
                                            R.drawable.smiley_disappointed,
                                            R.drawable.smiley_normal,
                                            R.drawable.smiley_happy,
                                            R.drawable.smiley_super_happy};
    private static final String[] mMoodShare = {"de très mauvaise humeur","triste","indécis","de bonne humeur","joyeux"};

    public PageViewPager() {
    }

    /**
     * Method that will create a new instance of PageViewPager.
     * @param pPosition get back the position of the viewpager that we get from the pageadapter
     * @param pColor color index chosen in the color chart according to the position of the viewpager
     */
    public static PageViewPager newInstance(int pPosition, int pColor) {

        PageViewPager mFrag = new PageViewPager();

        Bundle args = new Bundle();
        args.putInt(KEY_COLOR, pColor);
        args.putInt(KEY_IMAGE,mResImage[pPosition]);
        mFrag.setArguments(args);

        return(mFrag);
    }

    /**
     *In the Method onCreateView:
     *We recover the identifiers of the views to be defined.
     *The background color and the image are defined according to the position of the Viewpager.
     *We get the clicks on the buttons common to all the pages of the viewpager.
     */
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
       View result = inflater.inflate(R.layout.view_pager_page, container, false);

        RelativeLayout mColorLayout;
        ImageView mSmileyMood;
        ImageButton mButtonComment;
        ImageButton mButtonHistoric;
        ImageButton mButtonShare;

        mColorLayout = result.findViewById(R.id.fragment_page_relative_layout);
        mSmileyMood = result.findViewById(R.id.fragment_page_smiley_view);
        mButtonComment = result.findViewById(R.id.fragment_page_btn_comment);
        mButtonHistoric = result.findViewById(R.id.fragment_page_btn_historic);
        mButtonShare = result.findViewById(R.id.fragment_page_btn_share);

        int color = getArguments().getInt(KEY_COLOR, -1);
        int  image = getArguments().getInt(KEY_IMAGE,-1);

        mColorLayout.setBackgroundColor(color);
        mSmileyMood.setImageResource(image);

        //When the user clicks the history button the onClick method starts the history activity.
        mButtonHistoric.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), HistoricActivity.class);
                startActivity(intent);
            }
        });

        //When the user wants to add a comment we open a pop up window where we find a text entry field and two buttons to cancel or validate the comment.
        mButtonComment.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());

                LayoutInflater factory = LayoutInflater.from(getActivity());
                final View alertDialogView = factory.inflate(R.layout.alert_dialog_comment, null);

                builder.setTitle("Commentaire :")
                        .setView(alertDialogView)
                        .setPositiveButton("Valider", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                EditText pTextComment = alertDialogView.findViewById(R.id.alert_dialog_edit_comment);
                                String pComment = pTextComment.getText().toString();
                                Log.e(getClass().getSimpleName(),pComment);
                                SharedPreferences preferences = getActivity().getSharedPreferences("MyMood", MODE_PRIVATE);
                                preferences.edit().putString(KEY_COMMENT_SAVE, pComment)
                                        .apply();
                            }
                        })
                        .setNegativeButton("Annuler",
                                new DialogInterface.OnClickListener() {
                                    public void onClick(DialogInterface dialog, int whichButton) {

                                    }
                                })
                        .create()
                        .show();
            }
        });

        //This method click on the button and allows the sharing of mood via a third-party application.
        //If there is a comment he is also to share.
        mButtonShare.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int pPosition =getActivity().getSharedPreferences("MyMood", MODE_PRIVATE).getInt(KEY_POSITION_SAVE, 3);
                String pComment = getActivity().getSharedPreferences("MyMood", MODE_PRIVATE).getString(KEY_COMMENT_SAVE, "et j'ai rien de plus a dire");
                Intent shareIntent = new Intent();
                shareIntent.setAction(Intent.ACTION_SEND);
                shareIntent.setType("text/plain");
                shareIntent.putExtra(Intent.EXTRA_TEXT, "Aujourd'hui je suis "+mMoodShare[pPosition]+" "+pComment);
                startActivity(Intent.createChooser(shareIntent,getResources().getString(R.string.share_tittle)));

            }
        });
        return result;
    }
}
