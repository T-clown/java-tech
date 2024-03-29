### HTTP 1.0

- HTTP协议的基本特点是“⼀来⼀回”。什么意思呢？客户端发起⼀个 TCP连接，在连接上⾯发⼀个HTTP Request到服务器，服务器返回⼀个 HTTP Response，然后连接关闭。每来⼀个请求，就要开⼀个连接，请求
  完了，连接关闭。
- 缺点：
    1. 性能问题。连接的建⽴、关闭都是耗时操作 - 解决办法：
       (1)连接复用：HTTP 1.0设计了⼀个Keep-Alive机 制来实现TCP连接的复⽤。具体来说，就是客户端在HTTP请求的头部加上 ⼀个字段Connection：Keep-Alive。服务器收到带有这样字段的请求，在处
       39 理完请求之后不会关闭连接，同时在HTTP的Response⾥⾯也会加上该字 段，然后等待客户端在该连接上发送下⼀个请求
       (2)连接关闭：服务器会有 ⼀个Keep-Alive timeout参数，过⼀段时间之后，如果该连接上没有新的请 求进来，则连接就会关闭
       (3)数据完整性：在HTTP Response的头部，返回了⼀个Content-Length：xxx的 字段，这个字段可以告诉客户端HTTP Response的Body共有多少个字节，
       客户端接收到这么多个字节之后，就知道响应成功接收完毕
    2. 服务器推送问题。不⽀持“⼀来多回”，服务器⽆法在客户端没 有请求的情况下主动向客户端推送消息。但很多的应⽤恰恰都需要服务器 在某些事件完成后主动通知客户端。

### HTTP 1.1

1. 连接复⽤变成了⼀个默认属性
2. 引⽤了Chunk机制（Http Streaming）： HTTP1.0的Content-Length缺点：服务器返回的数据如果是动态语言生成的内容，计算长度会很困难，比较耗时，为此，在HTTP 1.1中引⽤了Chunk机制（Http
   Streaming）。具体来 说，就是在响应的头部加上Transfer-Encoding：chunked属性，其⽬的是告 诉客户端，响应的Body是分成了⼀块块的，块与块之间有间隔符，所有块 的结尾也有个特殊标记。这样，即使没有
   Content-Length 字段，也能⽅便 客户端判断出响应的末尾
3. 引⼊了Pipeline机制（默认关闭）： 在同⼀个TCP连接上⾯，可以在 ⼀个请求发出去之后、响应没有回来之前，就可以发送下⼀个、再下⼀个 请求，这样就提⾼了在同⼀个TCP连接上⾯的处理请求的效率 缺点：队头阻塞
4. 断点续传：断点下载

### HTTP/2

1. ⼆进制分帧：解决HTTP1.0的队头阻塞问题（没有完全解决，降低了阻塞的概率），的⼆进制分帧，是指在把这个 字符格式的报⽂给TCP之前转换成⼆进制，并且分成多个帧（多个数据 块）来发送
2. 头部压缩
3. 服务器推送
4. 流重置

### SSL/TLS

- SSL（Secure Sockets Layer）的中⽂名称为安全套接层
- TLS（Transport Layer Security）的中⽂ 名称为传输层安全协议

1. 对称加密 客户端和服务器知道同⼀个 密钥，客户端给服务器发消息时，客户端⽤此密钥加密，服务器⽤此密钥 解密；反过来，服务器给客户端发消息时，是相反的过程
2. 双向⾮对称加密 客户端为⾃⼰准备⼀对公私钥（PubA，PriA），服务 器为⾃⼰准备⼀对公私钥（PubB，PriB）。公私钥有个关键特性：公钥PubA是通过私钥PriA 计算出来的，但反过来不⾏，不能根据PubA推算出 PriA。
   客户端、服务器把⾃⼰的公钥公开出去，⾃⼰保留私钥。 当客户端给服务器发送信息时，就⽤⾃⼰的私钥PriA签名，再⽤服务 器的公钥PubB加密。 服务器收到信息后，先⽤⾃⼰的私钥PriB解密，再⽤客户端的公钥验
   签（证明信息是客户端发出的）。

- 签名和验签：私钥签名，公钥验签，⽬的是防篡改。
- 加密和解密：公钥加密，私钥解密。⽬的是防⽌信息被第三⽅ 拦截和偷听。

3. 单向⾮对称加密 ，客户端没有公钥和私钥对，只有服务器有。服 务器把公钥给到客户端，客户端给服务器发送消息时，⽤公钥加密，然后服务器⽤私钥解密。反过来，服务器给客户端发送的消息，采⽤明⽂发 送。

### HTTPS=HTTP+SSL/TLS

1. TCP连接的建⽴。 三次握手（3次刚好能保证发送方和接收方的能力做了一次确认）
2. SSL/TLS四次握⼿协商出对称加密的密钥。
   (1)第⼀个来回，是公钥的传输与验证过程（通过数字证书）；
   (2)第⼆个来回基于第⼀个来回得 到的公钥，协商出对称加密的密钥
3. 基于密钥，在TCP连接上对所有的HTTP Request/Response进⾏ 加密和解密。 HTTP/2主要是解决性能问 题，HTTPS主要解决安全问题。从理论上讲，两者没有必然的关系，

