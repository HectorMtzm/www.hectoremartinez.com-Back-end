package com.phoenixgb6.portfolio.sudoku.model;


import com.phoenixgb6.portfolio.sudoku.utility.Pair;

public class SudokuBoard {

    //Integer to be able to set the value to null. the board inputs will be empty instead of '0'
    private Integer[][] board;
    private int boardLength;

    //creates an empty board when the endpoint /home is accessed
    public SudokuBoard() {
        this.board = new Integer[9][9];
        this.boardLength = board.length;
    }

    // Recursively traverse the board brute forcing values.
    public boolean solve(Integer[][] board, int boardLength) {

        //coordinates variables
        int i = 0, j = 0;

        Pair<Integer> coord = findEmptySpace();

        //if an empty space is found, extract the coordinates, else, the board is solved.
        if (coord != null) {
            i = coord.getX();
            j = coord.getY();
        } else
            return true;

        for (int val = 1; val <= boardLength; val++) {
            if (valid(i, j, val)) {
                board[i][j] = val;
                if (solve(board, boardLength))
                    return true;
                else
                    board[i][j] = 0;
            }
        }
        return false;
    }

    //find an empty space (0)
    private Pair<Integer> findEmptySpace() {
        for (int i = 0; i < boardLength; i++) {
            for (int j = 0; j < boardLength; j++) {
                if (board[i][j] == 0) {
                    return new Pair<Integer>(i, j);
                }
            }
        }
        return null;
    }

    private boolean valid(int cordH, int cordV, int value) {

        //checks if the horizontal line is valid with the new number
        for (int i = 0; i < boardLength; i++) {
            if (board[i][cordV] == value) {
                return false;
            }
        }

        //Checks vertical line is valid with the new number
        for (int j = 0; j < boardLength; j++) {
            if (board[cordH][j] == value) {
                return false;
            }
        }

        int boxStartH = (cordH - cordH % 3);    //Points to the beginning of the box. i
        int boxStartV = (cordV - cordV % 3);    //Points to the beginning of the box. j

        //Checks if the square section is valid with the new number
        for (int i = boxStartH; i < boxStartH + 3; i++) {
            for (int j = boxStartV; j < boxStartV + 3; j++) {
                if (board[i][j] == value)
                    return false;
            }
        }
        return true;
    }

    // To print the board in the console if needed
    public void printBoard3x3() {
        try {
            Thread.sleep(120);
        } catch (InterruptedException e1) {
            e1.printStackTrace();
        }

        for (int i = 0; i < boardLength; i++) {
            if (i == 3 || i == 6)
                System.out.println("-----------------------");
            for (int j = 0; j < boardLength; j++) {
                if (j == 3 || j == 6)
                    System.out.print("| ");
                System.out.print(board[i][j] + " ");
            }
            System.out.println();
            if (i == boardLength - 1)
                System.out.println();
        }
    }

    // makes sure the user inputs are valid before starting the recursion.
    public boolean validUserInput(){
        int value;

        setZero();

        for (int i = 0; i < boardLength; i++) {
            for (int j = 0; j < boardLength; j++) {
                if(board[i][j] != 0){
                    value = board[i][j];
                    board[i][j] = 0;
                    if(!valid(i,j,value)){
                        return false;
                    }
                    else{
                        board[i][j] = value;
                    }
                }
            }
        }

        return true;
    }

    // Set the null spaces to 0 after a board is received to avoid a NullPointerException
    private void setZero() {
        for (int row = 0; row < board.length; row++) {
            for (int col = 0; col < board[row].length; col++) {
                if(board[row][col] == null || board[row][col].equals("/^[a-zA-Z0-9!@#$%^&*()_+\\-=\\[\\]{};':\"\\\\|,.<>\\/?]*$/"))
                    board[row][col] = 0;
            }
        }
    }

    public Integer[][] getBoard() {
        return board;
    }

    public int getBoardLength() {
        return boardLength;
    }
}