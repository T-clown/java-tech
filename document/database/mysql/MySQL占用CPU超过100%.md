[mysql cpu 占用超过 100%](https://xie.infoq.cn/article/a274e214af9b43f77119b1d90)

## mysql 的 cpu 占用率高，大部分情况都是因为慢 sql 语句导致的

- show global status; 列出 MySQL 服务器运行各种状态值
- show variables; 查询 MySQL 服务器配置信息

## 1.慢查询

- show variables like "%slow%";
- 打开慢查询日志可能会对系统性能有一点点影响，如果你的 MySQL 是主－从结构，可以考虑打开其中一台从服务器的慢查询日志，这样既可以监控慢查询，对系统性能影响又小。

## 2.Too many connections

- (1)访问量确实很高，MySQL 服务器抗不住，这个时候就要考虑增加从服务器分散读压力
- (2)MySQL 配置文件中 max_connections 值过小
- 查询最大连接数： show variables like "%max_connections%";
- 历史达到的最大连接数：show global status like 'Max_used_connections';
- 建议：比较理想的设置是 Max_used_connections / max_connections * 100% ≈ 85%最大连接数占上限连接数的 85％左右
- 如果发现比例在 10%以下，MySQL 服务器连接数上限设置的过高了。

## 3.Key_buffer_size

- 查看key_buffer_size设置大小：show variables like "key_buffer_size";
- show global status like "key_read%"： - Key_read_requests：索引读取请求数 - Key_reads：请求在内存中没有找到直接从硬盘读取索引数
- 索引未命中缓存的概率：key_cache_miss_rate ＝ Key_reads / Key_read_requests * 100%
- key_cache_miss_rate 在 0.1%以下都很好（每 1000 个请求有一个直接读硬盘），如果 key_cache_miss_rate 在 0.01%以下的话，key_buffer_size 分配的过多，可以适当减少。

- show global status like "key_blocks_u%";
    - Key_blocks_unused：表示未使用的缓存簇(blocks)数
    - Key_blocks_used：曾经用到的最大的 blocks 数
    - 比较理想的设置：Key_blocks_used / (Key_blocks_unused + Key_blocks_used) * 100% ≈ 80%

## 4.临时表

- show global status like "created_tmp%";
- 每次创建临时表，Created_tmp_tables 增加，
- 如果是在磁盘上创建临时表，Created_tmp_disk_tables 也增加,
- Created_tmp_files 表示 MySQL 服务创建的临时文件文件数，比较理想的配置是：Created_tmp_disk_tables / Created_tmp_tables * 100% <= 25%
- MySQL 服务器对临时表的配置：show variables where Variable_name in ("tmp_table_size", "max_heap_table_size");

## 5.Open Table 情况

- show global status like "open%tables%"; Open_tables ：打开表的数量 Opened_tables ：打开过的表数量
- 如果 Opened_tables 数量过大，说明配置中 table_cache(5.1.3 之后这个值叫做 table_open_cache)值可能太小
- show variables like "table_open_cache";
- 比较合适的值为：Open_tables / Opened_tables * 100% >= 85%， Open_tables / table_cache * 100% <= 95%

## 6.进程使用情况

- show global status like "Thread%";
- 如果在 MySQL 服务器配置文件中设置了 thread_cache_size，当客户端断开之后，服务器处理此客户的线程将会缓存起来以响应下一个客户而不是销毁（前提是缓存数未达上限）。
- Threads_created 表示创建过的线程数，如果发现 Threads_created 值过大的话，表明 MySQL 服务器一直在创建线程，这也是比较耗资源，可以适当增加配置文件中 thread_cache_size 值
- show variables like "thread_cache_size";

## 7.查询缓存(query cache)

- MySQL 5.7.20版本废弃，MySQL 8.0.版本移除
- show global status like "qcache%";

## 8.排序使用情况

- show global status like "sort%";
- Sort_merge_passes 包括两步。MySQL 首先会尝试在内存中做排序，使用的内存大小由系统变量 Sort_buffer_size 决定，
- 如果它的大小不够把所有的记录都读到内存中，MySQL 就会把每次在内存中排序的结果存到临时文件中，等 MySQL 找到所有记录之后，再把临时文件中的记录做一次排序。
- 这再次排序就会增加 Sort_merge_passes。实际上，MySQL 会用另一个临时文件来存再次排序的结果，所以通常会看到 Sort_merge_passes
  增加的数值是建临时文件数的两倍。因为用到了临时文件，所以速度可能会比较慢，增加 Sort_buffer_size 会减少 Sort_merge_passes 和 创建临时文件的次数。
- 但盲目的增加 Sort_buffer_size 并不一定能提高速度，见 How fast can you sort data with MySQL?（另外，增加 read_rnd_buffer_size(3.2.3 是
  record_rnd_buffer_size)的值对排序的操作也有一点的好处

## 9.文件打开数(open_files)

- show global status like "%open_files%";
- 比较合适的设置：Open_files / open_files_limit * 100% <= 75％

## 10.表锁情况

- show global status like "table_locks%";
- Table_locks_immediate 表示立即释放表锁数，
- Table_locks_waited 表示需要等待的表锁数，
- 如果 Table_locks_immediate / Table_locks_waited > 5000，最好采用 InnoDB 引擎，
- 因为 InnoDB 是行锁而 MyISAM 是表锁，对于高并发写入的应用 InnoDB 效果会好些

## 11.表扫描情况

- show global status like "handler_read%";
- show global status like "com_select"; 完成查询请求次数
- 计算表扫描率：表扫描率 ＝ Handler_read_rnd_next / Com_select
- 如果表扫描率超过 4000，说明进行了太多表扫描，很有可能索引没有建好，增加 read_buffer_size 值会有一些好处，但最好不要超过 8MB。
    
