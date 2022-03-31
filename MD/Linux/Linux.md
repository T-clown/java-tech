系统状态篇
https://mp.weixin.qq.com/s/1OXg1QBypTM5lXgyfOLI0g
文本查看篇
https://mp.weixin.qq.com/s/IzGdVjGLo2PzQ3AKOhtP9g
常用命令
https://mp.weixin.qq.com/s/6TbFFXGgj-AcOQMZu-dHJw
https://mp.weixin.qq.com/s?__biz=MzI4MDEwNzAzNg==&mid=400870558&idx=1&sn=cabba9c79cbe32707cdb85d43c8c4560#rd


总地址
http://w.itcodemonkey.com/article/23.html?v=1

上传文件到linux
1.rz
2.scp
-P [port] : 指定SSH端口号
-r : 递归复制整个目录
scp file username@ip:path

linux下载文件
1.sz file(下载文件夹sz file/*，只下载文件夹里面的内容)
2.scp  username@ip:path file


ps -p PID -o lstart
PID 为进程id  -o 是output 输出项
-o etime 是运行时间
-o lstart 是开始时间
-o comm 是进程名
-o user 运行用户

##Linux 将用户账号、密码等相关的信息分别存储在四个文件夹下：
    /etc/passwd —— 管理用户UID/GID重要参数
    /etc/shadow —— 管理用户密码
    /etc/group —— 管理用户组相关信息
    /etc/gshadow —— 管理用户组管理员相关信息
    这些文件中，每一行代表一个用户或一个用户组，并存储了相关的用户或用户组信息。