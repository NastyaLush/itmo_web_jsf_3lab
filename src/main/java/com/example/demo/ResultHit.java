package com.example.demo;

public enum ResultHit {
    HIT("hit"),
    LOSE("lose");

    ResultHit(String hit) {
    }
    public static ResultHit getResultHit(String result){
        if(result=="hit") return HIT;
        if(result=="lose") return LOSE;
        return null;
    }
}
