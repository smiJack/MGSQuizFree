package com.mgs_quiz.mgsquizfree;

import java.text.NumberFormat;

public class GetFormattedNumber {

    private GetFormattedNumber() {
    }

    public static String getFormattedNumber(int number) {
        return NumberFormat.getInstance().format(number);
    }
}
