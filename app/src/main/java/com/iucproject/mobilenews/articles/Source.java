package com.iucproject.mobilenews.articles;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Source {

    // https://newsapi.org/s/turkey-news-api buradaki json api'sinde source'da id ve name olmak üzere 2 tane
    // json verisi var.
    @SerializedName("id")
    @Expose
    private String id;

    @SerializedName("name")
    @Expose
    private String name;

    //GetterS -- SetterS
    public String getId() { return id; }
    public void setId(String id) { this.id = id; }
    public String getName() { return name; }
    public void setName(String name) { this.name = name; }
}
