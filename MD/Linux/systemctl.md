systemctl命令是系统服务管理器指令
使某服务自动启动	 	systemctl enable httpd.service
使某服务不自动启动	 systemctl disable httpd.service
验证一下是否为开机启动 systemctl is-enabled httpd.service
检查服务状态	  systemctl status httpd.service （服务详细信息） systemctl is-active httpd.service （仅显示是否 Active)
显示所有已启动的服务	systemctl list-units --type=service
启动某服务	systemctl start httpd.service
停止某服务	systemctl stop httpd.service
重启某服务	systemctl restart httpd.service

http://www.ruanyifeng.com/blog/2016/03/systemd-tutorial-commands.html
http://www.ruanyifeng.com/blog/2016/03/systemd-tutorial-part-two.html
一般系统管理员手工创建的单元文件建议存放在/etc/systemd/system/目录下面

systemctl daemon-reload