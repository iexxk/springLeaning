package com.exxk.netty;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import java.net.InetSocketAddress;

@Component
public class NettyServer {
    private final Logger log = LoggerFactory.getLogger(NettyServer.class);

    /**
     * boss 线程组用于处理连接工作
     */
    EventLoopGroup boosGrop = new NioEventLoopGroup();
    /**
     * work 线程组用于数据处理
     */
    EventLoopGroup workerGrop = new NioEventLoopGroup();

    @Autowired
    ConfigData configData;

    @Autowired
    WebSocketChannelInitaializer webSocketChannelInitaializer;

    /**
     * 启动Netty Server
     *
     * @throws InterruptedException
     */
//    @PostConstruct  //springboot初始化后执行,该启动模式存在，不能访问数据库，形成堵塞
    public void start() throws InterruptedException {


        try {
            ServerBootstrap serverBootstrap = new ServerBootstrap();
            //使用服务端初始化自定义类WebSocketChannelInitaializer
            serverBootstrap.group(boosGrop, workerGrop)
                    .channel(NioServerSocketChannel.class)
                    .childHandler(webSocketChannelInitaializer);

            //使用了不同的端口绑定方式
            ChannelFuture channelFuture = serverBootstrap.bind(new InetSocketAddress(configData.websocketPort)).sync();
            //关闭连接
            channelFuture.channel().closeFuture().sync();
            log.info("Netty start success");

        } finally {
            //退出，释放线程资源
            boosGrop.shutdownGracefully().sync();
            workerGrop.shutdownGracefully().sync();
        }


    }

//    @PreDestroy //springboot销毁时执行
//    public void destory() throws InterruptedException {
//        boosGrop.shutdownGracefully().sync();
//        workerGrop.shutdownGracefully().sync();
//        log.info("Netty close");
//    }

}
