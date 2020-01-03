package patterns.observer;

import java.util.ArrayList;
import java.util.List;

import lombok.Data;


public class Teacher {
    List<Student> observers = new ArrayList<>();
    private int state;

    public Teacher(){
    }
    public void setState(int state) {
        this.state = state;
        notifyAllObservers("明天放"+state+"天假！");
    }

    public void addStudent(Student student){
        observers.add(student);
    }


    public void notifyAllObservers(String message) {
        for(Student student :observers){
            student.getMessage(message);
        }
    }



}
