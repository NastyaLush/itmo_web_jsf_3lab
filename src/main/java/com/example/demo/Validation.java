package com.example.demo;

import java.sql.Time;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.Date;

public class Validation {
    private boolean isValid = true;
    private final SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd  HH:mm:ss ");

    public Result checkResult(Result result, long time){
        if(! (between(result.getX(), -5, 5)
        && between(result.getY(), -5, 3)
            && between(result.getR(), 2,5))){
            isValid =false;
            return null;
        }
        if(isInRectangle(result.getX(), result.getY(), result.getR()) ||
        isInTriangle(result.getX(), result.getY(), result.getR()) ||
        isInSector(result.getX(), result.getY(), result.getR())){
            result.setResultHit(ResultHit.HIT);
        } else{
            result.setResultHit(ResultHit.LOSE);
        }
        System.out.println(isInRectangle(result.getX(), result.getY(), result.getR()) );
        System.out.println(isInTriangle(result.getX(), result.getY(), result.getR()) );
        System.out.println(isInSector(result.getX(), result.getY(), result.getR()) );

        result.setDate(formatter.format(new Date(System.currentTimeMillis())));
        result.setTimeScript(Math.floor((System.nanoTime() - time)/(Math.pow(10,3)))/1000);
        System.out.println(result.getTimeScript());
        return result;
    }

    private final boolean between(double x, double left, double right){
        return x>= left && x<=right;
    }
    private boolean isInTriangle(double x, double y, double r){
        return between(x, -r, 0) && between(y, -r-x, 0);

    }
    private final boolean isInRectangle(double x, double y, double r){
        return between(x, 0, r/2) && between(y, -r,0 );
    }
    private final boolean isInSector(double x, double y, double r){
        return between(x, 0, r/2) && between(y, 0 , Math.sqrt(Math.pow(r/2, 2) - Math.pow(x,2)));
    }
}
