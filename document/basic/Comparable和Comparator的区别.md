### Comparable<T>

1. 类T实现Comparable接口并重写compareTo方法，实现类的自定义自然排序
2. 集合工具如SortedMap，SortedSet，Collections.sort(List) 和Arrays.sort(Object[])方法会自动使用compareTo方法进行排序

### Comparator<T>

1. 这是一个函数式比较器，
2. 当元素类没有实现Comparable接口时，可用Comparator比较器实现自定义排序，如Collections.sort(List,Comparator)，Arrays.sort(Object[],Comparator)
   ，或者一些需要排序的集合框架里通过构造函数传入 比较器，如SortedMap，SortedSet
3. Comparator接口常用于需要多种排序方式的情况下，或者对已经实现了Comparable接口的类进行额外的比较规则定义
  