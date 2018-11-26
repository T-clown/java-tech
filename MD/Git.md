创建分支：  git branch a 
切换分支：  git checkout a
创建并切换分支：  git checkout -b a
更新master主线上的东西到该分支上：git rebase master
切换到master分支：git checkout master
更新mybranch分支上的东西到master上：git rebase a
提交：git commit -a

创建本地分支和远程分支1
git branch a
git push origin a
git branch --set-upstream-to=origin/a

创建本地分支和远程分支2
git branch a
git push --set-upstream origin a

查看所有分支
git branch -vv
Git branch -a