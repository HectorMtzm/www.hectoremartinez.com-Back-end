package com.phoenixgb6.portfolio.webapp.controller;

import com.phoenixgb6.portfolio.sudoku.model.SudokuBoard;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping
public class PortfolioController {

    @GetMapping("/portfolio")
    public String portfolio(){
        return "portfolio/portfolio";
    }

    @GetMapping("/portfolio/universitycrm")
    public String universitycrm(){
        return "portfolio/universitycrm";
    }
}
