package patterns.builder;

public class Phone {
    public int id;
    public String brand;
    public int price;
    public String options;
    public String color;

    public Phone(PhoneBuilder builder) {
        this.id = builder.id;
        this.brand = builder.brand;
        this.price = builder.price;
        this.options = builder.options;
        this.color = builder.color;
    }

    Phone() {
    }

    public static PhoneBuilder builder() {
        return new PhoneBuilder();
    }

    public static class PhoneBuilder {
        private int id;
        private String brand;
        private int price;
        private String options;
        private String color;

        PhoneBuilder() {
        }

        public PhoneBuilder id(int id) {
            this.id = id;
            return this;
        }

        public PhoneBuilder brand(String brand) {
            this.brand = brand;
            return this;
        }

        public PhoneBuilder price(int price) {
            this.price = price;
            return this;
        }

        public PhoneBuilder options(String options) {
            this.options = options;
            return this;
        }

        public PhoneBuilder color(String color) {
            this.color = color;
            return this;
        }

        public Phone build() {
            return new Phone(this);
        }
    }

}
