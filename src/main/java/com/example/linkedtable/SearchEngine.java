package com.example.linkedtable;

//class for creating unique search engine object
public class SearchEngine {
    private String name;
    private String url;

    public SearchEngine(String name, String url) {
        this.name = name;
        this.url = url;
    }

    public String getName() {
        return name;
    }

    public String getUrl() {
        return url;
    }
}
