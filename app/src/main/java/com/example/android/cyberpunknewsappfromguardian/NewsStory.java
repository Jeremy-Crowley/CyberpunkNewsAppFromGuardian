package com.example.android.cyberpunknewsappfromguardian;

public class NewsStory {
    private String mTitle;
    private String mAuthor;
    private String mDate;
    private String  mWebAddress;
    private String mSection;

    public NewsStory(String title, String author, String date, String webAddress, String section){
        mTitle = title;
        mAuthor = author;
        mDate = date;
        mWebAddress = webAddress;
        mSection = section;
    }
    public String getTitle() {return mTitle;}

    public String getAuthor() {return mAuthor;}

    public String getDate() {return mDate;}

    public String getURL() {return mWebAddress;}

    public String getSection() {return mSection;}
}

