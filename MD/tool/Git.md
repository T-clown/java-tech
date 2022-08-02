创建分支： git branch a 
切换分支： git checkout a
创建并切换分支： git checkout -b a (等价于上面两条命令)

查看所有分支
git branch -vv
git branch -a

更新master主线上的东西到该分支上：git rebase master
更新mybranch分支上的东西到master上：git rebase a
提交：git commit -a

创建本地分支和远程分支1
git branch a
git push origin a
git branch --set-upstream-to=origin/a

创建本地分支和远程分支2
git branch a
git push --set-upstream origin a



git init //初始化本地git环境
git clone XXX//克隆一份代码到本地仓库
git pull //把远程库的代码更新到工作台
git pull --rebase origin master //强制把远程库的代码跟新到当前分支上面
git fetch //把远程库的代码更新到本地库
git add . //把本地的修改加到stage中
git commit -m 'comments here' //把stage中的修改提交到本地库
git push //把本地库的修改提交到远程库中
git branch -r/-a //查看远程分支/全部分支
git checkout master/branch //切换到某个分支
git checkout -b test //新建test分支
git branch -d test //删除本地test分支（d大写为强制删除）
git push origin --delete test //删除远程分支
git merge master //假设当前在test分支上面，把master分支上的修改同步到test分支上
git merge tool //调用merge工具

git stash //把未完成的修改缓存到栈容器中
git stash list //查看所有的缓存
git stash pop //恢复本地分支到缓存状态
git stash pop stash@{0}    //选择栈中的第0个版本
git stash clear   //删除所有的版本
git stash drop stash@{0}  //删除第一个队列的版本
git stash show -p stash@{0}

git blame someFile //查看某个文件的每一行的修改记录（）谁在什么时候修改的）
git status //查看当前分支有哪些修改
git log //查看当前分支上面的日志信息


git diff //查看当前没有add的内容
git diff --cache //查看已经add但是没有commit的内容
git diff HEAD //上面两个内容的合并
git reset --hard HEAD //撤销本地修改
echo $HOME //查看git config的HOME路径
export $HOME=/c/gitconfig //配置git config的HOME路径


本地项目推送到github步骤
git init
git add .
git commit -m "提交文件"
关联github仓库
git remote add origin https://github.com/T-clown/python.git
上传本地代码
git push -u origin master
git pull --rebase origin master

将当前分支关联到远程master分支
git branch --set-upstream-to=origin/master master

面试：
git回退到上一个版本
git reset --hard HEAD^
git回退到上上个版本
git reset --hard HEAD^^
git回退到前100个版本
git reset --hard HEAD~100

版本回退
该命令显示从最近到最远的提交日志。每一次提交都有对应的 commit id 和 commit message
git log
根据 id 回退到指定的版本
git reset --hard id
推送到本地到远程仓库：让远程仓库代码和你本地一样，到当前你本地的版本
git push origin HEAD --force
查看命令操作的历史
git reflog
查找到你要的 操作id，依旧使用 上文说的 git reset --hard id。又回退到当初一模一样的版本


远程分支覆盖本地分支
git fetch --all
git reset --hard origin/master (这里master要修改为对应的分支名)
git pull
