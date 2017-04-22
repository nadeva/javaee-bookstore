package com.oltruong.bookstore.page;

import net.codestory.simplelenium.PageObject;

/**
 * Created by nadeva on 05/10/2015.
 */
public class BookSearchPage implements PageObject {



    public void checkTitle(){
        find("h1").withText("Welcome librarian").should().exist();
    }

    @Override
    public String url() {
        return "/books";
    }
}
