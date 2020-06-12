package netty.sectionone;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelFutureListener;
import io.netty.channel.ChannelHandler;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.util.CharsetUtil;

/**
 * 所有的Netty服务器都需要以下两部分
 * 1. 至少一个ChannelHandler——该组件实现了服务器对从客户端接收的数据的处理，即它的业务逻辑
 * 2. 引导——这是配置服务器的启动代码。至少，它会将服务器绑定到它要监听链接请求的端口上
 *
 * 该类为Handler组件
 */
//标识一个ChannelHandler可以被多个Channel安全的共享
@ChannelHandler.Sharable
public class EchoServerHandler extends ChannelInboundHandlerAdapter {

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        ByteBuf in = (ByteBuf) msg;
        //将消息记录到控制台
        System.out.println("Server received: " + in.toString(CharsetUtil.UTF_8));
        //将接受到的消息写给发送者，而不是刷出站消息
        ctx.write(in);
    }

    @Override
    public void channelReadComplete(ChannelHandlerContext ctx) throws Exception {
        //将未决消息（pendingmessage）冲刷到远程节点，并且关闭该Channel
        // pending messge :指目前暂存与ChannelOutboundBuffer中的消息，在下一次调用flush()或者writeAndFlush()方法时将会尝试写出到套接字
        ctx.writeAndFlush(Unpooled.EMPTY_BUFFER).addListener(ChannelFutureListener.CLOSE);
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        //打印异常栈跟踪
        cause.printStackTrace();
        //关闭该channel
        ctx.close();
    }
}
