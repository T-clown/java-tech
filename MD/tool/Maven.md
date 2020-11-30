在maven中手动添加本地jar包
在 cmd命令行中 运行
mvn install:install-file   -Dfile=/c/Users/YCKJ2717/Desktop/janusgraph-utils/janusgraph-util-1.0-SNAPSHOT.jar -DgroupId=com.janusgraph -DartifactId=janusgraph-util -Dversion=1.0-SNAPSHOT  -Dpackaging=jar
上面的命令解释：
-Dfile：指明你下载的jar包的位置（就是本地存放jar的路径+jar包名）；
-DgroupId， -DartifactId，  -Dversion：三个参数，就是指明了存放maven仓库中的位置；
-Dpackaging ：猜测就是指明文件类型
  
