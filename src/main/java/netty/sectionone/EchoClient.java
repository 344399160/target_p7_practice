package netty.sectionone;

import io.netty.bootstrap.Bootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;

import java.net.InetSocketAddress;

/**
 * 客户端的主类
 *
 * 引导客户端类似于引导服务器，不同的是，客户端是使用主机和端口参数来连接远程地址，
 * 也就是这里的Echo服务器的地址，而不是绑定到一个一直被监听的端口。
 */
public class EchoClient {

    private final String host;
    private final int port;

    public EchoClient(String host, int port) {
        this.host = host;
        this.port = port;
    }

    public void start() throws Exception {
        EventLoopGroup group = new NioEventLoopGroup();
        try {
            //创建Bootstrap
            Bootstrap b = new Bootstrap();
            b.group(group) //指定EventLoopGroup 以处理客户端事件；需要适用于NIO的实现
                    .channel(NioSocketChannel.class)  //适用于NIO传输的Channel类型
                    .remoteAddress(new InetSocketAddress(host, port))
                    .handler(new ChannelInitializer<SocketChannel>() { //在创建Channel时，向ChannelPipeline中添加一个EchoClientHandler实例
                        @Override
                        protected void initChannel(SocketChannel socketChannel) throws Exception {
                            socketChannel.pipeline().addLast(new EchoClientHandler());
                        }
                    });
            //连接到远程节点，阻塞等待直到连接完成
            ChannelFuture f = b.connect().sync();
            //阻塞，直到Channel关闭
            f.channel().closeFuture().sync();
        } finally {
            //关闭线程池并且释放所有资源
            group.shutdownGracefully().sync();
        }
    }

    public static void main(String[] args) throws Exception{
        String host = "127.0.0.1";
        int port = 9999;
        new EchoClient(host, port).start();
    }
}
