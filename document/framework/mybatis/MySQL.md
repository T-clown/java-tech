[高性能 Mysql 主从架构的复制原理及配置详解](https://mp.weixin.qq.com/s/uBzPyqPv_xuWoFOIsVlyig)
[总目录](http://w.itcodemonkey.com/article/21.html?v=1)
[Mysql 中主库跑太快，从库追不上怎么整？](https://mp.weixin.qq.com/s/2Y1H8zx3cygGQPKBRocpdw)
[线上出现死锁怎么解决？](https://mp.weixin.qq.com/s/LRTYSQiVuWeN2J-vhnVOeg)
[说说MySQL的锁有那些类型，以及加锁的原理？](https://mp.weixin.qq.com/s/rtSdXR9GxPn39bFmmZVrnA)

### Mybatis 中$与#的区别

1. \#是将传入的值当做字符串的形式，select id,name,age from student where id =#{id},当前端把id值1，传入到后台的时候，就相当于 select id,name,age from
   student where id ='1'.
2. $是将传入的数据直接显示生成sql语句，select id,name,age from student where id =${id},当前端把id值1，传入到后台的时候，就相当于 select id,name,age from
   student where id = 1.
3. 使用#可以很大程度上防止sql注入。(语句的拼接)
4. 如果使用在order by 或者group by 的时候如果字段是动态的则需要使用 $
5. 如果表名是动态的，则必须使用$. 如：select * from ${tableName};

- \#与的区别最大在于：#{} 传入值时，sql解析时，参数是带引号的，而${}穿入值，sql解析时，参数是不带引号的。

1. \#{}: 解析为一个 JDBC 预编译语句（prepared statement）的参数标记符，一个 #{} 被解析为一个参数占位符 。
2. ${}: 仅仅为一个纯碎的 string 替换，在动态 SQL 解析阶段将会进行变量替换。
3. name-->cy
    - select id,name,age from student where name=#{name} -- name='cy'
    - select id,name,age from student where name=${name} -- name=cy