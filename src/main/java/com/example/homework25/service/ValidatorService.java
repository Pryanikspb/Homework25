package com.example.homework25.service;

import com.example.homework25.exception.IncorrectNameException;
import com.example.homework25.exception.IncorrectSurnameException;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

@Service
public class ValidatorService {

    public String validateFirstname(String firstname) {
        if (!StringUtils.isAlpha(firstname)) {
            throw new IncorrectNameException();
        }
        return StringUtils.capitalize(firstname.toLowerCase());
    }

    public String validateLastname(String lastname) {
        String[] surnames = lastname.split("-");
        for (int i = 0; i < surnames.length; i++) {
            String s = surnames[i];
            if (!StringUtils.isAlpha(s)) {
                throw new IncorrectSurnameException();
            }
            surnames[i] = StringUtils.capitalize(s.toLowerCase());
        }
        return String.join("-", surnames);
    }


}
