#!/bin/bash
#命名只能使用英文字母，数字和下划线，首个字符不能以数字开头。
#中间不能有空格，可以使用下划线（_）。
#不能使用标点符号。
#不能使用bash里的关键字（可用help命令查看保留关键字）

your_name="qinjx"
echo $your_name
echo ${your_name}

your_name="tom"
echo $your_name
your_name="alibaba"
echo $your_name

#使用 readonly 命令可以将变量定义为只读变量，只读变量的值不能被改变
myUrl="http://www.google.com"
readonly myUrl
#myUrl="http://www.runoob.com"

#使用 unset 命令可以删除变量，变量被删除后不能再次使用。
#unset 命令不能删除只读变量
url="http://www.google.com"
unset url
echo $url

#1) 局部变量 局部变量在脚本或命令中定义，仅在当前shell实例中有效，其他shell启动的程序不能访问局部变量。
#2) 环境变量 所有的程序，包括shell启动的程序，都能访问环境变量，有些程序需要环境变量来保证其正常运行。必要的时候shell脚本也可以定义环境变量。
#3) shell变量 shell变量是由shell程序设置的特殊变量。shell变量中有一部分是环境变量，有一部分是局部变量，这些变量保证了shell的正常运行

#字符串是shell编程中最常用最有用的数据类型（除了数字和字符串，也没啥其它类型好用了），字符串可以用单引号，也可以用双引号，也可以不用引号
#单引号里的任何字符都会原样输出，单引号字符串中的变量是无效的；
#单引号字串中不能出现单独一个的单引号（对单引号使用转义符后也不行），但可成对出现，作为字符串拼接使用。

#双引号里可以有变量
#双引号里可以出现转义字符

#拼接字符串
your_name="runoob"
# 使用双引号拼接
greeting="hello, "$your_name" !"
greeting_1="hello, ${your_name} !"
echo $greeting  $greeting_1
# 使用单引号拼接
greeting_2='hello, '$your_name' !'
greeting_3='hello, ${your_name} !'
echo $greeting_2  $greeting_3

#获取字符串长度
string="abcd"
echo ${#string} #输出 4

#提取子字符串
#以下实例从字符串第 2 个字符开始截取 4 个字符：
string="runoob is a great site"
echo ${string:1:4} # 输出 unoo

#查找子字符串
#查找字符 i 或 o 的位置(哪个字母先出现就计算哪个)：
string="runoob is a great site"
echo `expr index "$string" io`  # 输出 4

echo "Shell 传递参数实例！";
echo "执行的文件名：$0";
echo "第一个参数为：$1";
echo "第二个参数为：$2";
echo "第三个参数为：$3";

echo "参数个数为：$#";
echo "传递的参数作为一个字符串显示：$*";

#$* 与 $@ 区别：
#相同点：都是引用所有参数。
#不同点：只有在双引号中体现出来。假设在脚本运行时写了三个参数 1、2、3，，则 " * " 等价于 "1 2 3"（传递了一个参数），而 "@" 等价于 "1" "2" "3"（传递了三个参数）。

echo "-- \$* 演示 ---"
for i in "$*"; do
    echo $i
done

echo "-- \$@ 演示 ---"
for i in "$@"; do
    echo $i
done
#$?	显示最后命令的退出状态。0表示没有错误，其他任何值表明有错误
echo "退出状态"$?""