联合索引idx(a,b) 主键id（表字段id,a,b,c）t
1.当查询字段为(a,b,id)的子集，且条件为(a,b)的子集，则会用到idx索引
select a from t where b=1;
2.当查询字段不为(a,b,id)的子集：
  1.若条件为id和其他字段(and)，则不会用到索引
    select a,c from t where id=1 and a=1;
  1.若条件是a和其他字段(and)，则会用到idx索引
    select a,c from t where a=1 and c=1;