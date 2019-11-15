package patterns.observer;

/**
 * 对象间一对多的依赖关系，当一个对象的状态发生改变时，所有依赖于它的对象都得到通知并被自动更新
 */
public class App {
    public static void main(String[] args) {

        XiaoMei xiao_mei = new XiaoMei();
        LaoWang lao_wang = new LaoWang();
        LaoLi lao_li = new LaoLi();


        xiao_mei.addPerson(lao_wang);
        xiao_mei.addPerson(lao_li);


        xiao_mei.notifyPerson();
    }
}
