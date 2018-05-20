package kr.ac.cnu.web.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * Created by rokim on 2018. 5. 13..
 */
@Controller
public class HelloController {
    @RequestMapping(value = "/hello", method = RequestMethod.GET)
    @ResponseBody
    public String hello(@RequestParam(required = false) String message) {

        return "Hello, World!" + message;
    }

    @GetMapping(value = "/hello/{name}")
    @ResponseBody
    public String path(@PathVariable String name) {
        return "Hello " + name;
    }

    @PostMapping(value = "/hello/{name}")
    @ResponseBody
    public String pathPost(@PathVariable String name) {
        return "Hello " + name;
    }

    @GetMapping(value = "/ddanzi")
    public String ddanzi() {
        return "ddanzi";
    }

}
