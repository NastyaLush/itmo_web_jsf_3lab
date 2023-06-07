package com.example.demo.mbean;

import lombok.Getter;
public class Square implements SquareMBean{
    @Getter
    double square;
    @Override
    public void updateSquare(int r) {
        square=r*r+Math.PI*r*r/16;
    }
}
