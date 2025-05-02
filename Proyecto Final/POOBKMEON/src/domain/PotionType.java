package domain;

public enum PotionType {
    HYPER(200),
    SUPER(50),
    NORMAL(20);

    private final int value;

    PotionType(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }
}
