https://mp.weixin.qq.com/s?subscene=23&__biz=MjM5OTA1MDUyMA==&mid=2655443277&idx=2&sn=80bbe05f8237e8d899b9820e628334fd&chksm=bd73153a8a049c2c48189f907a4fcbf731b09b7b7d159b1f029615c98572752238079bdad542&scene=7&key=40e599c655b82c87dabee3e9b2b7175bffa30901120fa10a198cf9e7c3b2026f22642e37452c7a143fd4ca3a9ec495154122a1ab54ace0239f5ad6aabc17b40ec465acbc8d57dc3f5fa09f11ac2c6227&ascene=0&uin=MTIwNzg3MDIyOQ%3D%3D&devicetype=Windows+10+x64&version=62090070&lang=zh_CN&exportkey=AQqkRMkaj83kK6DFX50RH7M%3D&pass_ticket=IkHFIe5SdC17beybYjrdR%2FJ6VIlH%2FIGgiYLgJyvvniSIQuzQ3NHOedRjsBv7Yhtt
https://mp.weixin.qq.com/s?subscene=23&__biz=MzAwODkwMDk4OQ==&mid=2247483785&idx=1&sn=fc28e5e6b30bb4a71e0a3e4109249f9d&chksm=9b66999bac11108de28f53caa7191078495c4aeed7805fc7a4781dc9309d52a748b56e7d695c&scene=7&key=4ed30a840d75ec687f0171985f80fcace6a183184bb3f013836adfc5ead33f5f43365ac701cc8527030bfd8f2b28f49e3666dd9305b65c69c53ab187130fadac33b29a1000b523f86bdad230f5e00c82&ascene=0&uin=MTIwNzg3MDIyOQ%3D%3D&devicetype=Windows+10+x64&version=62090070&lang=zh_CN&exportkey=AaEC6NQ7omlixl5E0huR%2BJI%3D&pass_ticket=V4P1Tq3Jw%2FnL0x3yCNQ295CwoLfad7y1nTKiXGYN5cnPn6l7qGgVpqgGw1HImZID
### 语法
- awk [-F ERE] [-v assignment] ... program [argument ...]
- awk [-F ERE] -f progfile ...  [-v assignment] ...[argument ...]
  - -F ERE：定义字段分隔符，该选项的值可以是扩展的正则表达式（ERE）
  - -f progfile：指定awk脚本，可以同时指定多个脚本，它们会按照在命令行中出现的顺序连接在一起
  - -v assignment：定义awk变量，形式同awk中的变量赋值，即name=value，赋值发生在awk处理文本之前

例：
echo "1:2:3" | awk -F: '{print $1 " and " $2 " and " $3}'
echo | awk -v a=1 'BEGIN {print a}'
BEGIN是一个特殊的pattern，它在awk处理输入之前就会执行，可以认为是一个初始化语句，与此对应的还有END