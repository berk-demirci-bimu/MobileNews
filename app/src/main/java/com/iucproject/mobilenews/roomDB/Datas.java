package com.iucproject.mobilenews.roomDB;


import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "Values")
public class Datas {

    @PrimaryKey(autoGenerate = true)
    private int did;

    @ColumnInfo(name="title")
    private String title;
    @ColumnInfo(name="content")
    private String content;
    @ColumnInfo(name="img")
    private String coverImg;

    public Datas(String title, String content, String coverImg) {
        this.title = title;
        this.content = content;
        this.coverImg = coverImg;
    }

    public int getDid() { return did; }
    public void setDid(int did) { this.did = did; }
    public String getTitle() { return title; }
    public void setTitle(String title) { this.title = title; }
    public String getContent() { return content; }
    public void setContent(String content) { this.content = content; }
    public String getCoverImg() { return coverImg; }
    public void setCoverImg(String coverImg) { this.coverImg = coverImg; }
}
