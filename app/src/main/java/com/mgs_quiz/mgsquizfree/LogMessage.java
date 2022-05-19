package com.mgs_quiz.mgsquizfree;

import android.content.Context;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Date;

public class LogMessage {

    public static final String FILE_NAME = "mgsLog.txt";

    public static void save(Context appContext, String message) {
        message += "\n";
        message = String.format("%s\t%s", new Date(), message);
        try (FileOutputStream fos = appContext.openFileOutput(FILE_NAME, appContext.MODE_APPEND)) {
            fos.write(message.getBytes());
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static String load(Context appContext) {
        try (BufferedReader br = new BufferedReader(new InputStreamReader(appContext.openFileInput(FILE_NAME)))) {
            StringBuilder sb = new StringBuilder();
            String text;
            while ((text = br.readLine()) != null) {
                sb.append(text);
                sb.append("\n");
            }
            sb.append("\n\n\n\n\n\n===== end =====\n\n");
            return sb.toString();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static boolean deleteFile(Context context) {
        File dir = context.getFilesDir();
        File file = new File(dir, FILE_NAME);
        return file.delete();
    }
}
