package patterns.visitor;


public interface Visitor {

    void visit(Student student);

    void visit(Teacher teacher);

}
