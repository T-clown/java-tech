#!/bin/sh
echo "It is a test"
echo It is a test
echo "\"It is a test\""

read name 
echo "$name It is a test"

echo -e "OK! \n" # -e 开启转义
echo "It is a test"

echo -e "OK! \c" # -e 开启转义 \c 不换行
echo "It is a test"
#显示结果定向至文件
echo "It is a test" > myfile
#原样输出字符串，不进行转义或取变量(用单引号)
echo '$name\"'

echo `date`