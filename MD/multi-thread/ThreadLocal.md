[面试官：小伙子，听说你看过 ThreadLocal 源码？(万字图文深度解析)](https://mp.weixin.qq.com/s/WKaUzChzj2PIcqiw05jcIA)
[Java并发之ThreadLocal](https://mp.weixin.qq.com/s/snVbWXMmxcdiVG_EZQH9Rw)
[很无语，面试官总是爱问ThreadLocal.](https://mp.weixin.qq.com/s/wB4DEYs2uT7xv1L7DRIxQA)
[再也不学ThreadLocal了，看这一篇就忘不掉了！（万字总结）](https://mp.weixin.qq.com/s/dd-3FANWBSoratBQZZbc7w)
[ThreadLocal面试六连问，你能Hold住吗？](https://mp.weixin.qq.com/s/Y24LQwukYwXueTS6NG2kKA)
[面试官：小伙子，听说你看过 ThreadLocal 源码？(万字图文深度解析)](https://mp.weixin.qq.com/s/WKaUzChzj2PIcqiw05jcIA)

###ThreadLocal数据结构
    ThreadLocal中有一个静态内部类ThreadLocalMap，每个线程Thread中维护了一个ThreadLocal.ThreadLocalMap实例用来存放数据
    ThreadLocalMap内部维护一个Entry[]数组(Entry是ThreadLocalMap的静态内部类)，Entry继承了WeakReference(Entry extends WeakReference<ThreadLocal<?>>)