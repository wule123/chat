package com.wl.chat.websocket;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import org.springframework.web.socket.config.annotation.EnableWebSocket;
import org.springframework.web.socket.config.annotation.WebSocketConfigurer;
import org.springframework.web.socket.config.annotation.WebSocketHandlerRegistry;
import org.springframework.web.socket.handler.TextWebSocketHandler;

/**
 * Created by Administrator on 2017/4/2.
 */
@Configuration
//@EnableWebMvc
@EnableWebSocket
public class SpringWebSocketConfig extends WebMvcConfigurerAdapter implements WebSocketConfigurer {
	public void registerWebSocketHandlers(WebSocketHandlerRegistry registry) {
		registry.addHandler(webSocketHandler(), "/websocket/socketServer.json").setAllowedOrigins("*")
				.addInterceptors(new SpringWebSocketHandlerInterceptor());
		registry.addHandler(webSocketHandler(), "/sockjs/socketServer.json").setAllowedOrigins("*")
				.addInterceptors(new SpringWebSocketHandlerInterceptor()).withSockJS();
	}

	@Bean
	public TextWebSocketHandler webSocketHandler() {
		return new SpringWebSocketHandler();
	}

}