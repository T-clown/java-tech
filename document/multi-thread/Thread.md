### 创建线程的方式

- 继承Thread类
  ```
    public static class ThreadOne extends Thread {
        @Override
        public void run() {
            System.out.println("创建线程方式一：继承Thread");
        }
    }
    //创建并启动第一个线程
    new ThreadOne().start();
  ```
- 实现Runnable接口
  ```
    public static class ThreadTwo implements Runnable {
        @Override
        public void run() {
            System.out.println("创建线程方式二：实现Runnable");
        }
    }
   //创建并启动第二个线程
   new Thread(new ThreadTwo(1,()->test()), "线程一").start();
  ```
    - 实现Callable接口，在call方法中实现具体的运算逻辑并返回计算结果
  ```
    public static class ThreadThree implements Callable<String> {
          @Override
          public String call() {
              System.out.println("创建线程方式三：实现Callable");
              return Thread.currentThread().getName();
          }
      }
    //创建并启动第三个线程
    FutureTask<String> threadName = new FutureTask<>(new ThreadThree());
   //实质是以Callable对象来创建并启动线程
    new Thread(threadName, "有返回值的线程").start();
  ```
- 基于线程池

### 线程的生命周期

新建（New）、就绪（Runnable）、运行（Running）、阻塞（Blocked）和死亡（Dead）

- 调用new方法新建一个线程，这时线程处于新建状态。
- 调用start方法启动一个线程，这时线程处于就绪状态。
- 处于就绪状态的线程等待线程获取CPU资源，在等待其获取 CPU资源后线程会执行run方法进入运行状态。
- 正在运行的线程在调用了yield方法或失去处理器资源时， 会再次进入就绪状态。
- 正在执行的线程在执行了sleep方法、I/O阻塞、等待同步 锁、等待通知、调用suspend方法等操作后，会挂起并进入阻塞状态， 进入Blocked池。
- 阻塞状态的线程由于出现sleep时间已到、I/O方法返回、获 得同步锁、收到通知、调用resume方法等情况，会再次进入就绪状 态，等待CPU时间片的轮询。该线程在获取CPU资源后，会再次进入运 行状态。
- 处于运行状态的线程，在调用run方法或call方法正常执行 完成、调用stop方法停止线程或者程序执行错误导致异常退出时，会 进入死亡状态。

### 线程的方法

- void interrupt()

  中断线程，线程A调用线程B的interrupt()方法，会给线程B设置中断标识，但是线程B会继续往下执行，若线程B被设置
  中断标识的时候调用了wait系列方法，sleep方法，join方法，则线程B会抛出InterruptedException异常而返回

- boolean isInterrupted()

  判断线程是否被中断，实例方法

- Thread.interrupted()

  静态方法，判断当前线程是否被中断，如果被中断，会清除中断标识
- void join()
- join(long millis)

  当前线程阻塞，并等待调用此方法的线程结束（millis最大等待时间）
- void wait()

  持有监视器锁的线程调用此方法：释放锁，并等待其他线程唤醒或者打断
- void wait(long timeoutMillis)

  持有监视器锁的线程调用此方法：释放锁，并等待唤醒或者打断，timeoutMillis时间过后自动醒来，wait方法一般被用于同步方法或同步代码块中
- void notify()

  持有监视器锁的线程随机唤醒一个线程
- void notifyAll()

  持有监视器锁的线程唤醒其他所有线程
- Thread.yield()

  当前线程让出（释放）CPU执行时间片，与其 他线程一起重新竞争CPU时间片
- Thread.sleep(long millis);

  线程睡眠一段时间，不会释放锁

### sleep方法与wait方法的区别

- sleep方法属于Thread类，wait方法则属于Object类。
- sleep方法暂停执行指定的时间，让出CPU 给其他线程，但其 监控状态依然保持，在指定的时间过后又会自动恢复运行状态。
- 在调用sleep方法的过程中，线程不会释放对象锁。
- 在调用wait方法时，线程会放弃对象锁，进入等待此对象的等 待锁池，只有针对此对象调用notify方法后，该线程才能进入对象锁 池准备获取对象锁，并进入运行状态

