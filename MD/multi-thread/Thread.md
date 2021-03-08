
void interrupt()
中断线程，线程A调用线程B的interrupt()方法，会给线程B设置中断标识，但是线程B会继续往下执行，若线程B被设置
中断标识的时候调用了wait系列方法，sleep方法，join方法，则线程B会抛出InterruptedException异常而返回

boolean isInterrupted()
判断线程是否被中断，实例方法

Thread.interrupted()
静态方法，判断当前线程是否被中断，如果被中断，会清除中断标识
