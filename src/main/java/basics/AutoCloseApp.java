package basics;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.Closeable;
import java.io.IOException;
import java.io.InputStream;
import java.lang.ref.Cleaner;

/**
 * 如果需要在try-with-resources语句中管理资源的生命周期，并在资源使用完毕后自动释放，应该使用AutoCloseable。
 * 如果需要更细粒度的资源管理和更灵活的清理时机，可以考虑使用Cleaner
 */
public class AutoCloseApp {
    private static final Logger logger = LoggerFactory.getLogger(AutoCloseApp.class);

    public static void main(String[] args) {
        InputStream inputStream=new InputStream() {
            @Override
            public int read() throws IOException {
                return 0;
            }
        };
        try (CleanerApp cleanerApp = new CleanerApp(inputStream)) {

        }catch (Exception e){

        }

        try (CloseAble able = new CloseAble()) {
            //int a = 1 / 0;
            able.test();
        } catch (Exception e) {
            logger.info("异常" + e.getMessage());
        } finally {
            logger.info("finally方法执行");
        }
    }

    static class CleanerApp implements AutoCloseable {

        private static final Cleaner cleaner = Cleaner.create();
        private final State state;
        private final Cleaner.Cleanable cleanable;

        public CleanerApp(InputStream inputStream) {
            this.state = new State(inputStream);
            this.cleanable = cleaner.register(this, state);
        }

        static class State implements Runnable {
            private InputStream inputStream;

            State(InputStream inputStream) {
                this.inputStream = inputStream;
            }

            @Override
            public void run() {
                // 执行资源清理操作
                logger.info("执行资源清理操作");
                try {
                    inputStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }

        @Override
        public void close() {
            cleanable.clean();
            logger.info("回收cleanable对象");
        }
    }

    /**
     * 实现Closeable或者AutoCloseable都能用try-with-resources语法关闭资源
     */
    static class CloseAble implements Closeable {

        public void test() {
            logger.info("test执行");
            int a = 1 / 0;
        }

        @Override
        public void close() {
            logger.info("Closeable关闭资源");
        }
    }
}
