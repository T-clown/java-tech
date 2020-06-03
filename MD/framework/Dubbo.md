负载均衡
1.RandomLoadBalance随机负载均衡(默认)
(1)找出所有Invoker对应的权重，用数组存起来
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
(3)当所有的权重都一样，则随机选一个Invoker
invokers.get(ThreadLocalRandom.current().nextInt(invokers.size()))

2.LeastActiveLoadBalance最少活跃负载均衡
(1)找出所有最少活跃的Invoker和其对应的权重，用数组存起来
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
(3)当所有的权重都一样，则随机选一个Invoker
invokers.get(ThreadLocalRandom.current().nextInt(invokers.size()))

3.RoundRobinLoadBalance轮询负载均衡

4.ConsistentHashLoadBalance一致性哈希负载均衡

5.ShortestResponseLoadBalance最短效应负载均衡
