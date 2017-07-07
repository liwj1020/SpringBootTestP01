package com.liwj;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "config")
public class ConfigController {

    @Autowired
    private MyWebConfig myWebConfig;

    //    @RequestMapping(value = "index", method = RequestMethod.GET)
    @GetMapping("/index")
    public String index() {
        return "webName: " + myWebConfig.getName() + ", webVersion: " +
                myWebConfig.getVersion() + ", webAuthor: " + myWebConfig.getAuthor();
    }
}