package com.touchteach.touchteach.tools;

import com.touchteach.touchteach.tools.Users;

public abstract class Distributor {
    public final static int screenUnit = 3;
    private static Users user;

    public static void setUser(Users user) {
        Distributor.user = user;
    }

    public static Users getUser() {
        return Distributor.user;
    }

}