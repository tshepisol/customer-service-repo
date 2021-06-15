package za.co.investec.cis.domain;

public enum Gender {

    MALE(0),
    FEMALE(1);

    Gender(int type) {
        this.type = type;
    }


    int type;

    public int getType() {
        return type;
    }
}
