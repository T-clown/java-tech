package network.rpc.registry;

import java.io.File;
import java.lang.reflect.Method;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;


import com.alibaba.fastjson.JSON;
import io.netty.channel.ChannelHandlerAdapter;
import io.netty.channel.ChannelHandlerContext;
import network.rpc.protocol.InvokerProtocol;
import network.tomcat.TomcatServer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class RegistryHandler extends ChannelHandlerAdapter {
    private static final Logger logger = LoggerFactory.getLogger(RegistryHandler.class);
    /**
     * 保存所有可用的服务
     */
    public static ConcurrentHashMap<String, Object> registryMap = new ConcurrentHashMap<String, Object>();

    /**
     * 保存所有相关的服务类
     */
    private List<String> classNames = new ArrayList<>();

    public RegistryHandler() {
        //完成递归扫描
        scannerClass("network.rpc.provider");
        doRegister();
    }


    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        Object result = new Object();
        InvokerProtocol request = (InvokerProtocol) msg;

        //当客户端建立连接时，需要从自定义协议中获取信息，拿到具体的服务和实参
        //使用反射调用
        if (registryMap.containsKey(request.getClassName())) {
            logger.info("接收的参数：{}", JSON.toJSONString(request));
            Object clazz = registryMap.get(request.getClassName());
            logger.info("接口名称：{}", clazz.getClass().getName());
            Method method = clazz.getClass().getMethod(request.getMethodName(), request.getParams());
            logger.info("方法名称：{}", method.getName());
            result = method.invoke(clazz, request.getValues());
        }
        ctx.write(result);
        ctx.flush();
        ctx.close();
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        cause.printStackTrace();
        ctx.close();
    }


    /**
     * 递归扫描
     */
    private void scannerClass(String packageName) {
        URL url = this.getClass().getClassLoader().getResource(packageName.replaceAll("\\.", "/"));
        File dir = new File(url.getFile());
        for (File file : dir.listFiles()) {
            //如果是一个文件夹，继续递归
            if (file.isDirectory()) {
                scannerClass(packageName + "." + file.getName());
            } else {
                classNames.add(packageName + "." + file.getName().replace(".class", "").trim());
            }
        }
    }

    /**
     * 完成注册
     */
    private void doRegister() {
        if (classNames.size() == 0) {
            return;
        }
        for (String className : classNames) {
            try {
                Class<?> clazz = Class.forName(className);
                Class<?> i = clazz.getInterfaces()[0];
                registryMap.put(i.getName(), clazz.getConstructor().newInstance());
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

}
