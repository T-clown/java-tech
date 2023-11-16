package patterns.strategy;

public class ByOneGetTwo implements  Promotion<String, String> {
    @Override
    public String execute(String a) {
        System.out.println("买一送一");
        return "买一送一";
    }
}
