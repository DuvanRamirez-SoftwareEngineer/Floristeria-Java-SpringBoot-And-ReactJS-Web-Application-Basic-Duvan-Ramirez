package com.floristeria.floristeria.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping(value="/")
public class HomeController {
    
    @GetMapping("index")
    public String home(Model model) {
        return "index";
    }
}