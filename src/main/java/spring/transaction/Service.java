package spring.transaction;

public interface Service {
    //接口
    void doNeedTx();

    void doNotneedTx();
}
