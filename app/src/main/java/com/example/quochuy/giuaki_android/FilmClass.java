package com.example.quochuy.giuaki_android;

import java.io.Serializable;

/**
 * Created by Quoc Huy on 4/14/2018.
 */

public class FilmClass implements Serializable {
    private String NAME;
    private int TIME;
    private String CONTENT;
    private String YEAR="";
    private int RATE=0;
    private String URL;
    private String URL_IMAGE;



    public String getYEAR() {
        return YEAR;
    }

    public void setYEAR(String YEAR) {
        this.YEAR = YEAR;
    }

    public int getRATE() {
        return RATE;
    }

    public void setRATE(int RATE) {
        this.RATE = RATE;
    }

    public FilmClass(String NAME, int TIME, String CONTENT, String URL, String urlImage) {
        this.NAME = NAME;
        this.TIME = TIME;
        this.CONTENT = CONTENT;
        this.URL = URL;
        this.URL_IMAGE=urlImage;
    }

    public String getURL_IMAGE() {
        return URL_IMAGE;
    }

    public void setURL_IMAGE(String URL_IMAGE) {
        this.URL_IMAGE = URL_IMAGE;
    }

    public FilmClass() {
    }

    public String getNAME() {
        return NAME;
    }

    public void setNAME(String NAME) {
        this.NAME = NAME;
    }

    public int getTIME() {
        return TIME;
    }

    public void setTIME(int TIME) {
        this.TIME = TIME;
    }

    public String getCONTENT() {
        return CONTENT;
    }

    public void setCONTENT(String CONTENT) {
        this.CONTENT = CONTENT;
    }

    public String getURL() {
        return URL;
    }

    public void setURL(String URL) {
        this.URL = URL;
    }
}
