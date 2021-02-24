[](https://mp.weixin.qq.com/s/hGTxPHabIPZBrjg_Yww3ug)
1.Spring为什么默认把bean设计成单例
为了提高性能：
(1)减少了新生成实例的消耗
(1)减少jvm垃圾回收
(1)缓存快速获取bean
劣势：如果是有状态的话在并发环境下线程不安全
