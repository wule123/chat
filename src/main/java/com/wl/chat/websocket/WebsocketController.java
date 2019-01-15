package com.wl.chat.websocket;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.socket.TextMessage;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.Map;


@Controller
public class WebsocketController {
	private final static Log logger = LogFactory.getLog(WebsocketController.class);

    @Bean//这个注解会从Spring容器拿出Bean
    public SpringWebSocketHandler infoHandler() {
        return new SpringWebSocketHandler();
    }

    @RequestMapping("/websocket/login")
    public ModelAndView login(HttpServletRequest request, HttpServletResponse response) throws Exception {
        String username = request.getParameter("username");
        System.out.println(username+"登录");
        HttpSession session = request.getSession();
        session.setAttribute("username", username);
        Map map = new HashMap();
        map.put("username",username);
        return new ModelAndView("/send",map);
    }

    @RequestMapping("/websocket/request")
    @ResponseBody
    public Map test(HttpServletRequest request, HttpServletResponse response) throws Exception {
        Map map = new HashMap();
    	String username = request.getParameter("username");
        System.out.println(username+"登录");
        HttpSession session = request.getSession();
        session.setAttribute("username", username);
        map.put("test", "ok");
        return map;
    }


    @RequestMapping("/websocket/send")
    @ResponseBody
    public String send(HttpServletRequest request) {
        String username = request.getParameter("username");
        infoHandler().sendMessageToUser(username, new TextMessage("你好，测试！！！"));
        return "ok";
    }
}