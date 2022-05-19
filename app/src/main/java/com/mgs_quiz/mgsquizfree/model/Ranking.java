package com.mgs_quiz.mgsquizfree.model;

import java.io.Serializable;

import androidx.annotation.Keep;

@Keep
public class Ranking implements Serializable {

    private long serialVersionUID = 1L;

    private String u1;
    private String u2;
    private String u3;
    private String u4;
    private String u5;
    private String u6;
    private String u7;
    private String u8;
    private String u9;
    private String u10;

    private int v1;
    private int v2;
    private int v3;
    private int v4;
    private int v5;
    private int v6;
    private int v7;
    private int v8;
    private int v9;
    private int v10;

    public Ranking() {
    }

    public String getU1() {
        return u1;
    }

    public void setU1(String u1) {
        this.u1 = u1;
    }

    public String getU2() {
        return u2;
    }

    public void setU2(String u2) {
        this.u2 = u2;
    }

    public String getU3() {
        return u3;
    }

    public void setU3(String u3) {
        this.u3 = u3;
    }

    public String getU4() {
        return u4;
    }

    public void setU4(String u4) {
        this.u4 = u4;
    }

    public String getU5() {
        return u5;
    }

    public void setU5(String u5) {
        this.u5 = u5;
    }

    public String getU6() {
        return u6;
    }

    public void setU6(String u6) {
        this.u6 = u6;
    }

    public String getU7() {
        return u7;
    }

    public void setU7(String u7) {
        this.u7 = u7;
    }

    public String getU8() {
        return u8;
    }

    public void setU8(String u8) {
        this.u8 = u8;
    }

    public String getU9() {
        return u9;
    }

    public void setU9(String u9) {
        this.u9 = u9;
    }

    public String getU10() {
        return u10;
    }

    public void setU10(String u10) {
        this.u10 = u10;
    }

    public int getV1() {
        return v1;
    }

    public void setV1(int v1) {
        this.v1 = v1;
    }

    public int getV2() {
        return v2;
    }

    public void setV2(int v2) {
        this.v2 = v2;
    }

    public int getV3() {
        return v3;
    }

    public void setV3(int v3) {
        this.v3 = v3;
    }

    public int getV4() {
        return v4;
    }

    public void setV4(int v4) {
        this.v4 = v4;
    }

    public int getV5() {
        return v5;
    }

    public void setV5(int v5) {
        this.v5 = v5;
    }

    public int getV6() {
        return v6;
    }

    public void setV6(int v6) {
        this.v6 = v6;
    }

    public int getV7() {
        return v7;
    }

    public void setV7(int v7) {
        this.v7 = v7;
    }

    public int getV8() {
        return v8;
    }

    public void setV8(int v8) {
        this.v8 = v8;
    }

    public int getV9() {
        return v9;
    }

    public void setV9(int v9) {
        this.v9 = v9;
    }

    public int getV10() {
        return v10;
    }

    public void setV10(int v10) {
        this.v10 = v10;
    }

    @Override
    public String toString() {
        return "Ranking{" +
                "u1='" + u1 + '\'' +
                ", u2='" + u2 + '\'' +
                ", u3='" + u3 + '\'' +
                ", u4='" + u4 + '\'' +
                ", u5='" + u5 + '\'' +
                ", u6='" + u6 + '\'' +
                ", u7='" + u7 + '\'' +
                ", u8='" + u8 + '\'' +
                ", u9='" + u9 + '\'' +
                ", u10='" + u10 + '\'' +
                ", v1=" + v1 +
                ", v2=" + v2 +
                ", v3=" + v3 +
                ", v4=" + v4 +
                ", v5=" + v5 +
                ", v6=" + v6 +
                ", v7=" + v7 +
                ", v8=" + v8 +
                ", v9=" + v9 +
                ", v10=" + v10 +
                '}';
    }
}
