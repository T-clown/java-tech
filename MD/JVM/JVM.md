https://mp.weixin.qq.com/s/YfcRjHhbStm2T7Yheif7ug

https://mp.weixin.qq.com/s/RaD3SqZ9ytYTwCgz3HpEsg

1.程序计数器
    为了线程切换后能恢复到正确的执行位置，就要求每个线程都需要有个独立的程序计数器，各条线程之间的计数器互不影响，独立存储。所以程序计数器是线程私有的。
    另外，程序计数器是唯一一个在Java虚拟机规范中没有规定任何OutOfMemoryError情况的区域。
    
2.Java虚拟机栈
    线程私有的，生命周期与线程相同。虚拟机栈描述的是Java方法执行的内存模型：每个方法在执行的同时，都会创建一个栈帧，用于存储局部变量表（基本数据类型，对象的引用和returnAddress类型）、操作数栈、动态链接、方法出口等信息。
    局部变量表所需的内存空间在编译期间完成分配，当进入一个方法时，这个方法需要在栈帧中分配多大的局部变量空间是完全确定的，在方法运行期间不会改变局部变量表的大小。
    每个方法被调用直至执行完成的过程，就对应着一个栈帧从虚拟机栈中从入栈到出栈的过程。对于Java虚拟机栈，有两种以尝情况：
    1. 如果线程请求的栈深度大于虚拟机所允许的深度，将抛出StackOverFlowError异常。
    2. 如果虚拟机栈在动态扩展时，无法申请到足够的内存，就会抛出OutOfMemoryError异常。
    
 3. 本地方法栈
    本地方法栈和虚拟机栈所发挥的作用非常相似，它们之间的区别主要是：虚拟机栈是为虚拟机执行的Java方法（即字节码）服务的，而本地方法栈则为虚拟机使用到的Native方法服务。
    与虚拟机栈类似，本地方法栈也会抛出StackOverFlowError和OutOfMemoryError异常。
    
 4. Java堆
    Java堆是Java虚拟机所管理的内存中最大的一块。Java堆在主内存中，是被所有线程共享的一块内存区域，其随着JVM的创建而创建，堆内存的唯一目的是存放对象实例和数组。同时Java堆也是GC管理的主要区域。
    Java堆在物理上不需要连续的内存，只要逻辑上连续即可。如果堆中没有内存完成实例分配，并且也无法再扩展时，将会抛出OutOfMemoryError异常。
    
 5. 方法区
    方法区是所有线程共享的一块内存区域。用于存储已被虚拟机加载的类信息、常量、静态变量、即时编译器编译后的代码等数据。方法区也有一个别名叫Non-heap（非堆），用来与Java堆区分。对于HotSpot虚拟机来说，方法区又习惯成为“永久代（Permancent Generation）”，但这只是对于HotSpot虚拟机来说的，其他虚拟机的实现上并没有这个概念。相对而言，垃圾收集行为在这个区域比较少出现，但也并非不会来收集，这个区域的内存回收目标主要是针对常量池的回收和对类型的卸载上。
    根据Java 虚拟机规范的规定，当方法区无法满足内存分配需求时，将抛出OutOfMemoryError 异常。
    
 6. 运行时常量池
    运行时常量池属于方法区。Class文件中除了有类的版本、字段、方法、接口等描述信息外，还有一项信息是常量表，用于存放编译期生成的各种字面常量和符号引用，这部分内容将在类加载后进入方法区的运行时常量池中存放（JDK1.7开始，常量池已经被移到了堆内存中了）。
    也就是说，这部分内容，在编译时只是放入到了常量池信息中，到了加载时，才会放到运行时常量池中去。运行时常量池县归于Class文件常量池的另外一个重要特征是具备动态性，Java语言并不要求常量一定只有编译期才能产生，也就是并非预置入Class文件中常量池的内容才能进入方法区的运行时常量池，运行期间也可能将新的常量放入池中，这种特性被开发人员利用的比较多的是String类的intern()方法。
    当方法区无法满足内存分配需求时，将抛出OutOfMemoryError异常，常量池属于方法区，同样可能抛出OutOfMemoryError异常。

    