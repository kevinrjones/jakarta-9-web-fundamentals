package com.knowledgespike.blog;

public final class Topic {

    private final int id;
    private final String title;
    private final String url;

    public Topic(String title, String url, int id) {
        this.title = title;
        this.url = url;
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public String getUrl() {
        return url;
    }

    public int getId() {
        return id;
    }

}
