package com.TestRunner;

import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;

import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Parameters;

@CucumberOptions(
        features = "src/test/resources/Features",
        glue = {"com.Hooks","com.StepDefinitions"},
        tags = "@test",
        plugin = {"pretty", "html:target/spark-reports/cucumber.html"},
        monochrome =true
)



public  class TestNgCucumberRunner extends AbstractTestNGCucumberTests {
	

    @Override
    @DataProvider(parallel = true)
    public Object[][] scenarios() {
        return super.scenarios();
    }
    
  }
