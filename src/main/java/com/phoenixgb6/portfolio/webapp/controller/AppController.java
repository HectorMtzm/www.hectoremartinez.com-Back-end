package com.phoenixgb6.portfolio.webapp.controller;

import com.phoenixgb6.portfolio.sudoku.model.SudokuBoard;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/")
public class AppController {

    @GetMapping("/portfolio")
    public String portfolio(){
        return "";
    }
}
