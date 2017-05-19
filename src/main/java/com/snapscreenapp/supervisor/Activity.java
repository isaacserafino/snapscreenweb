package com.snapscreenapp.supervisor;

public class Activity {

    private final long id;
    private final String content;

    public Activity(long id, String content) {
        this.id = id;
        this.content = content;
    }

    public long getId() {
        return id;
    }

    public String getContent() {
        return content;
    }
}