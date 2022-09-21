package com.example.Device.Management.cucumber.glue;

import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class CustomerCrudControllerStepTests {
    @Autowired
    private CucumberHelperComponent cucumberHelperComponent;

    @Autowired
    private RestTemplate restTemplate;

    private ResponseEntity responseEntity;
    private List<String> customerIds = new ArrayList<>();
    private String removedCUID;

    @When("I try to retrieve all the customers from an empty list")
    public void iTryToRetrieveAllTheCustomersFromAnEmptyList() {
        responseEntity = restTemplate.getForEntity(cucumberHelperComponent.getAppUrlPrefix() + "/customers", List.class);
    }

    @Then("I receive an empty list")
    public void iReceiveAnEmptyList() {
        assertEquals(new ArrayList<>(), responseEntity.getBody());
    }

}
