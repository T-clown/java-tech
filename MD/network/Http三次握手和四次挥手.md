原文：https://mp.weixin.qq.com/s/jW4JWM3ATDX2ej7VFqMqBA
三次握手(Three-way Handshake)，是指在建立一个TCP连接时候，需要客户端和服务器总共发送3个数据包
1.client---(发送SYN=1,Seq=X)--->server    
发送完成后客户端进入SYN_SEND状态
2.server---(发送SYN=1,ACK=X+1,Seq=Y)--->client  
发送完成后服务端进入SYN_RCVD状态
3.client---(发送ACK=Y+1,Seq=Z)--->server
客户端发送完毕后，进入ESTABLISHED状态，服务端接收到这个数据包，也进入ESTABLISHED状态, TCP握手结束


断开TCP连接需要一共发送四个数据包，因此称为四次挥手(four-way handshake)。
客户端或服务器均可主动发起挥手动作（即断开连接操作）
1.client---(发送Fin=1,ACK=Z,Seq=X)--->server
发送完成后，客户端进入FIN_WAIT_1状态
2.server---(发送ACK=X+1,Seq=Z)--->client
发送完成后，服务端进入CLOSE_WAIT状态，客户端收到这个包后，进入FIN_WAIT_2，等待服务器关闭连接
3.server---(发送Fin=1,ACK=X,Seq=Y)--->client
发送完成后，服务端进入LAST_ACK状态，等待客户端确认
4.client---(发送ACK=Y,Seq=X)--->server
客户端接收到服务端的关闭请求，再发送ACK标记的确认包，进入TIME_WAIT状态，等待服务端可能请求重传的ACK包。
服务端接收到ACK包后，关闭连接，进入CLOSED状态。客户端在等待固定时间(两个最大段生命周期)后，没有接收到服务的ACK包，认为服务器已关闭连接，客户端自己也关闭连接，进入CLOSED状态