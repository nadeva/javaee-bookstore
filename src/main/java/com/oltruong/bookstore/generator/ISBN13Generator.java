package com.oltruong.bookstore.generator;


import com.oltruong.bookstore.generator.qualifier.ISBN13;
import org.apache.commons.lang3.RandomStringUtils;

import javax.enterprise.inject.Default;

@ISBN13
@Default
public class ISBN13Generator implements ISBNGenerator {
    @Override
    public String generateISBN() {
        return RandomStringUtils.randomNumeric(3)
                + "-" + RandomStringUtils.randomNumeric(1)
                + "-" + RandomStringUtils.randomNumeric(4)
                + "-" + RandomStringUtils.randomNumeric(4)
                + "-" + RandomStringUtils.randomNumeric(1);
    }
}
