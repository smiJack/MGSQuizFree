package com.mgs_quiz.mgsquizfree;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.Point;
import android.util.DisplayMetrics;
import android.view.Display;
import android.view.WindowManager;
import android.widget.TextView;

import com.mgs_quiz.mgsquizfree.model.Stats;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import static com.mgs_quiz.mgsquizfree.GameData.ASCORE_LIMIT;
import static com.mgs_quiz.mgsquizfree.GameData.ASCORE_RATE;
import static com.mgs_quiz.mgsquizfree.GameData.CAT_ALL;
import static com.mgs_quiz.mgsquizfree.GameData.COUNTDOWN_IN_MILLIS;
import static com.mgs_quiz.mgsquizfree.GameData.DIFFICULTY_A;
import static com.mgs_quiz.mgsquizfree.GameData.DIFFICULTY_D;
import static com.mgs_quiz.mgsquizfree.GameData.DSCORE_LIMIT;
import static com.mgs_quiz.mgsquizfree.GameData.DSCORE_RATE;
import static com.mgs_quiz.mgsquizfree.GameData.SP_KEYDIF_AH;
import static com.mgs_quiz.mgsquizfree.GameData.SP_KEYDIF_AT;
import static com.mgs_quiz.mgsquizfree.GameData.SP_KEYDIF_DH;
import static com.mgs_quiz.mgsquizfree.GameData.SP_KEYDIF_DT;
import static com.mgs_quiz.mgsquizfree.GameData.SP_KEYQTA;
import static com.mgs_quiz.mgsquizfree.GameData.SP_KEYQTAA;
import static com.mgs_quiz.mgsquizfree.GameData.SP_KEYQTD;
import static com.mgs_quiz.mgsquizfree.GameData.SP_KEYQTDA;
import static com.mgs_quiz.mgsquizfree.GameData.SP_KEYS;
import static com.mgs_quiz.mgsquizfree.GameData.SP_KEYZEIT;
import static com.mgs_quiz.mgsquizfree.GameData.SP_NAMES;
import static com.mgs_quiz.mgsquizfree.GameData.SP_PREKEY;
import static com.mgs_quiz.mgsquizfree.GameData.SP_QCANSWERSA_KEY;
import static com.mgs_quiz.mgsquizfree.GameData.SP_QCANSWERSD_KEY;
import static com.mgs_quiz.mgsquizfree.GameData.SP_QTOTAL1A_KEY;
import static com.mgs_quiz.mgsquizfree.GameData.SP_QTOTAL1D_KEY;
import static com.mgs_quiz.mgsquizfree.GameData.SP_QTOTAL2A_KEY;
import static com.mgs_quiz.mgsquizfree.GameData.SP_QTOTAL2D_KEY;
import static com.mgs_quiz.mgsquizfree.GameData.SP_QTOTAL3A_KEY;
import static com.mgs_quiz.mgsquizfree.GameData.SP_QTOTAL3D_KEY;
import static com.mgs_quiz.mgsquizfree.GameData.SP_QTOTAL4A_KEY;
import static com.mgs_quiz.mgsquizfree.GameData.SP_QTOTAL4D_KEY;
import static com.mgs_quiz.mgsquizfree.GameData.SP_QTOTAL5A_KEY;
import static com.mgs_quiz.mgsquizfree.GameData.SP_QTOTAL5D_KEY;
import static com.mgs_quiz.mgsquizfree.GameData.SP_QTOTALA1A_KEY;
import static com.mgs_quiz.mgsquizfree.GameData.SP_QTOTALA1D_KEY;
import static com.mgs_quiz.mgsquizfree.GameData.SP_QTOTALA2A_KEY;
import static com.mgs_quiz.mgsquizfree.GameData.SP_QTOTALA2D_KEY;
import static com.mgs_quiz.mgsquizfree.GameData.SP_QTOTALA3A_KEY;
import static com.mgs_quiz.mgsquizfree.GameData.SP_QTOTALA3D_KEY;
import static com.mgs_quiz.mgsquizfree.GameData.SP_QTOTALA4A_KEY;
import static com.mgs_quiz.mgsquizfree.GameData.SP_QTOTALA4D_KEY;
import static com.mgs_quiz.mgsquizfree.GameData.SP_QTOTALA5A_KEY;
import static com.mgs_quiz.mgsquizfree.GameData.SP_QTOTALA5D_KEY;
import static com.mgs_quiz.mgsquizfree.GameData.SP_QTOTALAALLA_KEY;
import static com.mgs_quiz.mgsquizfree.GameData.SP_QTOTALAALLD_KEY;
import static com.mgs_quiz.mgsquizfree.GameData.SP_QTOTALAA_KEY;
import static com.mgs_quiz.mgsquizfree.GameData.SP_QTOTALALLA_KEY;
import static com.mgs_quiz.mgsquizfree.GameData.SP_QTOTALALLD_KEY;
import static com.mgs_quiz.mgsquizfree.GameData.SP_QTOTALA_KEY;
import static com.mgs_quiz.mgsquizfree.GameData.SP_QTOTALDA_KEY;
import static com.mgs_quiz.mgsquizfree.GameData.SP_QTOTALD_KEY;
import static com.mgs_quiz.mgsquizfree.GameData.SP_QTOTALOVA_KEY;
import static com.mgs_quiz.mgsquizfree.GameData.SP_QTOTALOV_KEY;
import static com.mgs_quiz.mgsquizfree.GameData.SP_SC1AHZ_KEY;
import static com.mgs_quiz.mgsquizfree.GameData.SP_SC1AH_KEY;
import static com.mgs_quiz.mgsquizfree.GameData.SP_SC1AT_KEY;
import static com.mgs_quiz.mgsquizfree.GameData.SP_SC1DHZ_KEY;
import static com.mgs_quiz.mgsquizfree.GameData.SP_SC1DH_KEY;
import static com.mgs_quiz.mgsquizfree.GameData.SP_SC1DT_KEY;
import static com.mgs_quiz.mgsquizfree.GameData.SP_SC2AHZ_KEY;
import static com.mgs_quiz.mgsquizfree.GameData.SP_SC2AH_KEY;
import static com.mgs_quiz.mgsquizfree.GameData.SP_SC2AT_KEY;
import static com.mgs_quiz.mgsquizfree.GameData.SP_SC2DHZ_KEY;
import static com.mgs_quiz.mgsquizfree.GameData.SP_SC2DH_KEY;
import static com.mgs_quiz.mgsquizfree.GameData.SP_SC2DT_KEY;
import static com.mgs_quiz.mgsquizfree.GameData.SP_SC3AHZ_KEY;
import static com.mgs_quiz.mgsquizfree.GameData.SP_SC3AH_KEY;
import static com.mgs_quiz.mgsquizfree.GameData.SP_SC3AT_KEY;
import static com.mgs_quiz.mgsquizfree.GameData.SP_SC3DHZ_KEY;
import static com.mgs_quiz.mgsquizfree.GameData.SP_SC3DH_KEY;
import static com.mgs_quiz.mgsquizfree.GameData.SP_SC3DT_KEY;
import static com.mgs_quiz.mgsquizfree.GameData.SP_SC4AHZ_KEY;
import static com.mgs_quiz.mgsquizfree.GameData.SP_SC4AH_KEY;
import static com.mgs_quiz.mgsquizfree.GameData.SP_SC4AT_KEY;
import static com.mgs_quiz.mgsquizfree.GameData.SP_SC4DHZ_KEY;
import static com.mgs_quiz.mgsquizfree.GameData.SP_SC4DH_KEY;
import static com.mgs_quiz.mgsquizfree.GameData.SP_SC4DT_KEY;
import static com.mgs_quiz.mgsquizfree.GameData.SP_SC5AHZ_KEY;
import static com.mgs_quiz.mgsquizfree.GameData.SP_SC5AH_KEY;
import static com.mgs_quiz.mgsquizfree.GameData.SP_SC5AT_KEY;
import static com.mgs_quiz.mgsquizfree.GameData.SP_SC5DHZ_KEY;
import static com.mgs_quiz.mgsquizfree.GameData.SP_SC5DH_KEY;
import static com.mgs_quiz.mgsquizfree.GameData.SP_SC5DT_KEY;
import static com.mgs_quiz.mgsquizfree.GameData.SP_SCALLAHZ;
import static com.mgs_quiz.mgsquizfree.GameData.SP_SCALLAH_KEY;
import static com.mgs_quiz.mgsquizfree.GameData.SP_SCALLAT_KEY;
import static com.mgs_quiz.mgsquizfree.GameData.SP_SCALLDHZ;
import static com.mgs_quiz.mgsquizfree.GameData.SP_SCALLDH_KEY;
import static com.mgs_quiz.mgsquizfree.GameData.SP_SCALLDT_KEY;
import static com.mgs_quiz.mgsquizfree.GameData.SP_SCAT_KEY;
import static com.mgs_quiz.mgsquizfree.GameData.SP_SCDT_KEY;
import static com.mgs_quiz.mgsquizfree.GameData.SP_SCOT_KEY;
import static com.mgs_quiz.mgsquizfree.GameData.SP_UDATE;
import static com.mgs_quiz.mgsquizfree.GameData.SP_USERNAME;

public class GetData {

    private static String dateFormat = "dd.MM.yyyy";
    private static SimpleDateFormat simpleDateFormat = new SimpleDateFormat(dateFormat);

    public static String getDate() {
        return simpleDateFormat.format(new Date());
    }

    public static String getDate(Calendar calendar) {
        return simpleDateFormat.format(calendar.getTime());
    }

    public static String getBirthdayLocal(Context context, Calendar calendar) {
        DateFormat userDateFormat = android.text.format.DateFormat.getMediumDateFormat(context);
        return userDateFormat.format(calendar.getTime());
    }

    public static boolean getBirthdayMessage(Activity activity, String date, String user, TextView textView) {

        if (date.equalsIgnoreCase(activity.getString(R.string.defaultString))) {
            return false;
        }

        String today = simpleDateFormat.format(Calendar.getInstance().getTime());
        String ids[] = new String[]{"3001", "0602", "0508", "2408", "2512"};
        String id = date.substring(0,2) + date.substring(3,5);

        if (date.substring(0,5).equalsIgnoreCase(today.substring(0,5))) {
            if (id.equals(ids[0])) {
                textView.setText(activity.getString(R.string.b3001na, user));
                textView.setCompoundDrawablesWithIntrinsicBounds(0, 0, R.drawable.flag_ireland, 0);
                return true;
            } else if (id.equals(ids[1])) {
                textView.setText(activity.getString(R.string.b0602di, user));
                return true;
            } else if (id.equals(ids[2])) {
                textView.setText(activity.getString(R.string.b0508ei, user));
                return true;
            } else if (id.equals(ids[3])) {
                textView.setText(activity.getString(R.string.b2408oe, user));
                return true;
            } else if (id.equals(ids[4])) {
                textView.setText(activity.getString(R.string.b2512ij, user));
                return true;
            } else {
                textView.setText(activity.getString(R.string.b0000, user));
                return true;
            }
        } else {
            return false;
        }
    }

    public static boolean getBirthdayMessageShort(Activity activity, String date, String user, TextView textView) {

        Calendar calT = Calendar.getInstance();
        Calendar calE = Calendar.getInstance();
        Calendar calEOY = Calendar.getInstance();
        Calendar calNY = Calendar.getInstance();

        int today = calT.get(Calendar.DAY_OF_YEAR);
        int currentYear = calT.get(Calendar.YEAR);
        String lastDay = "31.12." + currentYear;
        int bday = -1;

        try {
            calE.setTime(simpleDateFormat.parse(date));
            calE.setTime(simpleDateFormat.parse(calE.get(Calendar.DAY_OF_MONTH) + "." + (calE.get(Calendar.MONTH)+1) + "." + currentYear));
            calEOY.setTime(simpleDateFormat.parse(lastDay));
            String strNY = calE.get(Calendar.DAY_OF_MONTH) + "." + (calE.get(Calendar.MONTH) + 1) + "." + (currentYear + 1);
            calNY.setTime(simpleDateFormat.parse(strNY));

            bday = calE.get(Calendar.DAY_OF_YEAR);
        } catch (ParseException e) {
            textView.setText(activity.getString(R.string.defaultString));
            return false;
        }
        String ids[] = new String[]{"3001", "0602", "0508", "2408", "2512"};
        String id = date.substring(0,2) + date.substring(3,5);

        if (today == bday) {
            if (id.equals(ids[0])) {
                textView.setText(activity.getString(R.string.b3001na_td));
                return true;
            } else if (id.equals(ids[1])) {
                textView.setText(activity.getString(R.string.b0602di_td));
                return true;
            } else if (id.equals(ids[2])) {
                textView.setText(activity.getString(R.string.b0508ei_td));
                return true;
            } else if (id.equals(ids[3])) {
                textView.setText(activity.getString(R.string.b2408oe_td));
                return true;
            } else if (id.equals(ids[4])) {
                textView.setText(activity.getString(R.string.b2512ij_td));
                return true;
            } else {
                textView.setText(activity.getString(R.string.b0000, user));
                return true;
            }
        } else {
            String dif;
            if (today < bday) {
                dif = getBirthdayDif(activity, (bday - today));
            } else {
                dif = getBirthdayDif(activity, ((calEOY.get(Calendar.DAY_OF_YEAR) - today) + calNY.get(Calendar.DAY_OF_YEAR)));
            }
            if (id.equals(ids[0])) {
                textView.setText(activity.getString(R.string.b3001na_sh));
                return false;
            } else if (id.equals(ids[1])) {
                textView.setText(activity.getString(R.string.b0602di_sh, dif));
                return false;
            } else if (id.equals(ids[2])) {
                textView.setText(activity.getString(R.string.b0508ei_sh));
                return false;
            } else if (id.equals(ids[3])) {
                textView.setText(activity.getString(R.string.b2408oe_sh, dif));
                return false;
            } else if (id.equals(ids[4])) {
                textView.setText(activity.getString(R.string.b2512ij_sh));
                return false;
            } else {
                textView.setText(dif);
                return false;
            }
        }
    }

    private static String getBirthdayDif(Activity activity, int dif) {
        switch (dif) {
            case 1:
                return activity.getString(R.string.b0000_1);
            case 2:
                return activity.getString(R.string.b0000_2);
            case 3:
                return activity.getString(R.string.b0000_3);
            case 4:
                return activity.getString(R.string.b0000_4);
            default:
                return activity.getString(R.string.b0000_xx, dif);
        }
    }

    public static String getDisplay(Activity activity) {
        double x = 0, y = 0;
        int mWidthPixels = 0, mHeightPixels = 0;
        String text = "";
        try {
            WindowManager windowManager = activity.getWindowManager();
            Display display = windowManager.getDefaultDisplay();
            DisplayMetrics displayMetrics = new DisplayMetrics();
            display.getMetrics(displayMetrics);
            Point size = new Point();
            Display.class.getMethod("getRealSize", Point.class).invoke(display, size);
            mWidthPixels = size.x;
            mHeightPixels = size.y;
            DisplayMetrics dm = new DisplayMetrics();
            activity.getWindowManager().getDefaultDisplay().getMetrics(dm);
            x = Math.pow(mWidthPixels / dm.xdpi, 2);
            y = Math.pow(mHeightPixels / dm.ydpi, 2);

            text = size.x + "_" + size.y;
            text += " - " + String.format(Locale.US, "%.2f", Math.sqrt(x + y));
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return text;
    }

    public static String getGender(Context context, String g) {
        String d = context.getString(R.string.defaultString);

        if (g == null) {
            return d;
        } else if (g.equalsIgnoreCase(d)) {
            return d;
        } else if (g.equalsIgnoreCase(context.getString(R.string.genderM))) {
            return context.getString(R.string.genderMale);
        } else {
            return context.getString(R.string.genderFemale);
        }
    }

    public static String[] getKeys(String category, String difficulty) {
        if (category == null || difficulty == null) {
            return null;
        }
        String keys[] = new String[8];

        if (category.equals(CAT_ALL)) {
            keys[0] = SP_PREKEY + CAT_ALL;
            keys[1] = SP_PREKEY + CAT_ALL;
            keys[2] = SP_PREKEY + CAT_ALL;
        } else {
            keys[0] = SP_PREKEY + category.substring(3, category.length()).trim();
            keys[1] = SP_PREKEY + category.substring(3, category.length()).trim();
            keys[2] = SP_PREKEY + category.substring(3, category.length()).trim();
        }

        if(difficulty.equals(DIFFICULTY_A)) {
            keys[0] = keys[0] + SP_KEYDIF_AH;
            keys[1] = keys[1] + SP_KEYDIF_AT;
            keys[2] = keys[2] + SP_KEYDIF_AH + SP_KEYZEIT;
            keys[3] = keys[1] + SP_KEYQTA;
            keys[4] = keys[1] + SP_KEYQTAA;
            keys[5] = SP_QTOTALA_KEY;
            keys[6] = SP_QTOTALAA_KEY;
            if (category.equals(CAT_ALL)) {
                keys[7] = SP_QCANSWERSA_KEY;
            }
        } else {
            keys[0] = keys[0] + SP_KEYDIF_DH;
            keys[1] = keys[1] + SP_KEYDIF_DT;
            keys[2] = keys[2] + SP_KEYDIF_DH + SP_KEYZEIT;
            keys[3] = keys[1] + SP_KEYQTD;
            keys[4] = keys[1] + SP_KEYQTDA;
            keys[5] = SP_QTOTALD_KEY;
            keys[6] = SP_QTOTALDA_KEY;
            if (category.equals(CAT_ALL)) {
                keys[7] = SP_QCANSWERSD_KEY;
            }
        }

        return keys;
    }

    public static String getQLang(Context context) {
        String lang = Locale.getDefault().getLanguage();
        switch (lang.toLowerCase()) {
            case "en":
                return context.getString(R.string.questionLangEN);
            case "de":
                return context.getString(R.string.questionLangDE);
            case "ru":
                return context.getString(R.string.questionLangRU);
            default:
                return context.getString(R.string.questionLangEN);
        }
    }

    public static Stats getStats(Context appContext) {
        SharedPreferences preferences = appContext.getSharedPreferences(SP_NAMES, Context.MODE_PRIVATE);

        if (!preferences.contains(SP_USERNAME) && !preferences.contains(SP_SC1AH_KEY)) {
            return null;
        }

        Stats stats = new Stats(getDate());
        Map<String, ?> allEntries = preferences.getAll();

        for (Map.Entry<String, ?> entry : allEntries.entrySet()) {
            if (entry.getKey().equalsIgnoreCase(SP_SC1AH_KEY)) {
                stats.setSc1ah(Integer.parseInt(entry.getValue().toString()));
            }
            if (entry.getKey().equalsIgnoreCase(SP_SC1AT_KEY)) {
                stats.setSc1at(Integer.parseInt(entry.getValue().toString()));
            }
            if (entry.getKey().equalsIgnoreCase(SP_SC1DH_KEY)) {
                stats.setSc1dh(Integer.parseInt(entry.getValue().toString()));
            }
            if (entry.getKey().equalsIgnoreCase(SP_SC1DT_KEY)) {
                stats.setSc1dt(Integer.parseInt(entry.getValue().toString()));
            }

            if (entry.getKey().equalsIgnoreCase(SP_SC2AH_KEY)) {
                stats.setSc2ah(Integer.parseInt(entry.getValue().toString()));
            }
            if (entry.getKey().equalsIgnoreCase(SP_SC2AT_KEY)) {
                stats.setSc2at(Integer.parseInt(entry.getValue().toString()));
            }
            if (entry.getKey().equalsIgnoreCase(SP_SC2DH_KEY)) {
                stats.setSc2dh(Integer.parseInt(entry.getValue().toString()));
            }
            if (entry.getKey().equalsIgnoreCase(SP_SC2DT_KEY)) {
                stats.setSc2dt(Integer.parseInt(entry.getValue().toString()));
            }

            if (entry.getKey().equalsIgnoreCase(SP_SC3AH_KEY)) {
                stats.setSc3ah(Integer.parseInt(entry.getValue().toString()));
            }
            if (entry.getKey().equalsIgnoreCase(SP_SC3AT_KEY)) {
                stats.setSc3at(Integer.parseInt(entry.getValue().toString()));
            }
            if (entry.getKey().equalsIgnoreCase(SP_SC3DH_KEY)) {
                stats.setSc3dh(Integer.parseInt(entry.getValue().toString()));
            }
            if (entry.getKey().equalsIgnoreCase(SP_SC3DT_KEY)) {
                stats.setSc3dt(Integer.parseInt(entry.getValue().toString()));
            }

            if (entry.getKey().equalsIgnoreCase(SP_SC4AH_KEY)) {
                stats.setSc4ah(Integer.parseInt(entry.getValue().toString()));
            }
            if (entry.getKey().equalsIgnoreCase(SP_SC4AT_KEY)) {
                stats.setSc4at(Integer.parseInt(entry.getValue().toString()));
            }
            if (entry.getKey().equalsIgnoreCase(SP_SC4DH_KEY)) {
                stats.setSc4dh(Integer.parseInt(entry.getValue().toString()));
            }
            if (entry.getKey().equalsIgnoreCase(SP_SC4DT_KEY)) {
                stats.setSc4dt(Integer.parseInt(entry.getValue().toString()));
            }

            if (entry.getKey().equalsIgnoreCase(SP_SC5AH_KEY)) {
                stats.setSc5ah(Integer.parseInt(entry.getValue().toString()));
            }
            if (entry.getKey().equalsIgnoreCase(SP_SC5AT_KEY)) {
                stats.setSc5at(Integer.parseInt(entry.getValue().toString()));
            }
            if (entry.getKey().equalsIgnoreCase(SP_SC5DH_KEY)) {
                stats.setSc5dh(Integer.parseInt(entry.getValue().toString()));
            }
            if (entry.getKey().equalsIgnoreCase(SP_SC5DT_KEY)) {
                stats.setSc5dt(Integer.parseInt(entry.getValue().toString()));
            }

            if (entry.getKey().equalsIgnoreCase(SP_SC1AHZ_KEY)) {
                stats.setSc1ahz(Integer.parseInt(entry.getValue().toString()));
            }
            if (entry.getKey().equalsIgnoreCase(SP_SC1DHZ_KEY)) {
                stats.setSc1dhz(Integer.parseInt(entry.getValue().toString()));
            }

            if (entry.getKey().equalsIgnoreCase(SP_SC2AHZ_KEY)) {
                stats.setSc2ahz(Integer.parseInt(entry.getValue().toString()));
            }
            if (entry.getKey().equalsIgnoreCase(SP_SC2DHZ_KEY)) {
                stats.setSc2dhz(Integer.parseInt(entry.getValue().toString()));
            }

            if (entry.getKey().equalsIgnoreCase(SP_SC3AHZ_KEY)) {
                stats.setSc3ahz(Integer.parseInt(entry.getValue().toString()));
            }
            if (entry.getKey().equalsIgnoreCase(SP_SC3DHZ_KEY)) {
                stats.setSc3dhz(Integer.parseInt(entry.getValue().toString()));
            }

            if (entry.getKey().equalsIgnoreCase(SP_SC4AHZ_KEY)) {
                stats.setSc4ahz(Integer.parseInt(entry.getValue().toString()));
            }
            if (entry.getKey().equalsIgnoreCase(SP_SC4DHZ_KEY)) {
                stats.setSc4dhz(Integer.parseInt(entry.getValue().toString()));
            }

            if (entry.getKey().equalsIgnoreCase(SP_SC5AHZ_KEY)) {
                stats.setSc5ahz(Integer.parseInt(entry.getValue().toString()));
            }
            if (entry.getKey().equalsIgnoreCase(SP_SC5DHZ_KEY)) {
                stats.setSc5dhz(Integer.parseInt(entry.getValue().toString()));
            }

            if (entry.getKey().equalsIgnoreCase(SP_SCALLAH_KEY)) {
                stats.setScallah(Integer.parseInt(entry.getValue().toString()));
            }
            if (entry.getKey().equalsIgnoreCase(SP_SCALLAT_KEY)) {
                stats.setScallat(Integer.parseInt(entry.getValue().toString()));
            }
            if (entry.getKey().equalsIgnoreCase(SP_SCALLDH_KEY)) {
                stats.setScalldh(Integer.parseInt(entry.getValue().toString()));
            }
            if (entry.getKey().equalsIgnoreCase(SP_SCALLDT_KEY)) {
                stats.setScalldt(Integer.parseInt(entry.getValue().toString()));
            }
            if (entry.getKey().equalsIgnoreCase(SP_SCALLAHZ)) {
                stats.setScallahz(Integer.parseInt(entry.getValue().toString()));
            }
            if (entry.getKey().equalsIgnoreCase(SP_SCALLDHZ)) {
                stats.setScalldhz(Integer.parseInt(entry.getValue().toString()));
            }
            if (entry.getKey().equalsIgnoreCase(SP_QTOTALALLA_KEY)) {
                stats.setScallatQTA(Integer.parseInt(entry.getValue().toString()));
            }
            if (entry.getKey().equalsIgnoreCase(SP_QTOTALALLD_KEY)) {
                stats.setScalldtQTD(Integer.parseInt(entry.getValue().toString()));
            }
            if (entry.getKey().equalsIgnoreCase(SP_QTOTALAALLA_KEY)) {
                stats.setScallatQTAA(Integer.parseInt(entry.getValue().toString()));
            }
            if (entry.getKey().equalsIgnoreCase(SP_QTOTALAALLD_KEY)) {
                stats.setScalldtQTDA(Integer.parseInt(entry.getValue().toString()));
            }

            if (entry.getKey().equalsIgnoreCase(SP_SCAT_KEY)) {
                stats.setScat(Integer.parseInt(entry.getValue().toString()));
            }
            if (entry.getKey().equalsIgnoreCase(SP_SCDT_KEY)) {
                stats.setScdt(Integer.parseInt(entry.getValue().toString()));
            }
            if (entry.getKey().equalsIgnoreCase(SP_SCOT_KEY)) {
                stats.setScot(Integer.parseInt(entry.getValue().toString()));
            }

            if (entry.getKey().equalsIgnoreCase(SP_QTOTALA_KEY)) {
                stats.setqTotalA(Integer.parseInt(entry.getValue().toString()));
            }
            if (entry.getKey().equalsIgnoreCase(SP_QTOTALAA_KEY)) {
                stats.setqTotalAA(Integer.parseInt(entry.getValue().toString()));
            }
            if (entry.getKey().equalsIgnoreCase(SP_QTOTALD_KEY)) {
                stats.setqTotalD(Integer.parseInt(entry.getValue().toString()));
            }
            if (entry.getKey().equalsIgnoreCase(SP_QTOTALDA_KEY)) {
                stats.setqTotalDA(Integer.parseInt(entry.getValue().toString()));
            }
            if (entry.getKey().equalsIgnoreCase(SP_QTOTALOV_KEY)) {
                stats.setqTotalOV(Integer.parseInt(entry.getValue().toString()));
            }
            if (entry.getKey().equalsIgnoreCase(SP_QTOTALOVA_KEY)) {
                stats.setqTotalOVA(Integer.parseInt(entry.getValue().toString()));
            }

            if (entry.getKey().equalsIgnoreCase(SP_QTOTAL1A_KEY)) {
                stats.setSc1atQTA(Integer.parseInt(entry.getValue().toString()));
            }
            if (entry.getKey().equalsIgnoreCase(SP_QTOTAL1D_KEY)) {
                stats.setSc1dtQTD(Integer.parseInt(entry.getValue().toString()));
            }
            if (entry.getKey().equalsIgnoreCase(SP_QTOTALA1A_KEY)) {
                stats.setSc1atQTAA(Integer.parseInt(entry.getValue().toString()));
            }
            if (entry.getKey().equalsIgnoreCase(SP_QTOTALA1D_KEY)) {
                stats.setSc1dtQTDA(Integer.parseInt(entry.getValue().toString()));
            }

            if (entry.getKey().equalsIgnoreCase(SP_QTOTAL2A_KEY)) {
                stats.setSc2atQTA(Integer.parseInt(entry.getValue().toString()));
            }
            if (entry.getKey().equalsIgnoreCase(SP_QTOTAL2D_KEY)) {
                stats.setSc2dtQTD(Integer.parseInt(entry.getValue().toString()));
            }
            if (entry.getKey().equalsIgnoreCase(SP_QTOTALA2A_KEY)) {
                stats.setSc2atQTAA(Integer.parseInt(entry.getValue().toString()));
            }
            if (entry.getKey().equalsIgnoreCase(SP_QTOTALA2D_KEY)) {
                stats.setSc2dtQTDA(Integer.parseInt(entry.getValue().toString()));
            }

            if (entry.getKey().equalsIgnoreCase(SP_QTOTAL3A_KEY)) {
                stats.setSc3atQTA(Integer.parseInt(entry.getValue().toString()));
            }
            if (entry.getKey().equalsIgnoreCase(SP_QTOTAL3D_KEY)) {
                stats.setSc3dtQTD(Integer.parseInt(entry.getValue().toString()));
            }
            if (entry.getKey().equalsIgnoreCase(SP_QTOTALA3A_KEY)) {
                stats.setSc3atQTAA(Integer.parseInt(entry.getValue().toString()));
            }
            if (entry.getKey().equalsIgnoreCase(SP_QTOTALA3D_KEY)) {
                stats.setSc3dtQTDA(Integer.parseInt(entry.getValue().toString()));
            }

            if (entry.getKey().equalsIgnoreCase(SP_QTOTAL4A_KEY)) {
                stats.setSc4atQTA(Integer.parseInt(entry.getValue().toString()));
            }
            if (entry.getKey().equalsIgnoreCase(SP_QTOTAL4D_KEY)) {
                stats.setSc4dtQTD(Integer.parseInt(entry.getValue().toString()));
            }
            if (entry.getKey().equalsIgnoreCase(SP_QTOTALA4A_KEY)) {
                stats.setSc4atQTAA(Integer.parseInt(entry.getValue().toString()));
            }
            if (entry.getKey().equalsIgnoreCase(SP_QTOTALA4D_KEY)) {
                stats.setSc4dtQTDA(Integer.parseInt(entry.getValue().toString()));
            }

            if (entry.getKey().equalsIgnoreCase(SP_QTOTAL5A_KEY)) {
                stats.setSc5atQTA(Integer.parseInt(entry.getValue().toString()));
            }
            if (entry.getKey().equalsIgnoreCase(SP_QTOTAL5D_KEY)) {
                stats.setSc5dtQTD(Integer.parseInt(entry.getValue().toString()));
            }
            if (entry.getKey().equalsIgnoreCase(SP_QTOTALA5A_KEY)) {
                stats.setSc5atQTAA(Integer.parseInt(entry.getValue().toString()));
            }
            if (entry.getKey().equalsIgnoreCase(SP_QTOTALA5D_KEY)) {
                stats.setSc5dtQTDA(Integer.parseInt(entry.getValue().toString()));
            }

            if (entry.getKey().equalsIgnoreCase(SP_QCANSWERSA_KEY)) {
                stats.setcAnswersA(Integer.parseInt(entry.getValue().toString()));
            }
            if (entry.getKey().equalsIgnoreCase(SP_QCANSWERSD_KEY)) {
                stats.setcAnswersD(Integer.parseInt(entry.getValue().toString()));
            }

            if (entry.getKey().equalsIgnoreCase(SP_KEYS)) {
                if (entry.getValue() != null) {
                    String keysArray[] = entry.getValue().toString().replaceAll("[^a-zA-Z0-9,]", "").split(",");
                    List<String> list = Arrays.asList(keysArray);

                    stats.setKeys(list);
                }
            }

            if (entry.getKey().equalsIgnoreCase(SP_UDATE)) {
                stats.setuDate(getDate());
            }
        }
        return stats;
    }

    public static double getRate(String difficulty) {
        if (difficulty.equals(DIFFICULTY_A)) {
            return ASCORE_RATE;
        } else if (difficulty.equals(DIFFICULTY_D)) {
            return DSCORE_RATE;
        }
        return 1;
    }

    public static Map<String, Long> getSortedRanking(Map<String, Long> unsortMap, final boolean order) {

        // order: asc = true, desc = false

        List<Map.Entry<String, Long>> list = new LinkedList<Map.Entry<String, Long>>(unsortMap.entrySet());

        // Sorting the list based on values
        Collections.sort(list, new Comparator<Map.Entry<String, Long>>() {
            public int compare(Map.Entry<String, Long> o1,
                               Map.Entry<String, Long> o2) {
                if (order){
                    return o1.getValue().compareTo(o2.getValue());
                } else {
                    return o2.getValue().compareTo(o1.getValue());
                }
            }
        });

        // Maintaining insertion order with the help of LinkedList
        Map<String, Long> sortedMap = new LinkedHashMap<String, Long>();
        for (Map.Entry<String, Long> entry : list) {
            sortedMap.put(entry.getKey(), entry.getValue());
        }

        return sortedMap;
    }

    public static int getTimeLimit(String difficulty) {
        if (difficulty.equals(DIFFICULTY_A)) {
            return ASCORE_LIMIT;
        } else if (difficulty.equals(DIFFICULTY_D)) {
            return DSCORE_LIMIT;
        }
        return (int)COUNTDOWN_IN_MILLIS / 1000;
    }

    public static Calendar getUserBirthday(String strBirtday) {
        Calendar calendar = Calendar.getInstance();

        Date date = new Date();
        if (strBirtday != null && strBirtday.length() > 6) {
            try {
                date = simpleDateFormat.parse(strBirtday);
                calendar.setTime(date);
            } catch (ParseException e) {
                e.printStackTrace();
            }
        }
        return calendar;
    }
}
