package spring.transaction;

import org.springframework.transaction.annotation.Transactional;

//目标类，实现接口
public class ServiceImpl implements Service {

    @Transactional(rollbackFor = Exception.class)
    @Override
    public void doNeedTx() {
        System.out.println("execute doNeedTx in ServiceImpl");
    }

    //no annotation here
    @Override
    public void doNotneedTx() {
        this.doNeedTx();
    }
}
