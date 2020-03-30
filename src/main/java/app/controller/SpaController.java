package app.controller;

import org.springframework.stereotype.Controller;

@Controller
public class SpaController {
//    @RequestMapping(value = "/{path:[^\\.]*}")
    public String redirect() {
        return "forward:/";
    }
}