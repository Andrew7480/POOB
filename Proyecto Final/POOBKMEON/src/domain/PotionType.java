package domain;

public enum PotionType {
    HYPER_DEFENSE(200),
    SUPER_DEFENSE(50),
    HYPER_ATTACK(200),
    SUPER_ATTACK(50),
    HYPER_PS(200),
    SUPER_PS(50),
    HYPER(200),
    SUPER(50);

    private final int value;

    PotionType(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }
}
