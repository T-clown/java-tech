https://mp.weixin.qq.com/s/yfxq9fvfmi1jFddYUQ3rMQ
https://mp.weixin.qq.com/s?__biz=MzI4NDY5Mjc1Mg==&mid=2247489297&idx=2&sn=29a11986ac2a33f4ccd7c55cb986d43a&chksm=ebf6cf6edc81467897695730dd370e261b1d647e3bfdcf1ff4bf0f5b1685c0d30ce190631e14&mpshare=1&scene=24&srcid=&sharer_sharetime=1569380317275&sharer_shareid=c39308937f2815a44c41054898432d19&key=9e572a9d17d70f7cec456153e39979f86c58526ba7dda9c23608543ce6be85aaa31066111dfc7a72544262b91ba7e39eb3b7451055b7f22fc499eb2ec0f31e7815d0142e710a9d3b9ba66856e878267f&ascene=14&uin=MTIwNzg3MDIyOQ%3D%3D&devicetype=Windows+10+x64&version=62090070&lang=zh_CN&exportkey=ATWT9d7ZuCeSzi9lye9BOJU%3D&pass_ticket=nQBHpyPlpFvTbFFf8uNfyuoL%2B7g3pvPlTg%2FdJ4GAmsI%2FXVKGH7GJAwkRJL2lptnu


Docker安装ElasticSearch并运行
https://www.cnblogs.com/jianxuanbing/p/9410800.html
拉取ElasticSearch
docker pull docker.elastic.co/elasticsearch/elasticsearch:tag
创建用户定义的网络(用于连接到同一网络的其他服务)
docker network create somenetwork
运行ElasticSearch
docker run -d --name elasticsearch --net somenetwork -p 9200:9200 -p 9300:9300 -e "discovery.type=single-node" elasticsearch:tag

systemctl start docker		启动 docker
systemctl status docker		查看 docker 状态
systemctl stop docker		停止 docker
systemctl enable docker		开机自启
docker info 			    查看 docker 概要信息
docker --help			    查看 docker 帮助文档

docker images               查看镜像
docker search 镜像名称       搜索镜像

docker ps                   查看正在运行的容器
docker ps -a                查看所有容器
docker ps -l                查看最后一次运行的容器
docker ps -f status=exited  查看停止的容器

查看日志
docker logs [OPTIONS] CONTAINER
  Options:
        --details        显示更多的信息
    -f, --follow         跟踪实时日志
        --since string   显示自某个timestamp之后的日志，或相对时间，如42m（即42分钟）
        --tail string    从日志末尾显示多少行日志， 默认是all
    -t, --timestamps     显示时间戳
        --until string   显示自某个timestamp之前的日志，或相对时间，如42m（即42分钟）
查看指定时间后的日志，只显示最后100行：
$ docker logs -f -t --since="2018-02-08" --tail=100 CONTAINER_ID
查看最近30分钟的日志:
$ docker logs --since 30m CONTAINER_ID
查看某时间之后的日志：
$ docker logs -t --since="2018-02-08T13:23:37" CONTAINER_ID
查看某时间段日志：
$ docker logs -t --since="2018-02-08T13:23:37" --until "2018-02-09T12:23:37" CONTAINER_ID


docker run                  创建容器
可以在 run 后面加参数。其中：
-i   表示运行容器
-t   表示容器启动后进入其命令行
--name  为创建的容器命名
-v     表示目录映射关系(前者是宿主机目录，后者是映射到宿主机上的目录)
-d     在 run 后面加上 -d 参数，则会创建一个守护式容器在后台运行
-p     表示端口映射，前者是宿主机端口，后者是容器内的映射端口


交互式方式创建容器
docker run -it --name=容器名称 镜像名称：标签 /bin/bash    
守护式方式创建容器
docker run -di --name=容器名称 镜像名称：标签
登录守护式容器方式
docker exec -it 容器名称(或容器 ID) /bin/bash
启动容器
docker start 容器名称(或容器 ID)
停止容器
docker stop 容器名称(或容器 ID)

将文件拷贝到容器内
docker cp 需要拷贝的文件或目录  容器名称：容器目录
将文件从容器内拷贝出来
docker cp 容器名称：容器目录	需要拷贝的文件或目录

查看容器 IP：
docker inspect 容器名称(容器 ID )
也可以直接输出 IP 地址：
docker inspect --format='{{NetworkSetting。IPAddress}}' 容器名称(容器 ID)
删除容器
docker rm 容器名称(容器 ID)
删除镜像
docker rmi 镜像名称(镜像ID)或者docker image rm 镜像名称(镜像ID)




启动所有的容器
docker start $(docker ps -a | awk '{ print $1}' | tail -n +2)
docker start $(docker ps -aq)
关闭所有的容器
docker stop $(docker ps -a | awk '{ print $1}' | tail -n +2)
删除所有的容器
docker rm $(docker ps -a | awk '{ print $1}' | tail -n +2)
删除所有的镜像
docker rmi $(docker images | awk '{print $3}' |tail -n +2)

登录redis客户端
docker exec -it 容器id redis-cli
登录zookeeper客户端
docker exec -it 容器id zkCli.sh

查看所有容器名称
docker ps --format "{{.Names}}"

docker基础容器中bash: vi: command not found问题解决
apt-get update
apt-get install vim

Docker有三种网络模式，bridge、host、none，在你创建容器的时候，不指定--network默认是bridge。
bridge：为每一个容器分配IP，并将容器连接到一个docker0虚拟网桥，通过docker0网桥与宿主机通信。也就是说，此模式下，你不能用宿主机的IP+容器映射端口来进行Docker容器之间的通信。
host：容器不会虚拟自己的网卡，配置自己的IP，而是使用宿主机的IP和端口。这样一来，Docker容器之间的通信就可以用宿主机的IP+容器映射端口
none：无网络

https://www.imooc.com/article/details/id/20872