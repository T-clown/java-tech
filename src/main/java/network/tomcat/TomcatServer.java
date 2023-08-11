package network.tomcat;


import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelHandlerAdapter;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelOption;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.handler.codec.http.HttpRequest;
import io.netty.handler.codec.http.HttpRequestDecoder;
import io.netty.handler.codec.http.HttpResponseEncoder;
import network.tomcat.http.AbstractServlet;
import network.tomcat.http.Request;
import network.tomcat.http.Response;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.FileInputStream;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;


/**
 * Netty就是一个同时支持多协议的网络通信框架
 */
public class TomcatServer {
    private static final Logger logger = LoggerFactory.getLogger(TomcatServer.class);
    //打开Tomcat源码，全局搜索ServerSocket

    private final int port = 8081;

    private Map<String, AbstractServlet> servletMapping = new HashMap<>();

    private Properties webXmlConfigure = new Properties();

    private void init() {

        //加载web.xml文件,同时初始化 ServletMapping对象
        try {
            String classpath = this.getClass().getResource("/").getPath();
            FileInputStream fis = new FileInputStream(classpath + "web.properties");

            webXmlConfigure.load(fis);

            for (Object k : webXmlConfigure.keySet()) {

                String key = k.toString();
                if (key.endsWith(".url")) {
                    String servletName = key.replaceAll("\\.url$", "");
                    String url = webXmlConfigure.getProperty(key);
                    String className = webXmlConfigure.getProperty(servletName + ".className");
                    AbstractServlet obj = (AbstractServlet) Class.forName(className).getConstructor().newInstance();
                    servletMapping.put(url, obj);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void start() {

        init();

        //eventLoop-1-XXX

        //Netty封装了NIO，Reactor模型，Boss，worker
        // Boss线程
        EventLoopGroup bossGroup = new NioEventLoopGroup();
        // Worker线程
        EventLoopGroup workerGroup = new NioEventLoopGroup();
        try {

            //1、创建对象
            // Netty服务
            //ServerBootstrap   ServerSocketChannel
            ServerBootstrap server = new ServerBootstrap();

            //2、配置参数
            // 链路式编程
            server.group(bossGroup, workerGroup)
                    // 主线程处理类,看到这样的写法，底层就是用反射
                    .channel(NioServerSocketChannel.class)
                    // 子线程处理类 , Handler
                    .childHandler(new ChannelInitializer<SocketChannel>() {
                        // 客户端初始化处理
                        @Override
                        protected void initChannel(SocketChannel client) {
                            // 无锁化串行编程
                            //Netty对HTTP协议的封装，顺序有要求
                            // HttpResponseEncoder 编码器
                            // 责任链模式，双向链表Inbound OutBound
                            client.pipeline().addLast(new HttpResponseEncoder());
                            // HttpRequestDecoder 解码器
                            client.pipeline().addLast(new HttpRequestDecoder());
                            // 业务逻辑处理
                            client.pipeline().addLast(new TomcatHandler());
                        }
                    })
                    // 针对主线程的配置 分配线程最大数量 128
                    .option(ChannelOption.SO_BACKLOG, 128)
                    // 针对子线程的配置 保持长连接
                    .childOption(ChannelOption.SO_KEEPALIVE, true);

            //3、启动服务器
            ChannelFuture f = server.bind(port).sync();
            logger.info(" Tomcat Start Successful，Listen Port：[{}]", port);
            f.channel().closeFuture().sync();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            // 关闭线程池
            bossGroup.shutdownGracefully();
            workerGroup.shutdownGracefully();
        }
    }

    public class TomcatHandler extends ChannelHandlerAdapter {

        @Override
        public void channelRead(ChannelHandlerContext context, Object msg) throws Exception {

            if (msg instanceof HttpRequest) {

                HttpRequest httpRequest = (HttpRequest) msg;

                // 转交给我们自己的request实现
                Request request = new Request(context, httpRequest);
                // 转交给我们自己的response实现
                Response response = new Response(context, httpRequest);
                // 实际业务处理
                String url = request.getUrl();

                if (servletMapping.containsKey(url)) {
                    AbstractServlet servlet = servletMapping.get(url);
                    logger.info("Request Servlet:[{}]", servlet.getClass().getName());
                    servlet.service(request, response);
                } else {
                    response.write("404 - Not Found");
                }

            }
        }

        @Override
        public void exceptionCaught(ChannelHandlerContext context, Throwable throwable) throws Exception {

        }

    }


    public static void main(String[] args) {
        new TomcatServer().start();
    }

}
