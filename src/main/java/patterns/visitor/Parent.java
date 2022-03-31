package patterns.visitor;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


public class Parent implements Visitor {

    private Logger logger = LoggerFactory.getLogger(Parent.class);

    @Override
    public void visit(Student student) {
        logger.info("{}访问学生:{}", this.toString(), student.getName());
    }

    @Override
    public void visit(Teacher teacher) {
        logger.info("{}访问教师:{}", this.toString(), teacher.getName());
    }

    @Override
    public String toString() {
        return "家长";
    }

}
