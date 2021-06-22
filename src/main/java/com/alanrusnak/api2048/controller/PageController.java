package com.alanrusnak.api2048.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class PageController {

    @RequestMapping("/2048")
    public String get2048Page() {
        return "2048-ui";
    }
}
