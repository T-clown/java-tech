show databases;     查看数据库
show tables(show tables from databaseName);       查看表
show columns from tableName;        查看表字段
show index from tableName;          查看表索引


show variables like 'max_connections';  查看最大连接数
show status like 'max%connections';     查看已使用连接数

更改最大连接数
1.set GLOBAL max_connections=1000;   全局set(临时)(这种方式重启mysql后会失效)
2.配置文件修改(全局):修改/etc/my.cnf配置文件 在[mysqld]块中修改或添加：max_connections=1000，重启mysql。

查看连接IP列表及数量
select SUBSTRING_INDEX(host,':',1) as ip , count(*) from information_schema.processlist group by ip;

查看数据库连接数
select count(*) from information_schema.processlist;

查看进程列表
show processlist;        列出前100条
show full processlist;   列出所有
各列的含义:
Id：该进程程序登录mysql时，系统分配的连接id，即为connection_id。
User：该进程程序连接mysql的用户。
Host：该进程程序连接mysql的ip。
db：该进程程序连接mysql的某个数据库。
Command：该进程程序执行的命令，取值为休眠（Sleep）、查询（Query）、连接（Connect）等。
Time：Command状态持续的时间，单位为秒。
State：使用当前的sql语句的状态，如starting。
Info：显示sql语句，如当前执行了show full processlist。

查看连接数据库的java进程
数据库端口为3306，查看连接该端口的java程序进程netstat -anp | grep 3306 | grep java

查看进程对应的程序
ps -ef | grep 进程号

统计某个进程连接数
netstat -anp | grep 3306 | grep 进程号 | wc -l