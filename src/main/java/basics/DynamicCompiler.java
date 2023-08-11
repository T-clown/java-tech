package basics;

import javassist.*;
import javassist.bytecode.LocalVariableAttribute;
import javassist.bytecode.MethodInfo;
import org.apache.commons.io.FileUtils;

import javax.tools.JavaCompiler;
import javax.tools.JavaFileObject;
import javax.tools.StandardJavaFileManager;
import javax.tools.ToolProvider;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.net.URLClassLoader;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.TimeUnit;

/**
 * 动态编译
 */
public class DynamicCompiler {

    public static void main(String[] args) throws Exception {
        //javassistCompiler();
        //jdkCompiler();
        //jdkCompiler2();
//        String className = "basics.DynamicCompiler";
//        String methodName = "test";
//        trackMethodExecution2(DynamicCompiler.class, methodName);

        // 示例：在目标类的方法上添加统计信息
        Class<?> test = addMethodStats(DynamicCompiler.class, "test");
        DynamicCompiler target = (DynamicCompiler) test.getConstructor().newInstance();
        // 调用目标类的方法
       // DynamicCompiler target = new DynamicCompiler();
        target.test("1");
    }


    public static Class<?> addMethodStats(Class<?> targetClass, String methodName) throws Exception {
        // 获取类池
        ClassPool classPool = ClassPool.getDefault();
        // 获取目标类
        CtClass ctClass = classPool.get(targetClass.getName());

        // 获取目标方法
        CtMethod ctMethod = ctClass.getDeclaredMethod(methodName);
        ctMethod.addLocalVariable("startTime",CtClass.longType);
        ctMethod.addLocalVariable("endTime",CtClass.longType);
        // 在方法体前后插入代码
        ctMethod.insertBefore("{ long startTime = System.nanoTime(); }");
        ctMethod.insertAfter("{ long endTime = System.nanoTime(); " +
                "System.out.println(\"Method Execution Time: \" + (endTime - startTime) + \" nanoseconds\"); }");

        // 重新加载类
        //targetClass.getClassLoader().loadClass(targetClass.getName());
        Loader classLoader = new Loader(classPool);
        // 生成新的字节码
        return classLoader.loadClass(targetClass.getName());
    }

    public  int test(String param) {
        try {
            TimeUnit.SECONDS.sleep(3);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("参数："+param);
        return Integer.parseInt(param);
    }
    public static void trackMethodExecution(Class<?> clazz, String methodName) {
        try {
            // 获取要修改的类
            ClassPool classPool = ClassPool.getDefault();
            CtClass targetClass = classPool.get(clazz.getName());

            // 获取要修改的方法
            CtMethod targetMethod = targetClass.getDeclaredMethod(methodName);
            targetMethod.addLocalVariable("startTime",CtClass.longType);
            targetMethod.addLocalVariable("endTime",CtClass.longType);
            // 获取方法的参数名
            CtClass[] parameterTypes = targetMethod.getParameterTypes();
            MethodInfo methodInfo = targetMethod.getMethodInfo();
            LocalVariableAttribute variableAttribute = (LocalVariableAttribute) methodInfo.getCodeAttribute().getAttribute(LocalVariableAttribute.tag);
            String[] parameterNames = new String[parameterTypes.length];
            int pos = Modifier.isStatic(targetMethod.getModifiers()) ? 0 : 1;
            for (int i = 0; i < parameterTypes.length; i++) {
                parameterNames[i] = variableAttribute.variableName(i + pos);
            }

            // 在方法的前后插入代码
            StringBuilder codeBuilder = new StringBuilder();
            codeBuilder.append("{ long startTime = System.nanoTime(); ");
            for (int i = 0; i < parameterNames.length; i++) {
                codeBuilder
                        .append("System.out.println(\"Parameter ")
                        .append(parameterNames[i])
                        .append(": \" + $")
                        .append(i + pos)
                        .append("); ");
            }
            codeBuilder.append("}");
            targetMethod.insertBefore(codeBuilder.toString());
            targetMethod.insertAfter("{ long endTime = System.nanoTime(); " +
                    "System.out.println(\"Execution time: \" + (endTime - startTime) + \" nanoseconds\"); }");

            // 创建新的类
            //Class<?> modifiedClass = targetClass.toClass();
            //同个Class不能在同个ClassLoader中加载两次，所以需要使用javassist提供的ClassLoader
            Loader classLoader = new Loader(classPool);
            // 生成新的字节码
            Class<?> modifiedClass = classLoader.loadClass(clazz.getName());

            // 实例化新的类
            Object instance = modifiedClass.getDeclaredConstructor().newInstance();

            // 获取方法
            //Method method = modifiedClass.getDeclaredMethod(methodName, parameterTypes);
            Method[] methods = modifiedClass.getDeclaredMethods();
            Method method = null;
            for (Method m : methods) {
                if (m.getName().equals(methodName) && m.getParameterCount() == parameterTypes.length) {
                    method = m;
                    break;
                }
            }

            // 构造方法参数
            Object[] args = new Object[parameterTypes.length];

            // 调用方法
            Object result = method.invoke(instance, args);

            // 输出结果
            System.out.println("Method result: " + result);
        } catch (NotFoundException | CannotCompileException | ReflectiveOperationException e) {
            e.printStackTrace();
        }
    }
    public static void trackMethodExecution2(Class<?> clazz, String methodName) {
        try {
            // 获取要修改的类
            ClassPool classPool = ClassPool.getDefault();
            CtClass targetClass = classPool.get(clazz.getName());

            // 获取要修改的方法
            CtMethod targetMethod = targetClass.getDeclaredMethod(methodName);
            targetMethod.addLocalVariable("startTime",CtClass.longType);
            targetMethod.addLocalVariable("endTime",CtClass.longType);
            // 在方法的前后插入代码
            targetMethod.insertBefore("{  startTime = System.nanoTime(); }");
            targetMethod.insertAfter("{ long endTime = System.nanoTime(); " +
                    "System.out.println(\"Execution time: \" + (endTime - startTime) + \" nanoseconds\"); }");

            //同个Class不能在同个ClassLoader中加载两次，所以需要使用javassist提供的ClassLoader
            Loader classLoader = new Loader(classPool);
            // 生成新的字节码
            Class<?> modifiedClass = classLoader.loadClass(clazz.getName());
            // 创建新的类
            //Class<?> modifiedClass = targetClass.toClass();

            // 实例化新的类
            Object instance = modifiedClass.getDeclaredConstructor().newInstance();

            // 获取方法
            Method[] declaredMethods = modifiedClass.getDeclaredMethods();
            Method method = modifiedClass.getDeclaredMethod(methodName);

            // 调用方法
            Object result = method.invoke(instance);

            // 输出结果
            System.out.println("Method result: " + result);
        } catch (NotFoundException | CannotCompileException | ReflectiveOperationException e) {
            e.printStackTrace();
        }
    }

    public static void javassistCompiler() {
        try {
            // 创建ClassPool对象，表示一个类池
            ClassPool classPool = ClassPool.getDefault();

            // 创建一个新的空类
            CtClass dynamicClass = classPool.makeClass("DynamicClass");

            // 添加一个字段
            CtField field = CtField.make("private int dynamicField;", dynamicClass);
            dynamicClass.addField(field);

            // 添加一个方法
            CtMethod method = CtMethod.make("public void dynamicMethod() { System.out.println(\"Dynamic method\"); }", dynamicClass);
            dynamicClass.addMethod(method);

            // 生成并加载类
            // Class<?> generatedClass = dynamicClass.toClass();
            Class<?> generatedClass = dynamicClass.toClass(Thread.currentThread().getContextClassLoader(), null);
            Object instance = generatedClass.getConstructor().newInstance();

            // 调用动态生成的方法
            generatedClass.getMethod("dynamicMethod").invoke(instance);

            // 修改字段的值并打印
            Field dynamicField = generatedClass.getDeclaredField("dynamicField");
            dynamicField.setAccessible(true);
            dynamicField.set(instance, 42);

            Field dynamicField2 = generatedClass.getDeclaredField("dynamicField");
            dynamicField2.setAccessible(true);
            System.out.println("Dynamic field value: " + dynamicField2.get(instance));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    public static void jdkCompiler() {
        // 定义源代码
        String sourceCode = "public class DynamicClass { public void dynamicMethod() { System.out.println(\"Dynamic method\"); } }";

        // 创建编译器
        JavaCompiler compiler = ToolProvider.getSystemJavaCompiler();
        StandardJavaFileManager fileManager = compiler.getStandardFileManager(null, null, null);

        // 创建一个临时Java源文件
        File sourceFile = new File("DynamicClass.java");
        try {
            FileUtils.writeStringToFile(sourceFile, sourceCode, "UTF-8");

            // 获取要编译的Java文件集合
            Iterable<? extends JavaFileObject> compilationUnits = fileManager.getJavaFileObjectsFromFiles(Arrays.asList(sourceFile));

            // 编译选项
            Iterable<String> compileOptions = Arrays.asList("-d", "./");

            // 创建编译任务
            JavaCompiler.CompilationTask task = compiler.getTask(null, fileManager, null, compileOptions, null, compilationUnits);

            // 执行编译任务
            boolean success = task.call();

            if (success) {
                System.out.println("Compilation succeeded.");

                // 加载并实例化动态生成的类
                Class<?> dynamicClass = Class.forName("DynamicClass");
                Object instance = dynamicClass.getConstructor().newInstance();

                // 调用动态生成的方法
                dynamicClass.getMethod("dynamicMethod").invoke(instance);
            } else {
                System.out.println("Compilation failed.");
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            // 删除临时源文件
            FileUtils.deleteQuietly(sourceFile);
        }
    }


    public static void jdkCompiler2() {
        // 定义源代码
        String sourceCode = "public class HelloWorld {\n" +
                "    public static void test() {\n" +
                "        System.out.println(\"Hello, World!\");\n" +
                "    }\n" +
                "}";

        // 获取Java编译器
        JavaCompiler compiler = ToolProvider.getSystemJavaCompiler();
        // 获取Java文件管理器
        StandardJavaFileManager fileManager = compiler.getStandardFileManager(null, null, null);

        try {
            // 创建一个临时Java文件
            File tempFile = File.createTempFile("HelloWorld", ".java");
            // 将源代码写入临时Java文件
            FileWriter writer = new FileWriter(tempFile);
            writer.write(sourceCode);
            writer.close();

            // 获取要编译的Java文件列表
            Iterable<? extends JavaFileObject> compilationUnits = fileManager.getJavaFileObjectsFromFiles(List.of(tempFile));

            // 设置编译参数，这里指定生成的class文件输出路径
            Iterable<String> options = List.of("-d", "output");

            // 创建编译任务
            JavaCompiler.CompilationTask task = compiler.getTask(null, fileManager, null, options, null, compilationUnits);

            // 执行编译任务
            boolean success = task.call();

            if (success) {
                System.out.println("编译成功！");

                // 加载并运行编译生成的类
                Class<?> compiledClass = Class.forName("HelloWorld");
                Method declaredMethod = compiledClass.getDeclaredMethod("test");
                declaredMethod.invoke(compiledClass.getConstructor().newInstance());
            } else {
                System.out.println("编译失败！");
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            // 关闭文件管理器
            try {
                fileManager.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }


}
