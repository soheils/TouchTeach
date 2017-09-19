package com.touchteach.touchteach.tools;

public abstract class Distributor {
    private static Users user;

    public static void setUser(Users user) {
        Distributor.user = user;
    }

    public static Users getUser() {
        return Distributor.user;
    }

}