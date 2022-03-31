二.	安装docker
参考：https://docs.docker.com/engine/install/centos/
    1.添加docker安装源
        sudo yum install -y yum-utils
        sudo yum-config-manager --add-repo http://mirrors.aliyun.com/docker-ce/linux/centos/docker-ce.repo
	2.安装docker
		sudo yum install docker-ce docker-ce-cli containerd.io
	3.启动docker
		sudo systemctl start docker
	4. 将docker加入开机启动
		sudo systemctl enable docker
	5. 测试Docker Engine是否安装好
		sudo docker run hello-world
		如果看到输出"Hello from Docker!"就表示安装成功了
三.安装mysql
	1.拉取镜像
		docker pull mysql:latest(或者docker pull mysql:version(版本号))
	2.启动容器
		docker run -itd --name mysql -p 3306:3306 -e MYSQL_ROOT_PASSWORD=123456 mysql，MYSQL_ROOT_PASSWORD为设置root用户密码
		docker ps | grep mysql出现mysql则启动成功
	3.登录mysql
		docker exec -it container_id /bin/bash
		mysql -uroot -p
		输入密码则登录成功

三.	安装redis
    1.拉取镜像
        docker pull redis
	2.启动容器
	    docker run -itd --name redis -p 6379:6379 redis
	3.登录redis
		docker exec -it redis /bin/bash
		redis-cli

四. 安装zookeeper
    1.拉取镜像
        docker pull zookeeper
    2.启动容器
    	docker run -d -p 2181:2181 --name zookeeper --restart always zookeeper  或者
        docker run --privileged=true -d --name zookeeper --publish 2181:2181  -d zookeeper:latest