package com.wl.chat.controller;

import com.alibaba.fastjson.JSONObject;
import com.wl.chat.message.MessageUtils;
import com.wl.chat.websocket.SpringWebSocketHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.socket.TextMessage;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.Map;

@Controller
public class ChatServerController {
    @Autowired
    SpringWebSocketHandler springWebSocketHandler;

    @RequestMapping("/open")
    public ModelAndView login(HttpServletRequest request, HttpServletResponse response) throws Exception {
        String username = "wen";
        request.getParameter("username");
        System.out.println(username + "登录");
        Map map = new HashMap();
        map.put("username", username);
        return new ModelAndView("/admin", map);
    }

    @RequestMapping("/useropen")
    public ModelAndView useropen(HttpServletRequest request, HttpServletResponse response) throws Exception {
        String username = "le";//request.getParameter("username");
        System.out.println(username + "登录");
        Map map = new HashMap();
        map.put("username", username);
        return new ModelAndView("/user", map);
    }

    @RequestMapping("/sendMsg")
    @ResponseBody
    public JSONObject sendMsg(String userName,String msg) {
        MessageUtils messageUtils = new MessageUtils();
        messageUtils.setMessage(msg);
        messageUtils.setSender(userName);
        if(userName.equals("wen")){
            messageUtils.setReceiver("le");
        }else{
            messageUtils.setReceiver("wen");
        }
        TextMessage textMessage = new TextMessage(JSONObject.toJSONString(messageUtils));
        springWebSocketHandler.sendMessageToUser(messageUtils.getReceiver(),textMessage);
        return new JSONObject();
    }
}