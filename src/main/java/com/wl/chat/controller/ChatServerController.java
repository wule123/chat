package com.wl.chat.controller;

import com.wl.chat.server.SimpleChatServer;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class ChatServerController {

    @RequestMapping(value = "start",method = RequestMethod.GET)
    public void startServer(){
        SimpleChatServer singleton = SimpleChatServer.getSingleton();
        singleton.startServer();
    }
}
