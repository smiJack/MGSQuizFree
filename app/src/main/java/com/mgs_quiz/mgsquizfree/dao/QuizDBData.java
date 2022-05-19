package com.mgs_quiz.mgsquizfree.dao;

import android.provider.BaseColumns;

public final class QuizDBData {

    private QuizDBData(){}

    private static class Category {
        public static final String MGS1 = "mgs1";
        public static final String MGS2 = "mgs2";
    }

    public static class QuestionsTable implements BaseColumns {
        public final static String TABLE_NAME = "questionsDE";
        public final static String TABLE_NAME_EN = "questionsEN";
        public final static String TABLE_NAME_RU = "questionsRU";
        // only _id field from BaseColumns will be used
        public static final String COLUMN_CATEGORY = "category";
        public final static String COLUMN_DIFFICULTY = "difficulty";
        public final static String COLUMN_QUESTION = "question";
        public final static String COLUMN_OPTION_C = "optionC";
        public final static String COLUMN_OPTION1 = "option1";
        public final static String COLUMN_OPTION2 = "option2";
        public final static String COLUMN_OPTION3 = "option3";
        public final static String COLUMN_OPTION4 = "option4";
    }
}
