https://mp.weixin.qq.com/s/yfxq9fvfmi1jFddYUQ3rMQ



Docker安装ElasticSearch并运行
https://www.cnblogs.com/jianxuanbing/p/9410800.html
拉取ElasticSearch
docker pull docker.elastic.co/elasticsearch/elasticsearch:tag
创建用户定义的网络(用于连接到同一网络的其他服务)
docker network create somenetwork
运行ElasticSearch
docker run -d --name elasticsearch --net somenetwork -p 9200:9200 -p 9300:9300 -e "discovery.type=single-node" elasticsearch:tag