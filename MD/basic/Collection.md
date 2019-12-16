ArrayList
数据结构：Object[]数组
初始化：长度为0的空数组
数组初始化默认容量：10（第一次add操作的时候会从0扩容为10）
扩容时机：数组装满了
扩容机制：原数组的1.5倍


HashMap
数据结构：Node[](数组+链表)默认为null
数组初始化默认容量：16（第一次put的时候才初始化数组）
默认负载因子：0.75
扩容时机：key-value键值对数>数组容量*负载因子
扩容机制：原数组的2倍
put流程：
1.Node数组为空，则初始化，默认容量为16
2.找出key计算出的索引位置元素，为空则直接添加
3.遍历对应索引位置的链表找出key对应的Node，key存在则停止遍历，不存在则添加Node，添加Node后如果链表长度>8并且Node数组大小>64，则转成红黑树
4.key存在的节点不为空，则根据条件!onlyIfAbsent || oldValue == null替换value
5.key-value键值对数>数组容量*负载因子：扩容
int index=（(key == null) ? 0 : (h = key.hashCode()) ^ (h >>> 16)）&(数组大小-1)

HashSet
数据结构：基于HashMap实现，内部有一个HashMap变量，其值为HashMap的key

HashTable
数据结构：Entry[]数组
数组初始化默认容量：11（创建集合的时候就初始化数组）
默认负载因子：0.75

HashMap和Hashtable区别：
1.内部数组初始化时机不同，HashMap在第一次put时初始化，Hashtable创建的时候初始化
2.HashMap键和值都可以为null,Hashtable键值不能
3.HashMap是非线程安全的，Hashtable是线程安全的（所有方法都用synchronized修饰）
4.Hashtable计算hash是直接使用key的hashcode对table数组的长度直接进行取模
  HashMap计算hash对key的hashcode进行了二次hash，以获得更好的散列值，然后对table数组长度取模
5.扩容机制不同，HashMap是扩容为原来的2倍，Hashtable则扩容为原来的2倍+1
6.HashMap继承了AbstractMap，HashTable继承Dictionary抽象类，两者均实现Map接口

LinkedList
数据结构：双向链表

