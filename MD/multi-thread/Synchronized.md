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
1. Lock是一个接口，而synchronized是Java中的关键字，synchronized是内置的语言实现；
2. synchronized在发生异常时，会自动释放线程占有的锁，因此不会导致死锁现象发生；
        而Lock在发生异常时，如果没有主动通过unLock()去释放锁，则很可能造成死锁现象，因此使用Lock时需要在finally块中释放锁；
3. Lock可以让等待锁的线程响应中断，而synchronized却不行，使用synchronized时，等待的线程会一直等待下去，不能够响应中断；
4. 通过Lock可以知道有没有成功获取锁，而synchronized却无法办到。
5. Lock可以提高多个线程进行读操作的效率
6. synchronized是非公平锁，ReentrantLock可以实现非公平锁和公平锁