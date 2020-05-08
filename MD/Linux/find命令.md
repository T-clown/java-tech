1.基于name查找文件
查找/目录下名称为tecmint.txt的文件
find / -name tecmint.txt
find / -iname tecmint.txt #忽略大小写
find / -type f -name tecmint.txt
find / -type d -name a  #查找/目录下名称为a的目录
find / -type f -name "*.sh" #查找/目录下.sh后缀的文件
二、基于权限查询文件
find . -type f -perm 0777 -print #查找权限为777的所有文件
find / -type f ! -perm 777 #查找所有文件未经许可777
find / -perm 2644 #查找权限设置为644的所有SGID位文件
find / -perm 1551 #查找权限为551的所有Sticky Bit设置文件
find / -perm /u=s #查找所有SUID集文件
find / -perm /g+s #查找所有SGID设置文件
find / -perm /u=r #查找所有只读文件
find / -perm /a=x #查找所有可执行文件
find / -type f -perm 0777 -print -exec chmod 644 {} #查找所有777个权限文件，并使用chmod命令将权限设置为644
find / -type d -perm 777 -print -exec chmod 755 {} #查找所有777个权限目录，并使用chmod命令将权限设置为755
find . -type f -name "tecmint.txt" -exec rm -f {} #查找并删除单个文件
find . -type f -name "*.txt" -exec rm -f{} #查找和删除多个文件 
find . -type f -name "*.mp3" -exec rm -f{} #查找和删除多个文件 
find /tmp -type f -empty  #在/tmp路径下查找所有空文件
find /tmp -type d -empty  #将/tmp路径下的所有空目录
find /tmp -type f -name ".*" #要查找所有隐藏的文件，请使用以下命令

三、基于用户和组查询文件
find / -user root -name tecmint.txt  #在所有者root的/ root目录下查找名为tecmint.txt的所有或单个文件
find /home -user tecmint #查找/ home目录下属于用户tecmint的所有文件
find /home -group developer #查找/ home目录下属于Group Developer的所有文件
find /home -user tecmint -iname "*.txt" #查找home目录下的用户tecmint的所有.txt文件 

四、基于时间查询文件或目录
find / -mtime 50 #查找50天后修改的所有文件
find / -atime 50 #查找50天后访问的所有文件
find / -mtime +50 –mtime -100 #查找所有被修改超过50天以及少于100天的文件
find / -cmin -60 #查找最近1小时内更改的所有文件
find / -mmin -60 #查找最近1小时内修改的所有文件
find / -amin -60 #查找最近1小时内访问的文件

五、基于大小查询文件或目录
find / -size 50M  #找到所有50MB的文件
find / -size +50M -size -100M  #找到大于50MB且小于100MB的所有文件
find / -size +100M -exec rm -rf {} #查找所有100MB文件并使用一个命令删除它们
find / -type f -name *.mp3 -size +10M -exec rm {} #查找超过10MB的所有.mp3文件，并使用一个命令删除它们



查找空文件或空目录
1.查找空文件
find ./ -size 0 #查找当前目录下大小为0的文件
find -type f -empty # -type f指明了要查找的是文件
2.查找空目录
find -type d -empty # -type d指明了要查找的是目录
查找时排除文件或目录
1.查找时排除文件
find ./ -name "*test*"  ! -name "*.log" #排除.log文件
2.查找时排除目录
find .  -path ./test -prune -o -name "*.txt"
find ./ ( -path "./test" -o -path "./home" ) -prune -o -name "*.txt" 
find ./ -name "*.txt" ! -path "./test"
对查找到的文件执行命令操作
1.利用xargs
find -name "*.log" | xargs rm -f #找到.log文件后，删除
find -name "*test" | xargs chmod 755 #将找到文件的权限修改为755
find -name "*test" | xargs grep "hello" #查找包含hello字符串的test文件
2.利用-exec参数
find ./ -name "*txt"  -exec rm -f {} ;#找到后删除
find ./ -name "*txt"  -exec cp {} ./test ;#找到后复制至test目录下
3.利用-ok参数，它与-exec的差别在于，它会询问用户
find ./ -name "*.log" -ok ls -al {}
4.利用-delete参数
find ./ -name "*.log" -delete #删除以log为后缀的文件

常用的条件组合参数有-a(and),-o(or),!(not)
find ./ -type f -o -type l #查找普通文件和符号链接文件
find ./ -name "*.zip" -o -name "*.gz" #在当前目录下查找zip包和gz包
find ./ -name "*test" -a -type l  #查找名为test的符号链接文件
find ./ ! -name "*.log" #查找log文件以外的文件

查找比某文件新或某文件旧的文件
newer 修改时间更新的
anewer 访问时间更新的
ctime 修改时间更新的，包括属性的修改
find ./ ! -newer 1.log |xargs ls -al #列出比1.log更旧的文件
find ./  -newer 1.log |xargs ls -al #列出比1.log更新的文件

查找结果以特定格式输出
find ./ -name "*.log" -printf "%f %a %M %s
%f 文件名
%a 访问时间
%c 修改时间
%M 权限信息
%m 权限位信息
%s 文件大小，单位为字节
%d 文件所在目录层级
%u 文件所属用户
%p 带相对路径的完整名
%y 文件类型