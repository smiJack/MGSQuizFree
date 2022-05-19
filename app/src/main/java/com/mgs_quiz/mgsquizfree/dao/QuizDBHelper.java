package com.mgs_quiz.mgsquizfree.dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import com.mgs_quiz.mgsquizfree.R;
import com.mgs_quiz.mgsquizfree.dao.QuizDBData.QuestionsTable;
import com.mgs_quiz.mgsquizfree.model.Question;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static com.mgs_quiz.mgsquizfree.GameData.CAT_ALL;
import static com.mgs_quiz.mgsquizfree.GameData.EXPERT_CATS;
import static com.mgs_quiz.mgsquizfree.GameData.QUESTIONS_FOR_QUIZ;

public class QuizDBHelper extends SQLiteOpenHelper {

    private final static String DATABASE_NAME = "MGSQuiz.db";
    private final static int DATABASE_VERSION = 5;
    // DATABASE_VERSION=5 => 10.11.20

    private SQLiteDatabase db;

    private Context context;

    private static QuizDBHelper instance;

    private QuizDBHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
        this.context = context;
    }

    public static synchronized QuizDBHelper getInstance(Context context) {
        if (instance == null) {
            instance = new QuizDBHelper(context.getApplicationContext());
        }
        return instance;
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        // reference to created database outside of this method
        db = sqLiteDatabase;

        final String SQL_CREATE_QUESTIONS_TABLE = "CREATE TABLE " + QuestionsTable.TABLE_NAME +
                " ( " + QuestionsTable._ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                QuestionsTable.COLUMN_CATEGORY + " TEXT, " +
                QuestionsTable.COLUMN_DIFFICULTY + " TEXT, " +
                QuestionsTable.COLUMN_QUESTION + " TEXT, " +
                QuestionsTable.COLUMN_OPTION_C + " TEXT, " +
                QuestionsTable.COLUMN_OPTION1 + " TEXT, " +
                QuestionsTable.COLUMN_OPTION2 + " TEXT, " +
                QuestionsTable.COLUMN_OPTION3 + " TEXT, " +
                QuestionsTable.COLUMN_OPTION4 + " TEXT " +
                ");";

        final String SQL_CREATE_QUESTIONS_TABLE_EN = "CREATE TABLE " + QuestionsTable.TABLE_NAME_EN +
                " ( " + QuestionsTable._ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                QuestionsTable.COLUMN_CATEGORY + " TEXT, " +
                QuestionsTable.COLUMN_DIFFICULTY + " TEXT, " +
                QuestionsTable.COLUMN_QUESTION + " TEXT, " +
                QuestionsTable.COLUMN_OPTION_C + " TEXT, " +
                QuestionsTable.COLUMN_OPTION1 + " TEXT, " +
                QuestionsTable.COLUMN_OPTION2 + " TEXT, " +
                QuestionsTable.COLUMN_OPTION3 + " TEXT, " +
                QuestionsTable.COLUMN_OPTION4 + " TEXT " +
                ");";

        final String SQL_CREATE_QUESTIONS_TABLE_RU = "CREATE TABLE " + QuestionsTable.TABLE_NAME_RU +
                " ( " + QuestionsTable._ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                QuestionsTable.COLUMN_CATEGORY + " TEXT, " +
                QuestionsTable.COLUMN_DIFFICULTY + " TEXT, " +
                QuestionsTable.COLUMN_QUESTION + " TEXT, " +
                QuestionsTable.COLUMN_OPTION_C + " TEXT, " +
                QuestionsTable.COLUMN_OPTION1 + " TEXT, " +
                QuestionsTable.COLUMN_OPTION2 + " TEXT, " +
                QuestionsTable.COLUMN_OPTION3 + " TEXT, " +
                QuestionsTable.COLUMN_OPTION4 + " TEXT " +
                ");";

        try {
            db.execSQL(SQL_CREATE_QUESTIONS_TABLE);
            db.execSQL(SQL_CREATE_QUESTIONS_TABLE_EN);
            db.execSQL(SQL_CREATE_QUESTIONS_TABLE_RU);
        } catch (Exception e) {
            Log.e("QuizDBHelper", "Create Table Error " + e.getMessage());
            e.printStackTrace();
        }

        fillQuestionsTable(R.raw.fragen_de, QuestionsTable.TABLE_NAME);
        fillQuestionsTable(R.raw.fragen_en, QuestionsTable.TABLE_NAME_EN);
        fillQuestionsTable(R.raw.fragen_ru, QuestionsTable.TABLE_NAME_RU);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + QuestionsTable.TABLE_NAME);
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + QuestionsTable.TABLE_NAME_EN);
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + QuestionsTable.TABLE_NAME_RU);
        onCreate(sqLiteDatabase);
    }

    private void fillQuestionsTable(int rawResource, String tableName) {
        InputStream is = context.getResources().openRawResource(rawResource);

        BufferedReader reader = new BufferedReader(
                new InputStreamReader(is));

        String line = "";

        try {
            while ((line = reader.readLine()) != null) {
                String[] tokens = line.split(";");

                Question question = new Question();
                question.setCategory(tokens[0]);
                question.setDifficultyLevel(tokens[1]);
                question.setQuestion(tokens[3]);
                question.setOptionC(tokens[2]);
                question.setOption1(tokens[4]);
                question.setOption2(tokens[5]);
                question.setOption3(tokens[6]);
                question.setOption4(tokens[7]);

                addQuestion(question, tableName);
            }
        } catch (IOException e1) {
            Log.e("QuizDBHelper", "Error" + line, e1);
            e1.printStackTrace();
        }
    }

    // save questions in database
    private void addQuestion(Question question, String TableName) {
        ContentValues contentValues = new ContentValues();
        contentValues.put(QuestionsTable.COLUMN_CATEGORY, question.getCategory());
        contentValues.put(QuestionsTable.COLUMN_DIFFICULTY, question.getDifficultyLevel());
        contentValues.put(QuestionsTable.COLUMN_QUESTION, question.getQuestion());
        contentValues.put(QuestionsTable.COLUMN_OPTION_C, question.getOptionC());
        contentValues.put(QuestionsTable.COLUMN_OPTION1, question.getOption1());
        contentValues.put(QuestionsTable.COLUMN_OPTION2, question.getOption2());
        contentValues.put(QuestionsTable.COLUMN_OPTION3, question.getOption3());
        contentValues.put(QuestionsTable.COLUMN_OPTION4, question.getOption4());

        long lng = db.insert(TableName, null, contentValues);
    }

    public List<Question> getAllQuestions() {
        List<Question> questionsList = new ArrayList<>();
        db = getWritableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM " + QuestionsTable.TABLE_NAME, null);

        if (cursor.moveToFirst()) {
            do{
                int categoryColumn = cursor.getColumnIndex(QuestionsTable.COLUMN_CATEGORY);
                int difficultyColumn = cursor.getColumnIndex(QuestionsTable.COLUMN_DIFFICULTY);
                int questionColumn = cursor.getColumnIndex(QuestionsTable.COLUMN_QUESTION);
                int optionCColumn = cursor.getColumnIndex(QuestionsTable.COLUMN_OPTION_C);
                int option1Column = cursor.getColumnIndex(QuestionsTable.COLUMN_OPTION1);
                int option2Column = cursor.getColumnIndex(QuestionsTable.COLUMN_OPTION2);
                int option3Column = cursor.getColumnIndex(QuestionsTable.COLUMN_OPTION3);
                int option4Column = cursor.getColumnIndex(QuestionsTable.COLUMN_OPTION4);

                Question question = new Question();
                question.setCategory(cursor.getString(categoryColumn));
                question.setDifficultyLevel(cursor.getString(difficultyColumn));
                question.setQuestion(cursor.getString(questionColumn));
                question.setOptionC(cursor.getString(optionCColumn));
                question.setOption1(cursor.getString(option1Column));
                question.setOption2(cursor.getString(option2Column));
                question.setOption3(cursor.getString(option3Column));
                question.setOption4(cursor.getString(option4Column));

                questionsList.add(question);
            } while (cursor.moveToNext());
        }
        cursor.close();
        db.close();
        Collections.shuffle(questionsList);
        return questionsList;
    }

    // retrieve questions from the database which have certain difficulty level
    public ArrayList<Question> getAllQuestionsOfCategory(String category, String difficultyLevel, String lang) {

        db = getWritableDatabase(); // todo löschen - this will call onCreate Method

        String table = "";
        switch (lang.toLowerCase()) {
            case "de":
                table = QuestionsTable.TABLE_NAME;
                break;
            case "en":
                table = QuestionsTable.TABLE_NAME_EN;
                break;
            case "ru":
                table = QuestionsTable.TABLE_NAME_RU;
                break;
            default:
                table = QuestionsTable.TABLE_NAME_EN;
        }

        // ? is just a placeholder for selectionArgs
        String selection = "";
        String selectionArgs[];
        if (category.equals(CAT_ALL)) {
            ArrayList<Question> questions = new ArrayList<Question>();
            String cats[] = EXPERT_CATS.split(",");

            int categoryNo = 1;
            for (String s : cats) {
                selection = QuestionsTable.COLUMN_CATEGORY + " = ? " +
                        " AND " + QuestionsTable.COLUMN_DIFFICULTY + " = ? ";
                selectionArgs = new String[]{s, difficultyLevel};

                // columns null = return all columns
                Cursor cursor = db.query(table, null,
                        selection, selectionArgs, null, null, null);

                questions.addAll(getQuestionsList(cursor));
                categoryNo++;
            }
            Collections.shuffle(questions);
            return questions;
        }
        selection = QuestionsTable.COLUMN_CATEGORY + " = ? " +
                " AND " + QuestionsTable.COLUMN_DIFFICULTY + " = ? ";
        selectionArgs = new String[]{category, difficultyLevel};

        // columns null = return all columns
        Cursor cursor = db.query(table, null,
                selection, selectionArgs, null, null, null);

        return getQuestionsList(cursor);
    }

    private ArrayList<Question> getQuestionsList(Cursor cursor) {
        List<Question> questionsList = new ArrayList<>();
        if (cursor.moveToFirst()) {
            do{
                int categoryColumn = cursor.getColumnIndex(QuestionsTable.COLUMN_CATEGORY);
                int difficultyColumn = cursor.getColumnIndex(QuestionsTable.COLUMN_DIFFICULTY);
                int questionColumn = cursor.getColumnIndex(QuestionsTable.COLUMN_QUESTION);
                int optionCColumn = cursor.getColumnIndex(QuestionsTable.COLUMN_OPTION_C);
                int option1Column = cursor.getColumnIndex(QuestionsTable.COLUMN_OPTION1);
                int option2Column = cursor.getColumnIndex(QuestionsTable.COLUMN_OPTION2);
                int option3Column = cursor.getColumnIndex(QuestionsTable.COLUMN_OPTION3);
                int option4Column = cursor.getColumnIndex(QuestionsTable.COLUMN_OPTION4);

                Question question = new Question();
                question.setCategory(cursor.getString(categoryColumn));
                question.setDifficultyLevel(cursor.getString(difficultyColumn));
                question.setQuestion(cursor.getString(questionColumn));
                question.setOptionC(cursor.getString(optionCColumn));
                question.setOption1(cursor.getString(option1Column));
                question.setOption2(cursor.getString(option2Column));
                question.setOption3(cursor.getString(option3Column));
                question.setOption4(cursor.getString(option4Column));

                questionsList.add(question);
            } while (cursor.moveToNext());
        }
        cursor.close();
        Collections.shuffle(questionsList);
        questionsList = questionsList.subList(0, QUESTIONS_FOR_QUIZ);

        ArrayList<Question> arrayList = new ArrayList<>();
        arrayList.addAll(questionsList);
        return arrayList;
    }
}
