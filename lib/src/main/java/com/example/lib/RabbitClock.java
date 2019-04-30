package com.example.lib;
import java.util.Timer;
import java.util.TimerTask;


public class RabbitClock {
    private static int hh = 0;
    private static int mm = 0;
    private static int ss = 10;
    public static void setTime(int setH, int setM, int setS) {
        hh = setH;
        mm = setM;
        ss = setS;
    }
    public static int time = hh * 60 * 60 + mm * 60 + ss;
    public static String timeDisplay = "00 : 00 : 00";
    public static boolean condition = true;


    public static void run() {
        if (condition) {
            while (time > 0) {
                time--;
                try {
                    Thread.sleep(1000);
                    int h = time / 60 / 60 % 60;
                    int m = time / 60 % 60;
                    int s = time % 60;
                    timeDisplay = h + " : " + m + " : " + s;
                    System.out.println(timeDisplay);
                } catch(InterruptedException e) {
                    e.printStackTrace();
                }
            }
        } else {
            return;
        }
    }
    public static String getDisplay() {
        return timeDisplay;
    }
    public static void main(String[] args) {
        run();
    }
    public static void setCondition() {
        condition = false;
    }

}
