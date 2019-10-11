package com.exxk.netty;


import io.netty.channel.ChannelHandler;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.handler.codec.http.websocketx.BinaryWebSocketFrame;
import io.netty.handler.codec.http.websocketx.TextWebSocketFrame;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Component
@ChannelHandler.Sharable
public class WebSocketFrameHandler extends SimpleChannelInboundHandler<Object> {
    private final Logger log = LoggerFactory.getLogger(WebSocketFrameHandler.class);

    @Override
    protected void channelRead0(ChannelHandlerContext ctx, Object msg) throws Exception {
//        接收数据示例：
//        TextWebSocketFrame(data: PooledUnsafeDirectByteBuf(ridx: 0, widx: 64, cap: 64))
//        数据类型解释TextWebSocketFrame代表数据类型为文本类型，BinaryWebSocketFrame代表二进制类型
//        cap代表数据长度，默认为65536，需要大数据修改pipeline.addLast(new WebSocketServerProtocolHandler(,,,65536*10))
        log.info("接收"+ ctx.channel().id().asLongText()+"的数据类型：{}", msg);
        if (msg instanceof TextWebSocketFrame) {
            log.info("text {}", ((TextWebSocketFrame) msg).text());
        } else if (msg instanceof BinaryWebSocketFrame) {
            log.info("binary {}",((BinaryWebSocketFrame) msg).content());
        }
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
