[美团面试官：你说你们公司的Mybatis分页插件是你写的，给我说说它的设计原理\
](https://mp.weixin.qq.com/s/kB_WAhh-3wlItgxtLLGEvA)

1. 物理分页 物理分页就是数据库本身提供了分页方式，如MySQL的limit，Oracle的rownum ，好处是效率高，不好的地方就是不同数据库有不同的搞法。
2. 逻辑分页 逻辑分页利用游标分页，好处是所有数据库都统一，坏处就是效率低。

3. 常用ORM框架采用的分页技术
   (1)hibernate采用的是物理分页
   (2)MyBatis使用RowBounds实现的分页是逻辑分页,也就是先把数据记录全部查询出来,然在再根据offset和limit截断记录返回（数据量大的时候会造成内存溢出），不过可以用插件或其他方式能达到物理分页效果。

4. 常见的两种Mybatis的物理分页插件： Mybatis-Paginator 和Mybatis-PageHelper

