- 上传文件到Linux
  1. rz
  2. scp【本地文件的路径】【服务器用户名】@【服务器地址】:【服务器上存放文件的路径】
     scp file username@ip:path

  -v 和大多数 linux命令中的-v意思一样，用来显示进度。可以用来查看连接、认证、或是配置错误
  -C 使能压缩选项
  -P 选择端口
  -r 复制目录

- Linux下载文件
  1. sz file(下载文件夹sz file/*，只下载文件夹里面的内容)
  2. scp 【服务器用户名】@【服务器地址】:【服务器上存放文件的路径】【本地文件的路径】
     scp  username@ip:path file


ps -p PID -o lstart
PID 为进程id  -o 是output 输出项
-o etime 是运行时间
-o lstart 是开始时间
-o comm 是进程名
-o user 运行用户

## Linux 将用户账号、密码等相关的信息分别存储在四个文件夹下：
    /etc/passwd —— 管理用户UID/GID重要参数
    /etc/shadow —— 管理用户密码
    /etc/group —— 管理用户组相关信息
    /etc/gshadow —— 管理用户组管理员相关信息
    这些文件中，每一行代表一个用户或一个用户组，并存储了相关的用户或用户组信息。

###免输密码登录
sshpass -p  "password" ssh -t root@ip "cd /project/logs/ && exec bash -l"
以root账号登录ip并直接进入/project/logs/目录，password是密码