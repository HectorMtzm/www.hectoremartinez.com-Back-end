package com.phoenixgb6.portfolio.sudoku.utility;

import java.lang.reflect.Constructor;

public class Pair<I> {
    private final int first;
    private final int second;

    public Pair(int first, int second) {
        this.first = first;
        this.second = second;
    }

    public int getX(){
        return first;
    }

    public int getY(){
        return second;
    }

}
