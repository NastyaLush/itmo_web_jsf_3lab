package com.example.demo.mbean;

import com.example.demo.data.Result;
import com.example.demo.data.ResultHit;
import lombok.Getter;

import javax.management.Notification;
import javax.management.NotificationBroadcasterSupport;

import java.lang.Math;


public class PointManager  extends NotificationBroadcasterSupport implements PointManagerMBean {
    @Getter
    private int establishedPoints;
    @Getter
    private int hitPoints;
    private final double max=4;

    public void onClick(Result result){
        System.out.println("execute point manager");
        if(result.getResultHit()== ResultHit.HIT) hitPoints++;
        establishedPoints++;
        if(Math.abs(result.getX())>max || Math.abs(result.getY())>max){
            Notification notification = new Notification("coordsOutOfGraph", this, establishedPoints,
                    "Coords of shot are out of rendered graph");
            sendNotification(notification);
        }
    }

}
