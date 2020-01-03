package patterns.observer;

import javax.security.auth.Subject;

import lombok.Data;

@Data
public class XiaoBai implements Student {
    private Teacher teacher;

    public XiaoBai(Teacher teacher) {
        this.teacher = teacher;
        this.teacher.addStudent(this);
    }

    @Override
    public void getMessage(String s) {
        System.out.println( "小白接到了老师打过来的电话，电话内容是：" + s);
    }

}
