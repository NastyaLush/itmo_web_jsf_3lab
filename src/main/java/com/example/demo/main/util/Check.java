package com.example.demo.main.util;



import com.example.demo.main.data.Conclusion;
import com.example.demo.main.data.Result;
import com.example.demo.main.data.RowResult;

import java.time.LocalDateTime;
import java.util.Date;

public class Check {
    public static Result getResult(RowResult result) {
        long timeBegin = new Date().getTime();
        if(result != null) {
            return new Result(roundToThree(result.getX()),
                    roundToThree(result.getY()),
                    roundToThree(result.getR()),
                    isInShape(result.getX(),
                            result.getY(),
                            result.getR()) ? Conclusion.HIT : Conclusion.MISS,
                    LocalDateTime.now(), new Date().getTime() - timeBegin
            );
        }
        return null;
    }

    private static boolean isInShape(double x, double y, double r) {
        return isInRect(x, y, r) || isInSector(x, y, r) || isInTriangle(x, y, r);
    }

    private static boolean isInRect(double x, double y, double r) {
       return between(x, r, 0) && between(y, 0, -r / 2);
    }

    private static boolean isInSector(double x, double y, double r) {
        return between(x, 0, -r) && between(y, 0, -Math.sqrt(r * r - x * x));
    }

    private static boolean isInTriangle(double x, double y, double r) {
        return between(x, 0, -r) && between(y, r + x, 0);
    }

    private static boolean between(double var, double begin, double end) {
        return var <= begin && var >= end;
    }

    private static double roundToThree(double number) {
        return (double) ((int) (number * 1000)) / 1000;
    }
}
