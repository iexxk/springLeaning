package com.exxk.netty;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;


@Configuration
public class ConfigData {

	@Value("${netty.websocket.port}")
	public Integer websocketPort;

	@Value("${netty.websocket.path}")
	public String websocketPath;

	@Value("${netty.websocket.maxFrameSize}")
	public Integer maxFrameSize;
	
}
