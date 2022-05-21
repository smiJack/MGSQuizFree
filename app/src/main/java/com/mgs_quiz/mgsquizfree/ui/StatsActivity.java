package com.mgs_quiz.mgsquizfree.ui;

import static com.mgs_quiz.mgsquizfree.AppAdRequest.getAdRequest;
import static com.mgs_quiz.mgsquizfree.GameData.SP_DEF;
import static com.mgs_quiz.mgsquizfree.GameData.SP_KEYS_SCORE;
import static com.mgs_quiz.mgsquizfree.GameData.SP_NAMES;
import static com.mgs_quiz.mgsquizfree.GameData.SP_NAME_EEA;
import static com.mgs_quiz.mgsquizfree.GameData.SP_QTOTALAA_KEY;
import static com.mgs_quiz.mgsquizfree.GameData.SP_QTOTALDA_KEY;
import static com.mgs_quiz.mgsquizfree.GameData.SP_SCOT_KEY;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.fragment.app.FragmentStatePagerAdapter;
import androidx.viewpager.widget.PagerAdapter;
import androidx.viewpager.widget.ViewPager;

import com.google.android.gms.ads.AdListener;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.LoadAdError;
import com.google.android.gms.ads.MobileAds;
import com.google.android.gms.ads.RequestConfiguration;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.tabs.TabLayout;
import com.mgs_quiz.mgsquizfree.R;

public class StatsActivity extends AppCompatActivity {

    /**
     * The {@link PagerAdapter} that will provide
     * fragments for each of the sections. We use a
     * {@link FragmentPagerAdapter} derivative, which will keep every
     * loaded fragment in memory. If this becomes too memory intensive, it
     * may be best to switch to a
     * {@link FragmentStatePagerAdapter}.
     */
    private SectionsPagerAdapter mSectionsPagerAdapter;

    /**
     * The {@link ViewPager} that will host the section contents.
     */
    private ViewPager mViewPager;

    private AlertDialog.Builder builder;
    private Context context;
    private SharedPreferences sharedPrefs;

    private StatsHighest statsHighest;
    private StatsOthers statsOthers;
    private StatsTotal statsTotal;
    private StatsTime statsTime;
    private String[] keys;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_stats);

        SharedPreferences eea = getSharedPreferences(SP_NAME_EEA, MODE_PRIVATE);
        final TextView placeholder = findViewById(R.id.statsAdPlaceholder);

        RequestConfiguration configuration =
                new RequestConfiguration.Builder().build();
        MobileAds.setRequestConfiguration(configuration);

        AdView view = findViewById(R.id.statsView);
        AdRequest request = getAdRequest(eea);
        view.setAdListener(new AdListener(){
            @Override
            public void onAdFailedToLoad(LoadAdError adError) {
                placeholder.setVisibility(View.VISIBLE);
            }

            @Override
            public void onAdLoaded() {
                placeholder.setVisibility(View.GONE);
            }
        });
        view.loadAd(request);


        // Create the adapter that will return a fragment for each of the three
        // primary sections of the activity.
        mSectionsPagerAdapter = new SectionsPagerAdapter(getSupportFragmentManager());

        // Set up the ViewPager with the sections adapter.
        mViewPager = (ViewPager) findViewById(R.id.container);
        mViewPager.setAdapter(mSectionsPagerAdapter);

        TabLayout tabLayout = (TabLayout) findViewById(R.id.tabsStats);

        mViewPager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tabLayout));
        tabLayout.addOnTabSelectedListener(new TabLayout.ViewPagerOnTabSelectedListener(mViewPager));

        context = this;
        sharedPrefs = getApplicationContext().getSharedPreferences(SP_NAMES, MODE_PRIVATE);

        statsHighest = new StatsHighest();
        statsOthers = new StatsOthers();
        statsTotal = new StatsTotal();
        statsTime = new StatsTime();
        keys = SP_KEYS_SCORE;

        final int questions = sharedPrefs.getInt(SP_QTOTALAA_KEY, SP_DEF) + sharedPrefs.getInt(SP_QTOTALDA_KEY, SP_DEF);
        builder = new AlertDialog.Builder(context);
        final DialogInterface.OnClickListener listener = new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.cancel();
                switch (which) {
                    case 0:
                        shareResults(getString(R.string.shareQuestionsAnsweredTotal, questions));
                        break;
                    case 1:
                        shareResults(getString(R.string.shareScoreOverall, sharedPrefs.getInt(SP_SCOT_KEY, 0)));
                        break;
                    case 2:
                        shareResults(getString(R.string.shareScoreTotal, sharedPrefs.getInt(keys[0], 0),
                                getString(R.string.tagMGS1), getString(R.string.difficultyNormal)));
                        break;
                    case 3:
                        shareResults(getString(R.string.shareScoreTotal, sharedPrefs.getInt(keys[1], 0),
                                getString(R.string.tagMGS1), getString(R.string.difficultyHard)));
                        break;
                    case 4:
                        shareResults(getString(R.string.shareScoreTotal, sharedPrefs.getInt(keys[2], 0),
                                getString(R.string.tagMGS2), getString(R.string.difficultyNormal)));
                        break;
                    case 5:
                        shareResults(getString(R.string.shareScoreTotal, sharedPrefs.getInt(keys[3], 0),
                                getString(R.string.tagMGS2), getString(R.string.difficultyHard)));
                        break;
                    case 6:
                        shareResults(getString(R.string.shareScoreTotal, sharedPrefs.getInt(keys[4], 0),
                                getString(R.string.tagMGS3), getString(R.string.difficultyNormal)));
                        break;
                    case 7:
                        shareResults(getString(R.string.shareScoreTotal, sharedPrefs.getInt(keys[5], 0),
                                getString(R.string.tagMGS3), getString(R.string.difficultyHard)));
                        break;
                    case 8:
                        shareResults(getString(R.string.shareScoreTotal, sharedPrefs.getInt(keys[6], 0),
                                getString(R.string.tagMGS4), getString(R.string.difficultyNormal)));
                        break;
                    case 9:
                        shareResults(getString(R.string.shareScoreTotal, sharedPrefs.getInt(keys[7], 0),
                                getString(R.string.tagMGS4), getString(R.string.difficultyHard)));
                        break;
                    case 10:
                        shareResults(getString(R.string.shareScoreTotal, sharedPrefs.getInt(keys[8], 0),
                                getString(R.string.tagMGS5), getString(R.string.difficultyNormal)));
                        break;
                    case 11:
                        shareResults(getString(R.string.shareScoreTotal, sharedPrefs.getInt(keys[9], 0),
                                getString(R.string.tagMGS5), getString(R.string.difficultyHard)));
                        break;
                    default:
                        shareResults(getString(R.string.shareScoreOverallNew, sharedPrefs.getInt(SP_SCOT_KEY, 0)));
                        break;
                }
            }
        };

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.statsFabShare);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                builder.setTitle(Html.fromHtml(getString(R.string.shareTitle)))
                        .setNegativeButton(R.string.shareCancel, null)
                        .setPositiveButton("OK", null)
                        .setCancelable(true)
                        .setSingleChoiceItems(R.array.shareScore, -1, listener);
                builder.create().show();
            }
        });
    }

    private void shareResults(String message) {
        Intent app = new Intent(Intent.ACTION_SEND);
        app.setType("text/plain");
        app.putExtra(Intent.EXTRA_SUBJECT, getString(R.string.app_name));
        app.putExtra(Intent.EXTRA_TEXT, message);

        try {
            startActivity(Intent.createChooser(app, getString(R.string.shareSelectApp)));
        } catch (Exception e) {
            Toast.makeText(this, getString(R.string.eGeneral), Toast.LENGTH_LONG).show();
        }
    }

    /**
     * A placeholder fragment containing a simple view.
     */
    public static class PlaceholderFragment extends Fragment {
        /**
         * The fragment argument representing the section number for this
         * fragment.
         */
        private static final String ARG_SECTION_NUMBER = "section_number";

        public PlaceholderFragment() {
        }

        /**
         * Returns a new instance of this fragment for the given section
         * number.
         */
        public static PlaceholderFragment newInstance(int sectionNumber) {
            PlaceholderFragment fragment = new PlaceholderFragment();
            Bundle args = new Bundle();
            args.putInt(ARG_SECTION_NUMBER, sectionNumber);
            fragment.setArguments(args);
            return fragment;
        }

        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container,
                                 Bundle savedInstanceState) {
            View rootView = inflater.inflate(R.layout.fragment_stats, container, false);
            return rootView;
        }
    }

    /**
     * A {@link FragmentPagerAdapter} that returns a fragment corresponding to
     * one of the sections/tabs/pages.
     */
    public class SectionsPagerAdapter extends FragmentPagerAdapter {

        public SectionsPagerAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            // getItem is called to instantiate the fragment for the given page.
            // Return a PlaceholderFragment (defined as a static inner class below).

            switch (position) {
                case 0:
                    return statsHighest;
                case 1:
                    return statsTotal;
                case 2:
                    return statsTime;
            }
            return statsOthers;
        }

        @Override
        public int getCount() {
            // Show 4 total pages.
            return 4;
        }
    }
}
