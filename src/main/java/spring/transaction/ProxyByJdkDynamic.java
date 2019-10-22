package spring.transaction;

//代理类，也要实现相同的接口
class ProxyByJdkDynamic implements Service {

    //包含目标对象
    private Service target;

    public ProxyByJdkDynamic(Service target) {
        this.target = target;
    }

    //目标类中此方法带注解，进行特殊处理
    @Override
    public void doNeedTx() {
        //开启事务
        System.out.println("-> create Tx here in Proxy");
        //调用目标对象的方法，该方法已在事务中了
        target.doNeedTx();
        //提交事务
        System.out.println("<- commit Tx here in Proxy");
    }

    //目标类中此方法没有注解，只做简单的调用
    @Override
    public void doNotneedTx() {
        //直接调用目标对象方法
        target.doNotneedTx();
    }
}
