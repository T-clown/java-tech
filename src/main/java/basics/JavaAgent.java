package basics;

import java.lang.instrument.ClassFileTransformer;
import java.lang.instrument.Instrumentation;

/**
 * 字节码增强
 */
public class JavaAgent {

    public static void main(String[] args) {
        System.out.println("Java Agent");
    }

    public static void premain(String agentArgs, Instrumentation inst) {
        System.out.println("Java Agent is running");

        // 注册转换器
        inst.addTransformer(new MyClassTransformer());
    }

    static class MyClassTransformer implements ClassFileTransformer {
        @Override
        public byte[] transform(ClassLoader loader, String className, Class<?> classBeingRedefined,
                                java.security.ProtectionDomain protectionDomain, byte[] classfileBuffer) {


            return classfileBuffer;
        }
    }


}
