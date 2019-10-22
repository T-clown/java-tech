package spring.transaction;

import org.springframework.stereotype.Service;

@Service
public class ExampleServiceImpl implements ExampleService{
    @Override
    public void doNeedTx() {

    }

    @Override
    public void doNotneedTx() {

    }
}
