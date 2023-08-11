package network.io.block;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;

/**
 * 阻塞IO
 * https://mp.weixin.qq.com/s/-d_SJlAlLIzfi9TxAeoapA
 * https://mp.weixin.qq.com/s/7gfPgqurqA-RJlbaShfRcQ
 */
public class SocketServer {
    public static void main(String[] args) throws IOException {

        ServerSocketChannel serverSocketChannel = ServerSocketChannel.open();

        // 监听 8081 端口进来的 TCP 链接
        serverSocketChannel.socket().bind(new InetSocketAddress(8081));

        while (true) {
            // 这里会阻塞，直到有一个请求的连接进来
            SocketChannel socketChannel = serverSocketChannel.accept();

            // 开启一个新的线程来处理这个请求，然后在 while 循环中继续监听 8080 端口
            SocketHandler handler = new SocketHandler(socketChannel);
            new Thread(handler).start();
        }
    }
}
