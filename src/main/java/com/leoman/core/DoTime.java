package com.leoman.core;


import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Timer;
import java.util.TimerTask;

/**
 * Created by Administrator on 2016/3/10.
 */
public class DoTime {

    private Timer timer = null;

    public DoTime(int time) {
        timer = new Timer();
        timer.schedule(new Mywork(), (new Date(System.currentTimeMillis() + time * 1000)));
    }
    class Mywork extends TimerTask {
        @Override
        public void run() {
            System.out.println("now is:" +  new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()));
            timer.cancel();
        }
    }

    public static void main(String[] args) {
        new DoTime(5);
        new DoTime(7);
    }
}



