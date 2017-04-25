package com.oltruong.bookstore.generator;


import com.oltruong.bookstore.generator.qualifier.ISBN10;
import org.apache.commons.lang3.RandomStringUtils;

@ISBN10
public class ISBN10Generator implements ISBNGenerator {

    @Override
    public String generateISBN() {
        return RandomStringUtils.randomNumeric(1)
                + "-" + RandomStringUtils.randomNumeric(4)
                + "-" + RandomStringUtils.randomNumeric(4)
                + "-" + RandomStringUtils.randomNumeric(1);
    }
}
