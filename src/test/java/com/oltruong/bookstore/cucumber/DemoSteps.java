package com.oltruong.bookstore.cucumber;

import org.assertj.core.data.Offset;

import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;

import static org.assertj.core.api.Assertions.assertThat;

public class DemoSteps {

    private double bookPrice;

    @Given("Un livre avec comme prix (\\d+) euros")
    public void initData(double bookPrice) throws Exception {
        this.bookPrice = bookPrice;
    }

    @When("Une réduction de (\\d+)% est appliquée")
    public void applyDiscount(double discountPercent) throws Throwable {
        this.bookPrice *= 1-(discountPercent/100);
    }

    @Then("Le livre coûte (\\d+) euros")
    public void checkPrice(double expectedPrice) throws Exception {
        assertThat(bookPrice).isCloseTo(expectedPrice, Offset.offset(0.009));
    }

}
