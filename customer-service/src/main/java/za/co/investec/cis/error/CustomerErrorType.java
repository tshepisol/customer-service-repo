package za.co.investec.cis.error;

public class CustomerErrorType {
    private String errorMessage;

    public CustomerErrorType(String errorMessage) {
        this.errorMessage = errorMessage;
    }

    public String getErrorMessage() {
        return errorMessage;
    }
}
