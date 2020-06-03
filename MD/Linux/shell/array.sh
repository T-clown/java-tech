#!/bin/bash
#定义数组
my_array=(A B "C" D)
#定义数组
array_name[0]=1
array_name[1]=2
array_name[2]=3

#读取数组
echo "第一个元素为: ${my_array[0]}"
echo "第二个元素为: ${my_array[1]}"
echo "第三个元素为: ${my_array[2]}"
echo "第四个元素为: ${my_array[3]}"

#使用@ 或 * 可以获取数组中的所有元素
echo "数组的元素为: ${array_name[*]}"
echo "数组的元素为: ${array_name[@]}"

#获取数组长度的方法与获取字符串长度的方法相同
echo "数组元素个数为: ${#my_array[*]}"
echo "数组元素个数为: ${#my_array[@]}"