package com.oltruong.bookstore.cucumber;

import cucumber.api.CucumberOptions;
import cucumber.api.junit.Cucumber;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
@CucumberOptions(features = "classpath:features", format = {"pretty", "html:target/cucumber"})
public class RunCukesIT {
}
