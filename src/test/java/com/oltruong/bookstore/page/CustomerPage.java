package com.oltruong.bookstore.page;

import net.codestory.simplelenium.PageObject;
import org.apache.commons.lang3.RandomUtils;

/**
 * Created by nadeva on 05/10/2015.
 */
public class CustomerPage implements PageObject {


    public void buyRandom(int value) {
        find("button").withText("Buy").nth(RandomUtils.nextInt(1, value)).click();
    }

    public void setName(String value) {
        find("#bookBeanBookName").fill(value);
    }

    public void setAddress(String value) {
        find("#bookBeanBookAuthor").fill(value);
    }

    public void sendOrder() {
        find("button").withText("Send order").click();
    }

    @Override
    public String url() {
        return "/customer";
    }
}
