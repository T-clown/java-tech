某一天早上，查看服务器的内存使用的时候，发现CPU超过了100%![img_17.png](img_17.png)
1. 找到最占用CPU的线程：top -Hp 20031 ![img_18.png](img_18.png)
2. 线程id转十六进制：printf “%x\n” 13326
3. 查看线程堆栈：jstack 20031 | grep 340e -C 20 --color ![img_19.png](img_19.png)
4. 这是一个Mybatis拦截器，同来做数据同步的![img_21.png](img_21.png)，![img_22.png](img_22.png)，![img_23.png](img_23.png)
5. 用arthas查看方法的返回值：watch com.hrtps.common.base.database.DTSMybatisInterceptor showSql '{returnObj}'  -x 3 ![img_20.png](img_20.png)
6. 发现所有select以外的SQL执行语句都经过了这个拦截器拼接完整SQL的逻辑，而如果执行语句很长，参数太多的话，在进行参数替换的时候非常耗费CPU
7. 并且这个拦截器被注册了2次![img_24.png](img_24.png)，![img_25.png](img_25.png)，也就是说每一个sql语句执行时这个拦截器都会执行2次![img_26.png](img_26.png)，![img_27.png](img_27.png)
8. 解决办法：1.让DTSMybatisInterceptor拦截器只注册一次![img_28.png](img_28.png)，2.拦截器里面判断提前，只有需要同步数据的表才执行组装完整sql的逻辑![img_29.png](img_29.png)
