package com.knowledgespike.blog;

import java.io.Serializable;

public final class User implements Serializable {

    private final String name;

    public User(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}
