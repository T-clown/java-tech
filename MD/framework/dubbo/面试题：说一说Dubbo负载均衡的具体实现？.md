[面试题：说一说Dubbo负载均衡的具体实现？](https://mp.weixin.qq.com/s/7y_VVrRZXQUepNEWR5INlA)
如果当前服务提供者启动时间小于预热时间，则会重新计算权重值，对服务进行降权处理
###1.随机负载均衡(默认)RandomLoadBalance   根据权重设置随机的概率
        // invoker的数量
        int length = invokers.size();
        // 每个 invoker 是否都有同样草权重
        boolean sameWeight = true;
        int[] weights = new int[length];
        // 所有权重之和
        int totalWeight = 0;
        for (int i = 0; i < length; i++) {
            int weight = getWeight(invokers.get(i), invocation);
            // 累加
            totalWeight += weight;
            // 保存权重，后面取invoker用到
            weights[i] = totalWeight;
            if (sameWeight && totalWeight != weight * (i + 1)) {
                sameWeight = false;
            }
        }
        if (totalWeight > 0 && !sameWeight) {
            // 如果所有invoker的权重都不是一样的，且至少一个invoker权重大于0，则基于totalWeight取一个随机值.
            int offset = ThreadLocalRandom.current().nextInt(totalWeight);
            // 返回基于随机值的 invoker 
            for (int i = 0; i < length; i++) {
                if (offset < weights[i]) {
                    return invokers.get(i);
                }
            }
        }
        // 如果所有invoker的权重都一样或者 totalWeight=0,随机返回一个invoker.
        return invokers.get(ThreadLocalRandom.current().nextInt(length));

###2.最少活跃负载均衡LeastActiveLoadBalance  
    活跃数是一个 Invoker 正在处理的请求的数量，当 Invoker 开始处理请求时，会将活跃数加 1，完成请求处理后，将相应 Invoker 的活跃数减 1
    (1)找出所有最少活跃次数(调用次数)的Invoker和其对应的权重，用数组存起来
    (2)如果所有Invoker权重之和大于0并且Invoker的权重不相等，则根据权重的随机值选取一个Invoker
    if (totalWeight > 0 && !sameWeight) {
                int offset = ThreadLocalRandom.current().nextInt(totalWeight);
                for (int i = 0; i < length; i++) {
                    offset -= weights[i];
                    if (offset < 0) {
                        return invokers.get(i);
                    }
                }
            }
    (3)当所有的权重都一样，则从最少活跃的invoker中随机选一个Invoker
     invokers.get(leastIndexes[ThreadLocalRandom.current().nextInt(leastCount)]);

###3.轮询负载均衡RoundRobinLoadBalance 加权轮训算法，根据权重设置轮训比例
    每个 Invoker 都有一个 current 值，初始值为自身权重。在每个 Invoker 中 current=current+weight。遍历完 Invoker 后，current 最大的那个 Invoker 就是本次选中的 Invoker。
    选中 Invoker 后，将本次 current 值计算 current=current-totalWeight
###4.一致性哈希负载均衡ConsistentHashLoadBalance  Hash 一致性算法，相同请求参数分配到相同提供者
    一致性 Hash 负载均衡策略（ConsistentHashLoadBalance）是让参数相同的请求分配到同一机器上。把每个服务节点分布在一个环上，请求也分布在环形中。以请求在环上的位置，顺时针寻找换上第一个服务节点。
    同时，为避免请求散列不均匀，dubbo 中会将每个 Invoker 再虚拟多个节点出来，使得请求调用更加均匀
###5.最短响应负载均衡ShortestResponseLoadBalance
    (1)遍历invokers，从invoker中获取当前invoker的平均请求响应时间succeededAverageElapsed和当前正在调用的请求数active，两者乘积
        得到该invoker的预估响应时间succeededAverageElapsed，取最小的响应时间
    (2)若最短响应时间invoker只有一个，直接返回
    (3)若有多个最短响应时间的invoker：每个invoker权重不一样且权重之和大于0，根据权重随机选择一个invoker返回，否则随机选择