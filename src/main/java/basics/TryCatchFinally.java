package basics;

import java.io.Closeable;
import java.io.IOException;

public class TryCatchFinally {
    public static void main(String[] args) {

        //System.out.println(test09());
        try (CloseAble able = new TryCatchFinally().new CloseAble()) {
            able.test();
        } catch (Exception e) {
            System.out.println("异常" + e.getMessage());
        } finally {
            System.out.println("finally方法执行");
        }
    }

    /**
     * 实现Closeable或者AutoCloseable都能用try-with-resources语法关闭资源
     */
    class CloseAble implements Closeable {

        public void test() {
            System.out.println("test执行");
            int a=1/0;
        }

        @Override
        public void close() throws IOException {
            System.out.println("Closeable关闭资源");
        }
    }

    //try
    public static final String test01() {
        String t = "";
        try {
            t = "try";
            return t;
        } catch (Exception e) {
            t = "catch";
            return t;
        } finally {
            t = "finally";
        }
    }

    //finally
    public static final String test02() {
        String t = "";

        try {
            t = "try";
            return t;
        } catch (Exception e) {
            // result = "catch";
            t = "catch";
            return t;
        } finally {
            t = "finally";
            return t;
        }
    }

    //catch
    public static final String test03() {
        String t = "";
        try {
            t = "try";
            Integer.parseInt(null);
            return t;
        } catch (Exception e) {
            t = "catch";
            return t;
        } finally {
            t = "finally";
            // System.out.println(t);
            // return t;
        }
    }

    //finally
    public static final String test04() {
        String t = "";
        try {
            t = "try";
            Integer.parseInt(null);
            return t;
        } catch (Exception e) {
            t = "catch";
            return t;
        } finally {
            t = "finally";
            return t;
        }
    }

    //NumberFormatException
    public static final String test05() {
        String t = "";

        try {
            t = "try";
            Integer.parseInt(null);
            return t;
        } catch (Exception e) {
            t = "catch";
            Integer.parseInt(null);
            return t;
        } finally {
            t = "finally";
            //return t;
        }
    }

    //finally
    public static final String test06() {
        String t = "";

        try {
            t = "try";
            Integer.parseInt(null);
            return t;
        } catch (Exception e) {
            t = "catch";
            Integer.parseInt(null);
            return t;
        } finally {
            t = "finally";
            return t;
        }
    }

    //NumberFormatException
    public static final String test07() {
        String t = "";

        try {
            t = "try";
            Integer.parseInt(null);
            return t;
        } catch (NullPointerException e) {
            t = "catch";
            return t;
        } finally {
            t = "finally";
        }
    }

    //finally
    public static final String test08() {
        String t = "";

        try {
            t = "try";
            Integer.parseInt(null);
            return t;
        } catch (NullPointerException e) {
            t = "catch";
            return t;
        } finally {
            t = "finally";
            return t;
        }
    }

    //NullPointerException
    public static final String test09() {
        String t = "";

        try {
            t = "try";
            return t;
        } catch (Exception e) {
            t = "catch";
            return t;
        } finally {
            t = "finally";
            String.valueOf(null);
            return t;
        }
    }

}
