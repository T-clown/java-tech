1. 修改数据表名

- ALTER TABLE [表名.]OLD_TABLE_NAME RENAME TO NEW_TABLE_NAME;

2. 修改列名

- ALTER TABLE [表名.]TABLE_NAME RENAME COLUMN OLD_COLUMN_NAME TO NEW_COLUMN_NAME;
- ALTER TABLE [表名.]TABLE_NAME CHANGE COLUMN OLD_COLUMN_NAME NEW_COLUMN_NAME;

3. 修改列的数据类型

- ALTER TABLE [表名.]TABLE_NAME MODIFY COLUMN_NAME NEW_DATATYPE;

4. 插入列

- ALTER TABLE [表名.]TABLE_NAME ADD COLUMN_NAME DATATYPE;

5. 删除列

- ALTER TABLE [表名.]TABLE_NAME DROP COLUMN COLUMN_NAME;

### 其他

1. 删除重复数据，仅保留一条

- delete FROM student WHERE id NOT IN ( SELECT t.id FROM ( SELECT MIN(id) AS id FROM student GROUP BY `name` ) t )

2. 删除重复数据，全部删除

- DELETE FROM student WHERE NAME IN (select t.name from ( SELECT NAME FROM student GROUP BY NAME HAVING count( 1 ) > 1)
  t)


3. 两表之间：根据一个表的字段更新另一个表的字段

- UPDATE A LEFT JOIN B ON A.ID=B.ID SET A.SCENIC_TYPE= B.TYPE
- UPDATE TABLE1 SET FIELD1=TABLE2.FIELD1, FIELD2=TABLE2.FIELD2 FROM TABLE2 WHERE TABLE1.ID=TABLE2.ID
- UPDATE table AS t1 JOIN table AS t2 ON t1.f1 = t2.f2 SET t1.f3 = t2.f4 WHERE t1.id=924856635290562560

4. 将一张表的查询结果插入另一张表

- INSERT INTO table (field_a, field_b) SELECT field1 AS field_a,field2 AS field_b FROM source_table

查询json数据
select data->'$.id' id,data->'$.name' name from log where data->'$.id' = 142;
select * from log2 where JSON_CONTAINS(data,JSON_OBJECT('id', "142"))
select json_extract(data,'$.name'),json_extract(data,'$.tel') from tab_json;