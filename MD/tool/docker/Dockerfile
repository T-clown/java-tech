FROM java:8
MAINTAINER ti93
ADD springboot-service-0.0.1-SNAPSHOT.jar springboot-service.jar
EXPOSE 8081
ENTRYPOINT ["java","-jar","-Duser.timezone=GMT+8","springboot-service.jar"]
RUN /bin/cp /usr/share/zoneinfo/Asia/Shanghai /etc/localtime && echo 'Asia/Shanghai' >/etc/timezone
