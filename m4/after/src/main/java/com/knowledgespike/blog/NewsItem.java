package com.knowledgespike.blog;

public class NewsItem {
    private final String title;
    private final String entry;
    private final int topic;

    public NewsItem(String title, String entry, int topic) {
        this.title = title;
        this.entry = entry;
        this.topic = topic;
    }

    public int getTopic() {
        return topic;
    }

    public String getEntry() {
        return entry;
    }

    public String getTitle() {
        return title;
    }
}
