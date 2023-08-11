Tomcat的conf目录下的server.xml文件中有如下配置：
 <Connector port="8080" protocol="HTTP/1.1" connectionTimeout="20000"
            redirectPort="8443" maxThreads="300" minSpareThreads="50"
            acceptCount="250 enableLookups="false" maxKeepAliveRequests="1"/>
   
 port : 在端口号8080处侦听来自客户browser的HTTP1.1请求.如果把8080改成80,则只要输入http://localhost/即可
 protocol:设定Http协议,默认值为HTTP/1.1
 connectionTimeout:定义建立客户连接超时的时间.如果为-1,表示不限制建立客户连接的时间
 
 redirectport : 当客户请求是https时，把该请求转发到端口8443去(如连接器不支持SSL请求，如收到SSL请求，Catalina容器将会自动重定向指定的端口号，让其进行处理)
 maxThreads：设置当前Tomcat的最大并发数，默认是150，根据硬件性能和CPU数设定
 minSpareThreads: 设置当前Tomcat初始化时创建的线程数，默认为25
 maxSpareThread:允许存在空闲线程的最大数目，默认值为50
 
 acceptCount : 当同时连接的人数达到maxThreads参数设置的值时，还可以接收排队的连接数量，超过这个连接的则直接返回拒绝连接。
               指定当任何能够使用的处理请求的线程数都被使用时，能够放到处理队列中的请求数，超过这个数的请求将不予处理。默认值为100。
               在实际应用中，如果想加大Tomcat的并发数 ，应该同时加大acceptCount和maxThreads的值。

 enableLookups:是否开启域名反查，一般设置为false来提高处理能力，它的取值还有true，一般很少使用。
               如果设为true,表示支持域名解析,可以把IP地址解析为主机名.WEB应用中调用request.getRemoteHost方法返回客户机主机名.默认值为true
               
maxKeepAliveRequests：服务器关闭之前，客户端发送的流水线最大数目。默认值为100
                      nginx动态的转给tomcat，nginx是不能keepalive的，而tomcat端默认开启了keepalive，会等待keepalive的timeout，默认不设置就是使用connectionTimeout。
                      所以必须设置tomcat的超时时间，并关闭tomcat的keepalive。否则会产生大量tomcat的socket timewait。
                      maxKeepAliveRequests=”1”就可以避免tomcat产生大量的TIME_WAIT连接，从而从一定程度上避免tomcat假死。
 
 allowTrace：是否允许HTTP的TRACE方法，默认为false
 emptySessionPath：如果设置为true，用户的所有路径都将设置为/，默认为false。
 maxPostSize：指定POST方式请求的最大量，没有指定默认为2097152。
 proxyName：如这个连接器正在一个代理配置中被使用，指定这个属性，在request.getServerName()时返回
 scheme：设置协议的名字，在request.getScheme()时返回，SSL连接器设为”https”，默认为”http”
 secure：在SSL连接器可将其设置为true，默认为false
 URIEncoding：用于解码URL的字符编码，没有指定默认值为ISO-8859-1
 useBodyEncodingForURI：主要用于Tomcat4.1.x中，指示是否使用在contentType中指定的编码来取代URIEncoding，用于解码URI查询参数，默认为false
 xpoweredBy：为true时，Tomcat使用规范建议的报头表明支持Servlet的规范版本，默认为false
 bufferSize：设由连接器创建输入流缓冲区的大小，以字节为单位。默认情况下，缓存区大的大小为2048字节
 compressableMimeType：MIME的列表，默认以逗号分隔。默认值是text/html，text/xml，text/plain
 compression：指定是否对响应的数据进行压缩。off：表示禁止压缩、on：表示允许压缩（文本将被压缩）、force：表示所有情况下都进行压缩，默认值为off
 disableUploadTimeOut：允许Servlet容器，正在执行使用一个较长的连接超时值，以使Servlet有较长的时间来完成它的执行，默认值为false
 maxHttpHeaderSize：HTTP请求和响应头的最大量，以字节为单位，默认值为4096字节
 socketBuffer：设Socket输出缓冲区的大小（以字节为单位），-1表示禁止缓冲，默认值为9000字节
 toNoDelay：为true时，可以提高性能。默认值为true
 threadPriority：设JVM中请求处理线程优先级。默认值为NORMAL-PRIORITY
 
 
<Service name="Catalina">
     //Connector里面的port定义访问端口，多端口定义多个Connector标签
     <Connector connectionTimeout="20000" port="8080" protocol="HTTP/1.1" redirectPort="8443"/>
     <Connector port="8009" protocol="AJP/1.3" redirectPort="8443"/>
     <Engine defaultHost="localhost" name="Catalina">
       <Realm className="org.apache.catalina.realm.LockOutRealm">
         <Realm className="org.apache.catalina.realm.UserDatabaseRealm" resourceName="UserDatabase"/>
       </Realm>
       //name定义访问域名，多域名定义多个Host标签
       <Host appBase="webapps" autoDeploy="true" name="localhost" unpackWARs="true">
         <Valve className="org.apache.catalina.valves.AccessLogValve" directory="logs" pattern="%h %l %u %t &quot;%r&quot; %s %b" prefix="localhost_access_log." suffix=".txt"/>
       </Host>
     </Engine>
</Service>

1.多项目使用同一域名(多端口访问)
配置多个Connector设置多个端口，并且Host标签里面加上：
<!--注意：docBase　要使用绝对路径 -->
<Context docBase="/usr/local/tomcat/webapps/testHostA" path="/testA" reloadable="true" autodeploy="true" />
<Context docBase="/usr/local/tomcat/webapps/testHostB" path="/testB" reloadable="true"  autodeploy="true" />
 访问路径：localhost:8080/testA
          localhost:8080/testB
          localhost:8009/testA
          localhost:8009/testB
2.不同项目不同域名（统一端口）
配置多个Host标签，设置域名，并在Host标签里面配置Context标签指定该域名访问项目
3.多域名多端口访问
配置多个service，每个service对应自己的端口配置
