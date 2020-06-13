package com.phoenixgb6.portfolio.sudoku.controller;

import com.fasterxml.jackson.databind.JsonMappingException;
import com.phoenixgb6.portfolio.sudoku.model.SudokuBoard;
import javassist.tools.web.BadHttpRequest;
import org.springframework.data.util.Pair;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.net.BindException;

@Controller
@RequestMapping("/portfolio/sudoku")
public class SudokuController {

    @GetMapping
    public String home(Model model) {
        model.addAttribute(new SudokuBoard());
        return "sudoku/home";
    }

    @PostMapping
    public String solveSudoku(@ModelAttribute("sudokuBoard") SudokuBoard sudokuBoard, Model model){

        // sets the letters and special characters to 0 to avoid an NullPointerException and then validates the
        // user input to make sure the board is valid before starting backtracking.
        if(sudokuBoard.validUserInput()){
            // If the board can be solved, add the solved board to the model, else, print an error message
            if(sudokuBoard.solve(sudokuBoard.getBoard(), sudokuBoard.getBoardLength())){
                model.addAttribute(sudokuBoard);
            }
            else {
                model.addAttribute(new SudokuBoard());
                model.addAttribute("invalid", 1);
            }
        }
        else {
            model.addAttribute(new SudokuBoard());
            model.addAttribute("invalid", 2);
        }
        return "sudoku/home";
    }
}
