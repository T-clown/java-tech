package patterns.observer;

import java.util.ArrayList;
import java.util.List;

public class Teacher {
    List<Student> list = new ArrayList<>();
    public Teacher(){
    }

    public void addStudent(Student student){
        list.add(student);
    }


    public void notifyStudent() {
        for(Student student :list){
            student.getMessage("明天放假!");
        }
    }

}
