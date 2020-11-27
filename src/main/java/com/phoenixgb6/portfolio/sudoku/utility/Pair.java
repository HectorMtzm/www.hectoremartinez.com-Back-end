package com.phoenixgb6.portfolio.sudoku.utility;

public class Pair<I> {
    private final I first;
    private final I second;

    public Pair(I first, I second) {
        this.first = first;
        this.second = second;
    }

    public I getX(){
        return first;
    }

    public I getY(){
        return second;
    }

}
