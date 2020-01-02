package patterns.observer;

import lombok.Data;

@Data
public class XiaoLi implements Student {
    private Teacher teacher;

    public XiaoLi(Teacher teacher) {
        this.teacher = teacher;
        this.teacher.addStudent(this);
    }

    @Override
    public void getMessage(String s) {
        System.out.println( "小李接到了老师打过来的电话，电话内容是：" + s);
    }

}
