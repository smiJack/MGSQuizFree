package com.mgs_quiz.mgsquizfree.ui;

import static com.mgs_quiz.mgsquizfree.AppAdRequest.getAdRequest;
import static com.mgs_quiz.mgsquizfree.GameData.BKEY_RANKING;
import static com.mgs_quiz.mgsquizfree.GameData.SP_NAMEEA;
import static com.mgs_quiz.mgsquizfree.GameData.SP_NAMEED;
import static com.mgs_quiz.mgsquizfree.GameData.SP_NAMEQ;
import static com.mgs_quiz.mgsquizfree.GameData.SP_NAMES_EXTENSION;
import static com.mgs_quiz.mgsquizfree.GameData.SP_NAMETS;
import static com.mgs_quiz.mgsquizfree.GameData.SP_NAME_EEA;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.viewpager.widget.ViewPager;

import com.google.android.gms.ads.AdListener;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.LoadAdError;
import com.google.android.gms.ads.MobileAds;
import com.google.android.gms.ads.RequestConfiguration;
import com.google.android.material.tabs.TabLayout;
import com.mgs_quiz.mgsquizfree.LogMessage;
import com.mgs_quiz.mgsquizfree.R;
import com.mgs_quiz.mgsquizfree.model.Ranking;

import java.io.BufferedInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.ObjectInputStream;

public class WorldActivity extends AppCompatActivity {

    private SectionsPagerAdapter mSectionsPagerAdapter;

    /**
     * The {@link ViewPager} that will host the section contents.
     */
    private ViewPager mViewPager;

    private WorldFragmentQuestions worldAnsweredQuestions;
    private WorldFragmentExpertA worldExpertA;
    private WorldFragmentExpertB worldExpertB;
    private WorldFragmentScore worldScore;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_world);

        SharedPreferences eea = getSharedPreferences(SP_NAME_EEA, MODE_PRIVATE);
        final TextView placeholder = findViewById(R.id.worldAdPlaceholder);

        RequestConfiguration configuration =
                new RequestConfiguration.Builder().build();
        MobileAds.setRequestConfiguration(configuration);

        AdView view = findViewById(R.id.worldView);
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

//        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
//        setSupportActionBar(toolbar);
        // Create the adapter that will return a fragment for each of the three
        // primary sections of the activity.
        mSectionsPagerAdapter = new SectionsPagerAdapter(getSupportFragmentManager());

        // Set up the ViewPager with the sections adapter.
        mViewPager = (ViewPager) findViewById(R.id.container);
        mViewPager.setAdapter(mSectionsPagerAdapter);

        TabLayout tabLayout = (TabLayout) findViewById(R.id.tabsWorld);

        mViewPager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tabLayout));
        tabLayout.addOnTabSelectedListener(new TabLayout.ViewPagerOnTabSelectedListener(mViewPager));

        worldAnsweredQuestions = new WorldFragmentQuestions();
        worldAnsweredQuestions.setArguments(getBundle((SP_NAMEQ + SP_NAMES_EXTENSION)));
        worldExpertA = new WorldFragmentExpertA();
        worldExpertA.setArguments(getBundle((SP_NAMEEA + SP_NAMES_EXTENSION)));
        worldExpertB = new WorldFragmentExpertB();
        worldExpertB.setArguments(getBundle(SP_NAMEED + SP_NAMES_EXTENSION));
        worldScore = new WorldFragmentScore();
        worldScore.setArguments(getBundle((SP_NAMETS + SP_NAMES_EXTENSION)));
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_world, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
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
            View rootView = inflater.inflate(R.layout.fragment_world, container, false);
            return rootView;
        }
    }

    public class SectionsPagerAdapter extends FragmentPagerAdapter {

        public SectionsPagerAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            switch (position) {
                case 0:
                    return worldAnsweredQuestions;
                case 1:
                    return worldExpertA;
                case 2:
                    return worldExpertB;
            }
            return worldScore;
        }

        @Override
        public int getCount() {
            return 4;
        }
    }

    private Bundle getBundle(String file) {
        Ranking ranking = null;
        try (ObjectInputStream rankingData = new ObjectInputStream(new BufferedInputStream(openFileInput(file)))) {
            ranking = (Ranking) rankingData.readObject();
        } catch (FileNotFoundException e) {
            LogMessage.save(getApplicationContext(), (e.getMessage() + "\ngetRanking() FileNotFound"));
        } catch (ClassNotFoundException e) {
            LogMessage.save(getApplicationContext(), (e.getMessage() + "\ngetRanking() ClassNotFound"));
        } catch (IOException e) {
            LogMessage.save(getApplicationContext(), (e.getMessage() + "\ngetRanking() IO"));
        }
        Bundle bundle = new Bundle();
        bundle.putSerializable(BKEY_RANKING, ranking);
        return bundle;
    }
}
