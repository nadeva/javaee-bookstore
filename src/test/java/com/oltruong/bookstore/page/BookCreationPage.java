package com.oltruong.bookstore.page;

import net.codestory.simplelenium.PageObject;

/**
 * Created by nadeva on 05/10/2015.
 */
public class BookCreationPage implements PageObject {


    public void setName(String value) {
        find("#bookBeanBookName").fill(value);
    }

    public void setAuthor(String value) {
        find("#bookBeanBookAuthor").fill(value);
    }

    public void setISBN(String value) {
        find("#bookBeanBookIsbn").fill(value);
    }

    public void setDescription(String value) {
        find("#bookBeanBookDescription").fill(value);
    }

    public void setPrice(String value) {
        find("#bookBeanBookPrice").fill(value);
    }

    public void setPictureURL(String value) {
        find("#createbookBeanBookPictureURL").fill(value);
    }

    public void create() {
        find("button").withText("Save").click();
    }


    @Override
    public String url() {
        return "/newbook";
    }
}
