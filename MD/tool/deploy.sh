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
