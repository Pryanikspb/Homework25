package com.example.homework25.service;

import com.example.homework25.exception.IncorrectNameException;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

@Service
public class ValidatorService {

    public String validate (String param) {
        if (!StringUtils.isAlpha(param)) {
            throw new IncorrectNameException();
        } return StringUtils.capitalize(param.toLowerCase()) ;
    }
}
