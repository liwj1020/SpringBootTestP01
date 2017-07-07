package com.liwj;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.env.Environment;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class WebController {

    @Value("${test.msg}")
    private String msg;

    @Autowired
    private Environment env;


    @RequestMapping(value = "index1", method = RequestMethod.GET)
    public String index() {
        return "The Way 1 : " + msg;
    }

    @GetMapping("/index2")
    public String index2() {
        return " the way :" + env.getProperty("test.msg");
    }
}