package patterns.template;

public class TempletApp {

    public static void main(String[] args) {
        String a="a";

        String c= TemplateUtil.call(()->a(a),a);
        System.out.println(c);
//        useTemplet("1", t -> {
//            int a1 = Integer.parseInt(t);
//            return a1 + 1;
//        });
    }

    public static String a(String a){
        return a+"1";
    }


    public static void useTemplet(String str, Callback<Integer, String> callback) {
        System.out.println("固定代码");

        Integer result = callback.doSomething(str);
        System.out.println(result);

        System.out.println("固定代码");
    }
}
