package netty.sectionone;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelHandler;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.util.CharsetUtil;

/**
 * Echo客户端, 它将会
 *
 * 1. 连接到服务器
 * 2. 发送一个或多个消息
 * 3. 对于每个消息，等待并接受从服务器发回的相同的消息
 * 4. 关闭连接
 * 编写客户端所涉及的两个主要代码部分也是业务逻辑和引导
 */
@ChannelHandler.Sharable //标记该类的实例可以被多个Channel共享
public class EchoClientHandler extends SimpleChannelInboundHandler<ByteBuf> {

    /**
     * 此处重写了channelActive()方法，其将在连接建立时被调用。这确保了数据将会被尽可能快地写入服务器，
     * 其在这个场景下是一个编译了字符串“Netty rocks!” 的字节缓冲区
     */
    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        //当被通知Channel是活跃的时候，发送一条消息
        ctx.writeAndFlush(Unpooled.copiedBuffer("Netty rocks!", CharsetUtil.UTF_8));
    }

    /**
     * 接下来重写了channelRead0()方法。每当接受数据时，都会调用这个方法。需要注意的时，由服务器
     * 发送的消息可能会被分块接收。也就是说，如果服务器发送了5字节，那么不能保证这5字节会被一次性接收。
     * 即使是对于这么少量的数据，channelRead0（）方法也可能会被调用两次，第一次使用一个持有3字节的Bytebuf(Netty的字节容器)，
     * 第二次使用一个持有2字节的ByteBuf.作为一个面向流的协议，TCP保证了字节数组将会按照服务器发送他们的顺序被接受
     */
    @Override
    protected void channelRead0(ChannelHandlerContext channelHandlerContext, ByteBuf in) throws Exception {
        //记录已接受消息的转储
        System.out.println("Client received: " + in.toString(CharsetUtil.UTF_8));
    }

    /**
     * 记录Throwable,关闭Channel
     */
    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        //在发生异常时，记录错误并关闭Channel
        cause.printStackTrace();
        ctx.close();
    }
}
