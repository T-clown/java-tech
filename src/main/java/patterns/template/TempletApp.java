package patterns.template;

public class TempletApp {
    public static void main(String[] args) {
        String a="a";

        String c= TemplateUtil.call(()->a(a),a);

        useTemplet("1", new Callback<Integer, String>() {
            @Override
            public Integer doSomething(String t) {
                int a= Integer.parseInt(t);
                return a+1;
            }
        });
    }

    public static String a(String a){
        return a+"1";
    }
    public static String b(String a,String b){
        return a+b;
    }


    public static void useTemplet(String str, Callback<Integer, String> callback) {
        System.out.println("固定代码");

        Integer result = callback.doSomething(str);
        System.out.println(result);

        System.out.println("固定代码");
    }
}
