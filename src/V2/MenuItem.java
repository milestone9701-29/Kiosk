package V2;

public class MenuItem {
    private final String name;
    private final double price;
    private final String desc;
    public MenuItem(String name, double price, String desc){ // 생성자 3종, 예외처리 2종
        if (name == null || name.isBlank()) throw new IllegalArgumentException("올바른 명칭이 아닙니다.");
        if (price < 0) throw new IllegalArgumentException("올바른 가격이 아닙니다.");

        this.name = name;
        this.price = price;
        this.desc = desc==null?"":desc; // if (desc==null) return this.desc else ""
    }
    public String getName() { return name; }
    public double getPrice() { return price; }
    public String getDesc() { return desc; }
}