[Java多线程访问Synchronized同步方法的八种使用场景](https://mp.weixin.qq.com/s/y2Qi8CaF9qFcBGuYpdrV6Q)
[全网最细，17张图带你秒杀synchronized关键字](https://mp.weixin.qq.com/s/3Yw7zwtIujdZXK651CsNTQ)
[由浅入深逐步了解 Synchronized](https://mp.weixin.qq.com/s/dQmbSyYBe9eZxQqbBkLJSQ)
[Java之戳中痛点之 synchronized 深度解析](https://mp.weixin.qq.com/s/wkQUT8Dd0yuJF__bHHNZ0w)
[Java 中的锁原理、锁优化、CAS、AQS 详解](https://mp.weixin.qq.com/s/i0bDEZW6rs2g8zR7BjKLgQ)

### synchronized的使用方式

1. 修饰实例方法：锁定的是this对象，对象内置锁
2. 修饰静态方法：锁定的是Class对象，类锁
3. 修饰代码块：根据括号中的对象或者是类，获得相应的对象内置锁或者是类锁

### synchronized原理

    对于同步方法，JVM采用ACC_SYNCHRONIZED标记符来实现同步。
    对于同步代码块。JVM采用monitorenter、monitorexit两个指令来实现同步。每个monitorenter指令对应两个monitorexit指令，一个正常退出，一个异常退出

### Java 6之后，为了减少获得锁和释放锁所带来的性能消耗，引入了锁升级

    synchronized锁有四种状态，无锁、偏向锁、轻量级锁、重量级锁。这几个状态会随着竞争状态逐渐升级，锁可以升级但不能降级，但是偏向锁状态可以被重置为无锁状态

### synchronized 和 ReentrantLock的区别

- 使用方式的区别
- 功能特性的区别
- 实现原理的区别
    - synchronized的底层是采用Java对象头来存储锁信息的,对象头包含三部分,分别是Mark Word、Class Metadata Address、Array length。其中,Mark
      Word用来存储对象的hashCode及锁信息,Class Metadata Address用来存储对象类型的指针,而Array length则用来存储数组对象的长度。
    -
    AQS是队列同步器,是用来构建锁的基础框架,Lock实现类都是基于AQS实现的。AQS是基于模板方法模式进行设计的,所以锁的实现需要继承AQS并重写它指定的方法。AQS内部定义了一个FIFO的队列来实现线程的同步,同时还定义了同步状态来记录锁的信息

1. Lock是一个接口，而synchronized是Java中的关键字，synchronized是内置的语言实现
2. synchronized在发生异常时，会自动释放线程占有的锁，因此不会导致死锁现象发生； 而Lock在发生异常时，如果没有主动通过unLock()去释放锁，则很可能造成死锁现象，因此使用Lock时需要在finally块中释放锁；
3. Lock可以让等待锁的线程响应中断，而synchronized却不行，使用synchronized时，等待的线程会一直等待下去，不能够响应中断；
4. 通过Lock可以知道有没有成功获取锁，而synchronized却无法办到。
5. Lock可以提高多个线程进行读操作的效率
6. synchronized是非公平锁，ReentrantLock可以实现非公平锁和公平锁

早期的synchronized性能较差,不如Lock。后来synchronized在实现上引入了锁升级机制,性能上已经不输给Lock了。所以,synchronized和Lock的区别主要不在性能上,因为二者性能相差无几。 Java
6为了减少获取锁和释放锁带来的性能消耗,引入了偏向锁和轻量级锁。所以,从Java
6开始,锁一共被分为4种状态,级别由低到高依次是：无锁、偏向锁、轻量级锁、重量级锁。随着线程竞争情况的升级,锁的状态会从无锁状态逐步升级到重量级锁状态。锁可以升级却不能降级,这种只能升不能降的策略,是为了提高效率。
synchronized的早期设计并不包含锁升级机制,那个时候只有无锁和有锁之分。是为了提升性能才引入了偏向锁和轻量级锁,所以需要重点关注这两种状态的原理,以及它们的区别。
偏向锁,顾名思义就是锁偏向于某一个线程。当一个线程访问同步块并获取锁时,会在对象头和栈帧中的锁记录里存储锁偏向的线程ID,以后该线程再进入和退出同步块时就不需要做加锁和解锁操作了,只需要简单地测试一下Mark
Word里是否存储着自己的线程ID即可。 轻量级锁,就是加锁时JVM先在当前线程栈帧中创建用于存储锁记录的空间,并将Mark Word复制到锁记录中,官方称之为Displaced Mark Word。然后线程尝试以CAS方式将Mark
Word替换为指向锁记录的指针,如果成功则当前线程获得锁,如果失败则表示其他线程竞争锁,此时当前线程就会通过自旋来尝试获取锁

1. 无锁态（Unlocked）

- 初始状态下，对象没有被任何线程锁定，可以被任意线程访问和修改。

2. 偏向锁（Biased Locking）

- 当第一个线程访问一个同步块时，对象的锁会升级为偏向锁。
- 偏向锁的目的是为了减少无竞争情况下的同步开销。在无竞争的情况下，偏向锁会将线程ID记录在对象的标记字段中，表示该线程拥有对象的锁。
- 在后续的同步块访问中，如果发现当前线程仍然是持有锁的线程，则无需竞争，直接进入同步块执行。
- 偏向锁使用了一种延迟锁升级的机制，如果在对象上有多个线程竞争锁，偏向锁会自动升级为轻量级锁。

3. 轻量级锁（Lightweight Locking）

- 当多个线程竞争同一个锁时，偏向锁会升级为轻量级锁。
- 轻量级锁通过CAS（Compare and Swap）操作来实现，将对象头中的锁记录替换为指向线程栈中的锁记录（Lock Record）的指针。
- 如果CAS成功，表示线程获取了轻量级锁，并继续执行同步块。如果CAS失败，表示有其他线程竞争锁，进入锁膨胀的过程。

4. 锁膨胀（Lock Inflation）

- 当轻量级锁竞争激烈、CAS操作多次失败时，锁会膨胀为重量级锁。
- 锁膨胀的过程包括升级为重量级锁、阻塞线程等待锁的释放。
- 重量级锁会将对象的监视器（Monitor）从线程的锁记录中挂起，并在对象的内存结构中创建一个互斥量，用于线程间的同步。


