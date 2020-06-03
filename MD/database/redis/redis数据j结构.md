https://mp.weixin.qq.com/s?__biz=MzA4NjgxMjQ5Mg==&mid=2665763950&idx=2&sn=d73e689d65ba2d755d8f5e5c8406f291&chksm=84d2064db3a58f5b05184b0dab859514ef771b810748b2b6064a3b34681ce89430f645efeccc&mpshare=1&scene=24&srcid=&sharer_sharetime=1589162326685&sharer_shareid=c39308937f2815a44c41054898432d19&key=8789ad4c0cbeb143b8fed987903ae0b3fd51df3c241bd6b35bad80b3fad446000fb06b7ce3472c8826752cac0a190aa010d44cb7299aca8cb84d59e67e1ac14d8319172d7ebb95a77bc0704d7c926e32&ascene=14&uin=MTIwNzg3MDIyOQ%3D%3D&devicetype=Windows+10+x64&version=62090070&lang=zh_CN&exportkey=ARSwXhe60kMe6Dg2yy8MCVQ%3D&pass_ticket=%2BUPT7tryhpGcvLtueQqS9DncJWiw%2B7vMueLeyKKbuDBllUr4jQt4RjLHDlveutCz
https://mp.weixin.qq.com/s?__biz=MzA4MTk3MjI0Mw==&mid=2247487882&idx=1&sn=24c8b5bf9dc3988b5d234d642caaef17&chksm=9f8d8cf6a8fa05e0970db20c62d4a447510f13dc9af18af59520f3b43d36657bff9a682ed781&mpshare=1&scene=24&srcid=&sharer_sharetime=1589163467891&sharer_shareid=c39308937f2815a44c41054898432d19&key=8789ad4c0cbeb143070bda5c990217005a6443780d06e4193f48ffd4dcffd24bad66544ff0bb3a2e9b322e7d702c3aac7deb513be04617765b2d5a083af0542c5e5014c7515cc09d0c5c926654fedb35&ascene=14&uin=MTIwNzg3MDIyOQ%3D%3D&devicetype=Windows+10+x64&version=62090070&lang=zh_CN&exportkey=AVIexZuPgt3WwEGi3sbjnxc%3D&pass_ticket=%2BUPT7tryhpGcvLtueQqS9DncJWiw%2B7vMueLeyKKbuDBllUr4jQt4RjLHDlveutCz

1.String
数据结构：支持自动动态扩容的字节数组
(1)当value是整数的时候，为int编码(长度<20，否重为embstr编码)
(2)当字符串长度小于44时，为embstr编码，否则为raw编码
embstr和raw两种编码比较：
    a.embstr编码的字符串对象的所有数据都保存在一块连续的内存里面，所以这种编码的字符串对象比起raw编码的字符串对象能更好地利用缓存带来的优势
    b.释放embstr编码的字符串对象只需要调用一次内存释放函数，而释放raw编码对象的字符串对象需要调用两次内存释放函数
2.list
数据结构：双向链表(对应java的LinkedList)
编码：quicklist
3.hash
数据结构：
编码：ziplist

4.set
数据结构：
编码：hashtable

5.zset
数据结构：跳表
编码：ziplist

6.bitmap
数据结构：

7.GeoHash
数据结构：

8.HyperLogLog
数据结构：
编码：raw

9.Streams(5.0版本增加的数据结构)
数据结构：Radix Tree(基数树)，事实上就几乎相同于传统的二叉树
