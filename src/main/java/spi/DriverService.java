package spi;


import java.sql.Connection;
import java.sql.Driver;
import java.sql.DriverPropertyInfo;
import java.sql.SQLException;
import java.sql.SQLFeatureNotSupportedException;
import java.util.Properties;
import java.util.ServiceLoader;
import java.util.logging.Logger;

public class DriverService implements Driver {
    public static void main(String[] args) {
        /**
         * 系统类加载器
         * 它是负责加载JVM启动时在classpath中指定的类
         * 这个类加载器是通过 sun.misc.Launcher$AppClassLoader 实现的，它通常会被用来加载应用程序中的类
         */
        ClassLoader systemClassLoader = ClassLoader.getSystemClassLoader();
        /**
         * 线程上下文类加载器
         * 用于加载应用程序中的类，这些类可能不在classpath中。
         * 通常被用来解决Java应用程序中的类依赖性问题，比如在应用程序中使用第三方库时，库可能依赖于其他库，这些库可能不在应用程序的classpath中，上下文类加载器可以解决这个问题。
         */
        ClassLoader contextClassLoader = Thread.currentThread().getContextClassLoader();
        //Thread.currentThread().setContextClassLoader();
        System.out.println(systemClassLoader.getName());
        System.out.println(Driver.class.getClassLoader().getName());
        //这里Driver.class的类加载器为platform，按道理这里应该用platform去加载，但是实现类和Driver不在一个包里，platform加载不到，这个时候需要依赖子类加载器去加载，即应用类加载器，那如何获取应用类加载器，则使用Thread.currentThread().getContextClassLoader()
        ServiceLoader<Driver> load = ServiceLoader.load(Driver.class, Driver.class.getClassLoader());

        ServiceLoader<Driver> load2 = ServiceLoader.load(Driver.class);
        for (Driver driver : load2) {
            System.out.println(driver.getClass().getName());
        }
        //SpringFactoriesLoader.loadFactories()
    }

    @Override
    public Connection connect(String url, Properties info) throws SQLException {
        return null;
    }

    @Override
    public boolean acceptsURL(String url) throws SQLException {
        return false;
    }

    @Override
    public DriverPropertyInfo[] getPropertyInfo(String url, Properties info) throws SQLException {
        return new DriverPropertyInfo[0];
    }

    @Override
    public int getMajorVersion() {
        return 0;
    }

    @Override
    public int getMinorVersion() {
        return 0;
    }

    @Override
    public boolean jdbcCompliant() {
        return false;
    }

    @Override
    public Logger getParentLogger() throws SQLFeatureNotSupportedException {
        return null;
    }
}
