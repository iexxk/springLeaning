package com.exxk.netty;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;


@Configuration
public class ConfigData {

	@Value("${netty.websocket.port}")
	public Integer websocketPort;
	
}
