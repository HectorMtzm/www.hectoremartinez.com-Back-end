package com.phoenixgb6.portfolio.sudoku.utility;

//Class used to store coordinates

public class Pair<T> {
    private final T first;
    private final T second;

    public Pair(T first, T second) {
        this.first = first;
        this.second = second;
    }

    public T getX(){
        return first;
    }

    public T getY(){
        return second;
    }

}
