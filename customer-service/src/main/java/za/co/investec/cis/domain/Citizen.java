package za.co.investec.cis.domain;

public enum Citizen {
    SA(0),
    PERM(1);

    Citizen(int type) {
        this.type = type;
    }


    int type;

    public int getType() {
        return type;
    }
}

