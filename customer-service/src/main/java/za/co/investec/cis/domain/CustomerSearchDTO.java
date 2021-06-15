package za.co.investec.cis.domain;

public class CustomerSearchDTO {


    private String firstName;
    private String idNumber;
    private String mobileNumber;

    public CustomerSearchDTO(String name, String idNumber, String phone) {
        this.firstName = name;
        this.idNumber = idNumber;
        this.mobileNumber = phone;
    }


    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getIdNumber() {
        return idNumber;
    }

    public void setIdNumber(String idNumber) {
        this.idNumber = idNumber;
    }

    public String getMobileNumber() {
        return mobileNumber;
    }

    public void setMobileNumber(String mobileNumber) {
        this.mobileNumber = mobileNumber;
    }

    @Override
    public String toString() {
        return "CustomerSearchDTO{" +
                "firstName='" + firstName + '\'' +
                ", idNumber='" + idNumber + '\'' +
                ", mobileNumber='" + mobileNumber + '\'' +
                '}';
    }
}
