### 队列方法

1. add：增加一个元索(内部调用offer添加),如果队列已满，则抛出一个IllegalStateException异常
2. put：添加一个元素,如果队列满，则阻塞
3. offer： 添加一个元素并返回true,如果队列已满，则返回false
4. remove： 移除并返回队列头部的元素(内部调用poll),如果队列为空，则抛出一个NoSuchElementException异常
5. take ：移除并返回队列头部的元素,如果队列为空，则阻塞
6. poll： 移除并返问队列头部的元素,如果队列为空，则返回null
7. peek： 返回队列头部的元素,如果队列为空，则返回null
8. element ：返回队列头部的元素,如果队列为空，则抛出一个NoSuchElementException异常

### 1.ArrayBlockingQueue 阻塞队列

- 数据结构：Object[] 数组
- 是否有界：有界，初始化时需要指定队列大小
- 是否线程安全：线程安全，采用ReentrantLock锁(只有一把锁)，添加元素和获取元素都加锁
- 阻塞原理：使用ReentrantLock的ConditionObject实现阻塞等待
- 元素能否为空：不能

### 2.LinkedBlockingQueue 阻塞队列

- 数据结构：Node单向链表
- 是否有界：有界，初始化时指定容量，不指定则为Integer.MAX_VALUE
- 是否线程安全：线程安全，采用ReentrantLock锁(put添加和take获取各有一把锁)
- 阻塞原理：使用ReentrantLock的ConditionObject实现阻塞等待
- 元素能否为空：不能

### 3.ConcurrentLinkedQueue 非阻塞队列（并发队列）

- 数据结构：Node单向链表，FIFO
- 是否有界：无界
- 是否线程安全：线程安全，采用volatile+CAS保证线程安全
- 元素能否为空：不能

### 4.PriorityQueue 优先级队列(非阻塞)

- 数据结构：Object[]数组
- 是否有界：有界，默认容量11，最大容量Integer.MAX_VALUE - 8
- 是否线程安全：非线程安全
- 元素能否为空：不能，添加的元素要么实现Comparable接口，要么在构造函数传入Comparator比较器参数

### 5.PriorityBlockingQueue 优先级队列(阻塞队列)

- 数据结构：Object[]数组
- 是否有界：有界，默认容量11，最大容量Integer.MAX_VALUE - 8
- 是否线程安全：线程安全，采用ReentrantLock锁(只有一把锁)，任何操作都加锁
- 扩容方式：有用到CAS，oldCap + ((oldCap < 64) ? (oldCap + 2) : (oldCap >> 1));
- 阻塞原理：使用ReentrantLock的ConditionObject实现阻塞等待
- 元素能否为空：不能，添加的元素要么实现Comparable接口，要么在构造函数传入Comparator比较器参数

### 6.DelayQueue 延迟队列(阻塞)

- 数据结构：内部维护一个PriorityQueue队列
- 是否有界：同PriorityQueue
- 是否线程安全：线程安全，采用ReentrantLock，任何操作都加锁
- 元素能否为空：不能，元素必须继承Delayed并实现其getDelay方法
- 阻塞原理：内部维护一个ReentrantLock的ConditionObject变量，如果队列为空，则调用ConditionObject的await方法进行阻塞等待
- 延迟原理：调用ConditionObject的awaitNanos(long nanosTimeout)方法阻塞nanosTimeout时间后执行，nanosTimeout是元素的getDelay方法返回的延迟时间
- 内部变量有
    - ReentrantLock lock：对方法加锁操作，线程安全
    - PriorityQueue<E> q：优先级队列，存储元素
    - Thread leader
    - Condition available = lock.newCondition()：条件队列
- 操作流程
    - offer操作
        1. 加锁
        2. 元素入队
        3. 如果新增元素为头部元素，则leader线程变量置为null，并且唤醒一个条件队列中的沉睡线程 available.signal();
        4. 释放锁
    - take操作
        1. 加锁
        2. 获取队列头部元素q.peek()，如果元素为空，则睡眠available.await();
        3. 获取元素的延迟时间（纳秒），如果延迟时间小于等于0，则直接返回q.poll()
        4. 通过leader!=null判断是否已经存在leader（即是否有其他线程在等待），如果存在，则当前线程也进入等待状态available.await()
           如果不存在leader，当前线程成为leader，并通过awaitNanos()方法等待指定的延迟时间（元素的延迟时间）
        5. 当前线程被唤醒，则重新获取锁

### 7. SynchronousQueue 同步队列

- 不保存任何元素，它将等待插入的元素立即移交给等待接收该元素的线程。
- 当一个线程向SynchronousQueue插入元素时，如果没有线程正在等待接收该元素，那么该线程将被阻塞，直到有另一个线程准备好接收该元素
- 当一个线程从SynchronousQueue中取出元素，如果没有元素可用，则该线程也将被阻塞，直到有另一个线程准备好插入元素
- 使用场景：线程池中使用，避免任务的缓存和延迟

### 8.LinkedTransferQueue

### 8.ArrayDeque

### 9. ConcurrentLinkedDeque 并发双端队列

### 10 LinkedBlockingDeque

### ConcurrentSkipListMap

- 数据结构：跳表实现
- key是否有序：有序
- 操作的时间复杂度：O(log(n))

### ConcurrentSkipListSet

- 数据结构：基于ConcurrentSkipListMap实现
- key是否有序：有序
