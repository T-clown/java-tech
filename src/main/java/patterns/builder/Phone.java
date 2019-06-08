package patterns.builder;


public final class Phone {
    private int id;
    private String brand;
    private int price;
    private String options;
    private String color;


    public static class Builder{
        private int id;
        private String brand;
        private int price;
        private String options;
        private String color;
    }

}
