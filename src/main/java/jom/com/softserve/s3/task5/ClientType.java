package jom.com.softserve.s3.task5;

enum ClientType {
    NEW(1),
    SILVER(12),
    GOLD(30),
    PLATINUM(60);

    private int months;


    ClientType(int months) {
        this.months = months;
    }

    public double discount() {

        return switch (months) {
            case 1 -> 1.0;
            case 12 -> (100 - months * 0.25) / 100;
            case 30 -> (100 - months * 0.3) / 100;
            case 60 -> (100 - months * 0.35) / 100;

            default -> 0.0;
        };
    }

}