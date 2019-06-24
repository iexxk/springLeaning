package com.exxk.netty;


import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.handler.codec.http.websocketx.TextWebSocketFrame;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.net.InetSocketAddress;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

public class TextWebSocketFrameHandler extends SimpleChannelInboundHandler<TextWebSocketFrame> {
    private final Logger log = LoggerFactory.getLogger(TextWebSocketFrameHandler.class);


    @Override
    protected void channelRead0(ChannelHandlerContext ctx, TextWebSocketFrame msg) throws Exception {
        log.info("接收" + ctx.channel().id().asLongText() + "的数据类型：{}", msg);
        log.info("text {}", msg.text());

        // 如果是向客户端发送文本消息，则需要发送 TextWebSocketFrame 消息
        InetSocketAddress inetSocketAddress = (InetSocketAddress) ctx.channel().remoteAddress();
        String ip = inetSocketAddress.getHostName();
        String txtMsg = "[" + ip + "][" + LocalTime.now().format(DateTimeFormatter.ofPattern("HH:mm:ss")) + "] ==> " + msg.text();
        ctx.channel().writeAndFlush(new TextWebSocketFrame(txtMsg));
    }


    /**
     * 连接建立时
     *
     * @param ctx
     * @throws Exception
     */
    @Override
    public void handlerAdded(ChannelHandlerContext ctx) throws Exception {
        log.info("handlerAddred {}", ctx.channel().id().asLongText());
    }

    /**
     * 连接关闭时
     *
     * @param ctx
     * @throws Exception
     */
    @Override
    public void handlerRemoved(ChannelHandlerContext ctx) throws Exception {
        log.info("handlerRemoved {}", ctx.channel().id().asLongText());
    }

    /**
     * 异常发生时
     *
     * @param ctx
     * @param cause
     * @throws Exception
     */
    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        log.info(ctx.channel().id().asLongText() + "异常发生 {}", cause.getMessage());
        ctx.close();
    }


}
