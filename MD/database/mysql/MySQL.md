[高性能 Mysql 主从架构的复制原理及配置详解](https://mp.weixin.qq.com/s/uBzPyqPv_xuWoFOIsVlyig)
[总目录](http://w.itcodemonkey.com/article/21.html?v=1)
[Mysql 中主库跑太快，从库追不上怎么整？](https://mp.weixin.qq.com/s/2Y1H8zx3cygGQPKBRocpdw)
[线上出现死锁怎么解决？](https://mp.weixin.qq.com/s/LRTYSQiVuWeN2J-vhnVOeg)
[说说MySQL的锁有那些类型，以及加锁的原理？](https://mp.weixin.qq.com/s/rtSdXR9GxPn39bFmmZVrnA)
Mybatis 中$与#的区别
 1 #是将传入的值当做字符串的形式，select id,name,age from student where id =#{id},当前端把id值1，传入到后台的时候，就相当于 select id,name,age from student where id ='1'.
 2 $是将传入的数据直接显示生成sql语句，select id,name,age from student where id =${id},当前端把id值1，传入到后台的时候，就相当于 select id,name,age from student where id = 1.
 3 使用#可以很大程度上防止sql注入。(语句的拼接)
 4 但是如果使用在order by 中就需要使用 $.
 5 在大多数情况下还是经常使用#，但在不同情况下必须使用$. 
 6.如果表名是动态的，则必须使用$.   如：select * from ${tableName};

我觉得#与的区别最大在于：#{} 传入值时，sql解析时，参数是带引号的，而${}穿入值，sql解析时，参数是不带引号的。

一 : 理解mybatis中 $与#
    在mybatis中的$与#都是在sql中动态的传入参数。
    eg:select id,name,age from student where name=#{name}  这个name是动态的，可变的。当你传入什么样的值，就会根据你传入的值执行sql语句。

二:使用$与#
   #{}: 解析为一个 JDBC 预编译语句（prepared statement）的参数标记符，一个 #{ } 被解析为一个参数占位符 。
   ${}: 仅仅为一个纯碎的 string 替换，在动态 SQL 解析阶段将会进行变量替换。

 name-->cy
      select id,name,age from student where name=#{name}   -- name='cy'
      select id,name,age from student where name=${name}    -- name=cy