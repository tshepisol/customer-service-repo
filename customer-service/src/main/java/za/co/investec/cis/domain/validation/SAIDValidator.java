package za.co.investec.cis.domain.validation;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import za.co.investec.cis.domain.Customer;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;

public class SAIDValidator implements ConstraintValidator<SAID, Customer> {

    private static final Logger log = LoggerFactory.getLogger(SAIDValidator.class);
    private static final SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyMMdd");


    @Override
    public boolean isValid(Customer customer, ConstraintValidatorContext constraintValidatorContext) {


        String id = customer.getIdNumber();
        log.info("id:" + id);

        String birthdayString = id.substring(0, 6);
        log.info("birthday:" + birthdayString);


        try {
            Date birthday = simpleDateFormat.parse(birthdayString);
            if (birthday.after(new Date()))
                return false;
        } catch (ParseException e) {
            log.error("failed to arse date", e);
            return false;

        }

        if (customer.getGender() != null) {
            String gender = id.substring(6, 10);
            log.info("gender:" + gender);

            if (Long.valueOf(gender) >= 5000) {
                if (customer.getGender().getType() != 0)
                    return false;
            } else if (customer.getGender().getType() == 1)
                return false;

        }

        if (customer.getCitizenship() != null) {
            String citizen = id.substring(10, 11);
            log.info("citizen:" + citizen);
            if (customer.getCitizenship().getType() != Integer.valueOf(citizen))
                return false;
        }

        return isValidIDChecksum(id);

    }

    private boolean isValidIDChecksum(String idNumber) {
        log.info("isValidIDChecksum:" + idNumber);

        int[] idArray = new int[idNumber.length()];

        for (int i = 0; i < idNumber.length(); i++) {
            char c = idNumber.charAt(i);
            idArray[i] = Integer.parseInt("" + c);
        }

        for (int i = idArray.length - 2; i >= 0; i = i - 2) {
            int num = idArray[i];
            num = num * 2;  // step 1
            if (num > 9) {
                num = num % 10 + num / 10;  // step 2
            }
            idArray[i] = num;
        }

        int sum = sumDigits(idArray);  // step 3


        return sum % 10 == 0;

    }

    private int sumDigits(int[] arr) {
        return Arrays.stream(arr).sum();
    }
}
