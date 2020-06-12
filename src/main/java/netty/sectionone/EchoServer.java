package netty.sectionone;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;

import java.net.InetSocketAddress;

/**
 * 引导服务器
 *
 * 绑定到服务器将在其上监听并接受传入连接请求的端口
 * 配置Channel，以将有关的入站消息通知给EchoServerHandler实例
 */
public class EchoServer {

    private final int port;

    public EchoServer(int port) {
        this.port = port;
    }

    public static void main(String[] args) throws Exception{
        //设置端口值
        int port = 9999;
        //调用服务器的start()方法
        new EchoServer(port).start();
    }

    public void start() throws Exception {
        final EchoServerHandler serverHandler = new EchoServerHandler();
        //1.创建EventLoopGroup
        EventLoopGroup group = new NioEventLoopGroup();
        try {
            //2.创建ServerBootstrap
            ServerBootstrap b = new ServerBootstrap();
            b.group(group)
                    .channel(NioServerSocketChannel.class)  //3.指定所使用的 NIO传输Channel
                    .localAddress(new InetSocketAddress(port))    //4.使用指定的端口设置套接字地址
                    .childHandler(new ChannelInitializer<SocketChannel>() {  //5.添加一个EchoServerHandler到子Channel的ChannelPipeline
                        @Override
                        protected void initChannel(SocketChannel socketChannel) throws Exception {
                            //EchoServerHandler被标注为@Shareable,所以我们可以总是使用同样的实例
                            socketChannel.pipeline().addLast(serverHandler);
                        }
                    });
            //6.异步地绑定服务器，调用sync()方法阻塞等待直到绑定完成
            ChannelFuture f = b.bind().sync();
            //7.获取Channel的CloseFuture,并且阻塞当前线程直到它完成
            f.channel().closeFuture().sync();
        } finally {
            //8.关闭EventLoopGroup,释放所有资源
            group.shutdownGracefully().sync();
        }
    }
}
