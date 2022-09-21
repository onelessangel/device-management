package com.example.Device.Management.cucumber.glue;

import com.example.Device.Management.DeviceManagementApplication;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.cucumber.spring.CucumberContextConfiguration;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.server.LocalServerPort;


@Slf4j
@Getter
@SpringBootTest(classes = {DeviceManagementApplication.class},
        webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@CucumberContextConfiguration
public class CucumberHelperComponent {

    @Autowired
    private ObjectMapper objectMapper;

    @LocalServerPort
    private int appPort;

    @Value("${server.servlet.context-path:}")
    private String appContextPath;

    public String getAppUrlPrefix() {
        return "http://localhost:" + getAppPort() + getAppContextPath();
    }
}

