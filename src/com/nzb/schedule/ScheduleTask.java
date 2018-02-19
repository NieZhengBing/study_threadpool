package com.nzb.schedule;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @author M
 * @create 2018/2/19
 */
public class ScheduleTask implements Runnable {
    public static enum OpenType {
        None, OnlyThrowException, CatchException
    }

    public static SimpleDateFormat formater = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

    private OpenType openType;

    public ScheduleTask(OpenType openType) {
        this.openType = openType;
    }

    @Override
    public void run() {
        switch (openType) {
            case OnlyThrowException:
                System.out.println("Exception not catch: " + formater.format(new Date()));
                throw new RuntimeException("OnlyThrowException");
            case CatchException:
                try {
                    throw new RuntimeException("CatchException");
                } catch (RuntimeException e) {
                    System.out.println("Exception be catched: " + formater.format(new Date()));
                }
                break;
            case None:
                System.out.println("None: " + formater.format(new Date()));
        }
    }
}
