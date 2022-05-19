package com.mgs_quiz.mgsquizfree.ui;

import android.os.Bundle;
import android.view.View;

import com.mgs_quiz.mgsquizfree.LogMessage;
import com.mgs_quiz.mgsquizfree.R;

import java.util.HashMap;

import androidx.appcompat.app.AppCompatActivity;

public class FAQsActivity extends AppCompatActivity {

    private HashMap<Integer, Integer> menu;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_faqs);

        initE();
    }

    private void initE() {
        menu = new HashMap<>();
        menu.put(R.id.faqsLblQLangs, R.id.faqsTVQLangs);
        menu.put(R.id.faqsLblPoints, R.id.faqsTVPoints);
        menu.put(R.id.faqsLblTimePenalty, R.id.faqsTVTimePenalty);
        menu.put(R.id.faqsLblLockedSections, R.id.faqsTVLockedSections);
    }

    public void expandMenu(View view) {
        try {
            if (menu.containsKey(view.getId())) {
                View menuItem = findViewById(menu.get(view.getId()));
                if (menuItem.getVisibility() != View.VISIBLE) {
                    menuItem.setVisibility(View.VISIBLE);
                } else {
                    menuItem.setVisibility(View.GONE);
                }
            }
        } catch (Exception e) {
            LogMessage.save(this, e.getMessage());
        }
    }
}
