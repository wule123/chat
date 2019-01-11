package com.wl.chat.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class HelloWorld {


    @RequestMapping(value = "hello",method = RequestMethod.GET)
    @ResponseBody
    public String getHello(){
        return "hello ";
    }
}
