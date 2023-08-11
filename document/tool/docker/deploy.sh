container_id=`docker ps -a | grep -E "(springboot)" | awk '{print $1}'`
if [ -n "$container_id" ]
then
docker stop $container_id
docker rm $container_id
fi
image_id=` docker images | grep -E "(springboot-service)" | awk '{print $3}'`
if [ -n "$image_id" ]
then
docker image rm $image_id
fi
new_image_id=`docker build -t springboot-service .`
echo $new_image_id
docker run -itd --name springboot -v /etc/localtime:/etc/localtime:ro -e TZ="Asia/Shanghai" -p 8081:8081 springboot-service


##找出kg的容器id
#container_id=`docker ps -a | grep kg | awk '{ print $1}'`
##删除容器
#if [ $container_id ]
#then
#docker stop $container_id
#docker rm $container_id
#fi
##找出kg的镜像
#image_id=` docker images | grep kg-core | awk '{print $3}'`
##删除镜像
#if [ $image_id ]
#then
#docker rmi $image_id
#fi
##重新构建镜像
#new_image_id=`docker build -t kg-core .`
#echo $new_image_id
##运行镜像
#docker run -itd --name kg -v /etc/localtime:/etc/localtime:ro -e TZ="Asia/Shanghai" --net=host kg-core

