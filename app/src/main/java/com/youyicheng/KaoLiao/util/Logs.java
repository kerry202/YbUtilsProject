package com.youyicheng.KaoLiao.util;

public class Logs {

    private static boolean isDebug = true;

    public static void s(String str) {
        if (isDebug) {
            System.out.println(" yblogs: " + str);
        }
    }
}
