package com.geekbang.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class Login {

    @GetMapping("/index")
    public String index() {
        return "index";
    }
}
