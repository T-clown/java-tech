package patterns.observer;

/**
 * 对象间一对多的依赖关系，当一个对象的状态发生改变时，所有依赖于它的对象都得到通知并被自动更新
 */
public class App {
    public static void main(String[] args) {

        Teacher teacher = new Teacher();
        XiaoBai lao_wang = new XiaoBai();
        XiaoLi lao_li = new XiaoLi();

        teacher.addStudent(lao_wang);
        teacher.addStudent(lao_li);

        teacher.notifyStudent();
    }
}
