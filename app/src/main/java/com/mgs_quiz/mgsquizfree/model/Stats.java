package com.mgs_quiz.mgsquizfree.model;

import java.util.List;

import androidx.annotation.Keep;

import static com.mgs_quiz.mgsquizfree.GameData.DEFZEITSEC;
import static com.mgs_quiz.mgsquizfree.GameData.DEFZEITSECEXP;

@Keep
public class Stats {

    private int sc1ah;
    private int sc1at;
    private int sc1dh;
    private int sc1dt;
    private int sc2ah;
    private int sc2at;
    private int sc2dh;
    private int sc2dt;
    private int sc3ah;
    private int sc3at;
    private int sc3dh;
    private int sc3dt;
    private int sc4ah;
    private int sc4at;
    private int sc4dh;
    private int sc4dt;
    private int sc5ah;
    private int sc5at;
    private int sc5dh;
    private int sc5dt;

    private int sc1ahz = DEFZEITSEC;
    private int sc1dhz = DEFZEITSEC;
    private int sc2ahz = DEFZEITSEC;
    private int sc2dhz = DEFZEITSEC;
    private int sc3ahz = DEFZEITSEC;
    private int sc3dhz = DEFZEITSEC;
    private int sc4ahz = DEFZEITSEC;
    private int sc4dhz = DEFZEITSEC;
    private int sc5ahz = DEFZEITSEC;
    private int sc5dhz = DEFZEITSEC;

    private int scallah;
    private int scallat;
    private int scalldh;
    private int scalldt;
    private int scallahz = DEFZEITSECEXP;;
    private int scalldhz = DEFZEITSECEXP;
    private int scallatQTA;
    private int scalldtQTD;
    private int scallatQTAA;
    private int scalldtQTDA;

    private int scat;
    private int scdt;
    private int scot;

    private int qTotalA;
    private int qTotalAA;
    private int qTotalD;
    private int qTotalDA;
    private int qTotalOV;
    private int qTotalOVA;

    private int sc1atQTA;
    private int sc1dtQTD;
    private int sc1atQTAA;
    private int sc1dtQTDA;
    private int sc2atQTA;
    private int sc2dtQTD;
    private int sc2atQTAA;
    private int sc2dtQTDA;
    private int sc3atQTA;
    private int sc3dtQTD;
    private int sc3atQTAA;
    private int sc3dtQTDA;
    private int sc4atQTA;
    private int sc4dtQTD;
    private int sc4atQTAA;
    private int sc4dtQTDA;
    private int sc5atQTA;
    private int sc5dtQTD;
    private int sc5atQTAA;
    private int sc5dtQTDA;

    private int cAnswersA;
    private int cAnswersD;

    private String uDate;
    private List<String> keys;

    public Stats() {
    }

    public Stats(String uDate) {
        this.uDate = uDate;
    }

    public Stats(int zero, String uDate) {
        this(zero, zero, zero, zero, zero, zero, zero, zero, zero, zero,
                zero, zero, zero, zero, zero, zero, zero, zero, zero, zero, uDate);
    }

    public Stats(int sc1ah, int sc1at, int sc1dh, int sc1dt, int sc2ah, int sc2at, int sc2dh, int sc2dt,
                 int sc3ah, int sc3at, int sc3dh, int sc3dt, int sc4ah, int sc4at, int sc4dh, int sc4dt,
                 int sc5ah, int sc5at, int sc5dh, int sc5dt, String uDate) {
        this.sc1ah = sc1ah;
        this.sc1at = sc1at;
        this.sc1dh = sc1dh;
        this.sc1dt = sc1dt;
        this.sc2ah = sc2ah;
        this.sc2at = sc2at;
        this.sc2dh = sc2dh;
        this.sc2dt = sc2dt;
        this.sc3ah = sc3ah;
        this.sc3at = sc3at;
        this.sc3dh = sc3dh;
        this.sc3dt = sc3dt;
        this.sc4ah = sc4ah;
        this.sc4at = sc4at;
        this.sc4dh = sc4dh;
        this.sc4dt = sc4dt;
        this.sc5ah = sc5ah;
        this.sc5at = sc5at;
        this.sc5dh = sc5dh;
        this.sc5dt = sc5dt;
        this.uDate = uDate;
    }

    public int getSc1ah() {
        return sc1ah;
    }

    public void setSc1ah(int sc1ah) {
        this.sc1ah = sc1ah;
    }

    public int getSc1at() {
        return sc1at;
    }

    public void setSc1at(int sc1at) {
        this.sc1at = sc1at;
    }

    public int getSc1dh() {
        return sc1dh;
    }

    public void setSc1dh(int sc1dh) {
        this.sc1dh = sc1dh;
    }

    public int getSc1dt() {
        return sc1dt;
    }

    public void setSc1dt(int sc1dt) {
        this.sc1dt = sc1dt;
    }

    public int getSc2ah() {
        return sc2ah;
    }

    public void setSc2ah(int sc2ah) {
        this.sc2ah = sc2ah;
    }

    public int getSc2at() {
        return sc2at;
    }

    public void setSc2at(int sc2at) {
        this.sc2at = sc2at;
    }

    public int getSc2dh() {
        return sc2dh;
    }

    public void setSc2dh(int sc2dh) {
        this.sc2dh = sc2dh;
    }

    public int getSc2dt() {
        return sc2dt;
    }

    public void setSc2dt(int sc2dt) {
        this.sc2dt = sc2dt;
    }

    public int getSc3ah() {
        return sc3ah;
    }

    public void setSc3ah(int sc3ah) {
        this.sc3ah = sc3ah;
    }

    public int getSc3at() {
        return sc3at;
    }

    public void setSc3at(int sc3at) {
        this.sc3at = sc3at;
    }

    public int getSc3dh() {
        return sc3dh;
    }

    public void setSc3dh(int sc3dh) {
        this.sc3dh = sc3dh;
    }

    public int getSc3dt() {
        return sc3dt;
    }

    public void setSc3dt(int sc3dt) {
        this.sc3dt = sc3dt;
    }

    public int getSc4ah() {
        return sc4ah;
    }

    public void setSc4ah(int sc4ah) {
        this.sc4ah = sc4ah;
    }

    public int getSc4at() {
        return sc4at;
    }

    public void setSc4at(int sc4at) {
        this.sc4at = sc4at;
    }

    public int getSc4dh() {
        return sc4dh;
    }

    public void setSc4dh(int sc4dh) {
        this.sc4dh = sc4dh;
    }

    public int getSc4dt() {
        return sc4dt;
    }

    public void setSc4dt(int sc4dt) {
        this.sc4dt = sc4dt;
    }

    public int getSc5ah() {
        return sc5ah;
    }

    public void setSc5ah(int sc5ah) {
        this.sc5ah = sc5ah;
    }

    public int getSc5at() {
        return sc5at;
    }

    public void setSc5at(int sc5at) {
        this.sc5at = sc5at;
    }

    public int getSc5dh() {
        return sc5dh;
    }

    public void setSc5dh(int sc5dh) {
        this.sc5dh = sc5dh;
    }

    public int getSc5dt() {
        return sc5dt;
    }

    public void setSc5dt(int sc5dt) {
        this.sc5dt = sc5dt;
    }

    public int getSc1ahz() {
        return sc1ahz;
    }

    public void setSc1ahz(int sc1ahz) {
        this.sc1ahz = sc1ahz;
    }

    public int getSc1dhz() {
        return sc1dhz;
    }

    public void setSc1dhz(int sc1dhz) {
        this.sc1dhz = sc1dhz;
    }

    public int getSc2ahz() {
        return sc2ahz;
    }

    public void setSc2ahz(int sc2ahz) {
        this.sc2ahz = sc2ahz;
    }

    public int getSc2dhz() {
        return sc2dhz;
    }

    public void setSc2dhz(int sc2dhz) {
        this.sc2dhz = sc2dhz;
    }

    public int getSc3ahz() {
        return sc3ahz;
    }

    public void setSc3ahz(int sc3ahz) {
        this.sc3ahz = sc3ahz;
    }

    public int getSc3dhz() {
        return sc3dhz;
    }

    public void setSc3dhz(int sc3dhz) {
        this.sc3dhz = sc3dhz;
    }

    public int getSc4ahz() {
        return sc4ahz;
    }

    public void setSc4ahz(int sc4ahz) {
        this.sc4ahz = sc4ahz;
    }

    public int getSc4dhz() {
        return sc4dhz;
    }

    public void setSc4dhz(int sc4dhz) {
        this.sc4dhz = sc4dhz;
    }

    public int getSc5ahz() {
        return sc5ahz;
    }

    public void setSc5ahz(int sc5ahz) {
        this.sc5ahz = sc5ahz;
    }

    public int getSc5dhz() {
        return sc5dhz;
    }

    public void setSc5dhz(int sc5dhz) {
        this.sc5dhz = sc5dhz;
    }

    public int getScat() {
        return scat;
    }

    public void setScat(int scat) {
        this.scat = scat;
    }

    public int getScdt() {
        return scdt;
    }

    public void setScdt(int scdt) {
        this.scdt = scdt;
    }

    public int getScallah() {
        return scallah;
    }

    public void setScallah(int scallah) {
        this.scallah = scallah;
    }

    public int getScallat() {
        return scallat;
    }

    public void setScallat(int scallat) {
        this.scallat = scallat;
    }

    public int getScalldh() {
        return scalldh;
    }

    public void setScalldh(int scalldh) {
        this.scalldh = scalldh;
    }

    public int getScalldt() {
        return scalldt;
    }

    public void setScalldt(int scalldt) {
        this.scalldt = scalldt;
    }

    public int getScallahz() {
        return scallahz;
    }

    public void setScallahz(int scallahz) {
        this.scallahz = scallahz;
    }

    public int getScalldhz() {
        return scalldhz;
    }

    public void setScalldhz(int scalldhz) {
        this.scalldhz = scalldhz;
    }

    public int getScallatQTA() {
        return scallatQTA;
    }

    public void setScallatQTA(int scallatQTA) {
        this.scallatQTA = scallatQTA;
    }

    public int getScalldtQTD() {
        return scalldtQTD;
    }

    public void setScalldtQTD(int scalldtQTD) {
        this.scalldtQTD = scalldtQTD;
    }

    public int getScallatQTAA() {
        return scallatQTAA;
    }

    public void setScallatQTAA(int scallatQTAA) {
        this.scallatQTAA = scallatQTAA;
    }

    public int getScalldtQTDA() {
        return scalldtQTDA;
    }

    public void setScalldtQTDA(int scalldtQTDA) {
        this.scalldtQTDA = scalldtQTDA;
    }

    public int getScot() {
        return scot;
    }

    public void setScot(int scot) {
        this.scot = scot;
    }

    public int getqTotalA() {
        return qTotalA;
    }

    public void setqTotalA(int qTotalA) {
        this.qTotalA = qTotalA;
    }

    public int getqTotalAA() {
        return qTotalAA;
    }

    public void setqTotalAA(int qTotalAA) {
        this.qTotalAA = qTotalAA;
    }

    public int getqTotalD() {
        return qTotalD;
    }

    public void setqTotalD(int qTotalD) {
        this.qTotalD = qTotalD;
    }

    public int getqTotalDA() {
        return qTotalDA;
    }

    public void setqTotalDA(int qTotalDA) {
        this.qTotalDA = qTotalDA;
    }

    public int getSc1atQTA() {
        return sc1atQTA;
    }

    public int getqTotalOV() {
        return qTotalOV;
    }

    public void setqTotalOV(int qTotalOV) {
        this.qTotalOV = qTotalOV;
    }

    public int getqTotalOVA() {
        return qTotalOVA;
    }

    public void setqTotalOVA(int qTotalOVA) {
        this.qTotalOVA = qTotalOVA;
    }

    public void setSc1atQTA(int sc1atQTA) {
        this.sc1atQTA = sc1atQTA;
    }

    public int getSc1dtQTD() {
        return sc1dtQTD;
    }

    public void setSc1dtQTD(int sc1dtQTD) {
        this.sc1dtQTD = sc1dtQTD;
    }

    public int getSc1atQTAA() {
        return sc1atQTAA;
    }

    public void setSc1atQTAA(int sc1atQTAA) {
        this.sc1atQTAA = sc1atQTAA;
    }

    public int getSc1dtQTDA() {
        return sc1dtQTDA;
    }

    public void setSc1dtQTDA(int sc1dtQTDA) {
        this.sc1dtQTDA = sc1dtQTDA;
    }

    public int getSc2atQTA() {
        return sc2atQTA;
    }

    public void setSc2atQTA(int sc2atQTA) {
        this.sc2atQTA = sc2atQTA;
    }

    public int getSc2dtQTD() {
        return sc2dtQTD;
    }

    public void setSc2dtQTD(int sc2dtQTD) {
        this.sc2dtQTD = sc2dtQTD;
    }

    public int getSc2atQTAA() {
        return sc2atQTAA;
    }

    public void setSc2atQTAA(int sc2atQTAA) {
        this.sc2atQTAA = sc2atQTAA;
    }

    public int getSc2dtQTDA() {
        return sc2dtQTDA;
    }

    public void setSc2dtQTDA(int sc2dtQTDA) {
        this.sc2dtQTDA = sc2dtQTDA;
    }

    public int getSc3atQTA() {
        return sc3atQTA;
    }

    public void setSc3atQTA(int sc3atQTA) {
        this.sc3atQTA = sc3atQTA;
    }

    public int getSc3dtQTD() {
        return sc3dtQTD;
    }

    public void setSc3dtQTD(int sc3dtQTD) {
        this.sc3dtQTD = sc3dtQTD;
    }

    public int getSc3atQTAA() {
        return sc3atQTAA;
    }

    public void setSc3atQTAA(int sc3atQTAA) {
        this.sc3atQTAA = sc3atQTAA;
    }

    public int getSc3dtQTDA() {
        return sc3dtQTDA;
    }

    public void setSc3dtQTDA(int sc3dtQTDA) {
        this.sc3dtQTDA = sc3dtQTDA;
    }

    public int getSc4atQTA() {
        return sc4atQTA;
    }

    public void setSc4atQTA(int sc4atQTA) {
        this.sc4atQTA = sc4atQTA;
    }

    public int getSc4dtQTD() {
        return sc4dtQTD;
    }

    public void setSc4dtQTD(int sc4dtQTD) {
        this.sc4dtQTD = sc4dtQTD;
    }

    public int getSc4atQTAA() {
        return sc4atQTAA;
    }

    public void setSc4atQTAA(int sc4atQTAA) {
        this.sc4atQTAA = sc4atQTAA;
    }

    public int getSc4dtQTDA() {
        return sc4dtQTDA;
    }

    public void setSc4dtQTDA(int sc4dtQTDA) {
        this.sc4dtQTDA = sc4dtQTDA;
    }

    public int getSc5atQTA() {
        return sc5atQTA;
    }

    public void setSc5atQTA(int sc5atQTA) {
        this.sc5atQTA = sc5atQTA;
    }

    public int getSc5dtQTD() {
        return sc5dtQTD;
    }

    public void setSc5dtQTD(int sc5dtQTD) {
        this.sc5dtQTD = sc5dtQTD;
    }

    public int getSc5atQTAA() {
        return sc5atQTAA;
    }

    public void setSc5atQTAA(int sc5atQTAA) {
        this.sc5atQTAA = sc5atQTAA;
    }

    public int getSc5dtQTDA() {
        return sc5dtQTDA;
    }

    public void setSc5dtQTDA(int sc5dtQTDA) {
        this.sc5dtQTDA = sc5dtQTDA;
    }

    public int getcAnswersA() {
        return cAnswersA;
    }

    public void setcAnswersA(int cAnswersA) {
        this.cAnswersA = cAnswersA;
    }

    public int getcAnswersD() {
        return cAnswersD;
    }

    public void setcAnswersD(int cAnswersD) {
        this.cAnswersD = cAnswersD;
    }

    public String getuDate() {
        return uDate;
    }

    public void setuDate(String uDate) {
        this.uDate = uDate;
    }

    public List<String> getKeys() {
        return keys;
    }

    public void setKeys(List<String> keys) {
        this.keys = keys;
    }

    @Override
    public String toString() {
        return "Stats{" +
                "sc1ah=" + sc1ah +
                ", sc1at=" + sc1at +
                ", sc1dh=" + sc1dh +
                ", sc1dt=" + sc1dt + "\n" +
                ", sc2ah=" + sc2ah +
                ", sc2at=" + sc2at +
                ", sc2dh=" + sc2dh +
                ", sc2dt=" + sc2dt + "\n" +
                ", sc3ah=" + sc3ah +
                ", sc3at=" + sc3at +
                ", sc3dh=" + sc3dh +
                ", sc3dt=" + sc3dt + "\n" +
                ", sc4ah=" + sc4ah +
                ", sc4at=" + sc4at +
                ", sc4dh=" + sc4dh +
                ", sc4dt=" + sc4dt + "\n" +
                ", sc5ah=" + sc5ah +
                ", sc5at=" + sc5at +
                ", sc5dh=" + sc5dh +
                ", sc5dt=" + sc5dt + "\n" +
                ", sc1ahz=" + sc1ahz +
                ", sc1dhz=" + sc1dhz +
                ", sc2ahz=" + sc2ahz +
                ", sc2dhz=" + sc2dhz + "\n" +
                ", sc3ahz=" + sc3ahz +
                ", sc3dhz=" + sc3dhz +
                ", sc4ahz=" + sc4ahz +
                ", sc4dhz=" + sc4dhz +
                ", sc5ahz=" + sc5ahz +
                ", sc5dhz=" + sc5dhz + "\n" +
                ", scat=" + scat +
                ", scdt=" + scdt +
                ", scot=" + scot + "\n" +
                ", uDate='" + uDate + '\'' +
                ", keys=" + keys +
                '}';
    }
}
