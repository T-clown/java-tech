package feature.entity;

import java.math.BigDecimal;
import java.util.Date;

public class Student implements Comparable<Student> {
    public int id;
    public String name;
    public String address;
    public int score;
    public BigDecimal high;
    public Date date;

    public Student() {
    }

    public Student(int id, String name, String address, int score) {
        this.id = id;
        this.name = name;
        this.address = address;
        this.score = score;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public BigDecimal getHigh() {
        return high;
    }

    public void setHigh(BigDecimal high) {
        this.high = high;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) { return true; }
        if (o == null || getClass() != o.getClass()) { return false; }

        Student student = (Student)o;

        if (id != student.id) { return false; }
        if (score != student.score) { return false; }
        if (name != null ? !name.equals(student.name) : student.name != null) { return false; }
        return address != null ? address.equals(student.address) : student.address == null;
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + (name != null ? name.hashCode() : 0);
        result = 31 * result + (address != null ? address.hashCode() : 0);
        result = 31 * result + score;
        return result;
    }

    @Override
    public String toString() {
        return "Student{" +
            "id=" + id +
            ", name='" + name + '\'' +
            ", address='" + address + '\'' +
            ", score=" + score +
            ", high=" + high +
            ", date=" + date +
            '}';
    }

    @Override
    public int compareTo(Student stu) {
        return this.id - stu.id;
    }
}
