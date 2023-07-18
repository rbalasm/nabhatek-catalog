package com.nabhatek.catalogservice;

import com.nabhatek.catalogservice.config.CatalogProperties;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.net.InetAddress;
import java.net.UnknownHostException;

@RestController
public class HomeController {

    private final CatalogProperties catalogProperties;
    private final String ip;
    private final String hostname;

    public HomeController(CatalogProperties catalogProperties) {
        this.catalogProperties = catalogProperties;
        try {
            ip = InetAddress.getLocalHost().toString();
            hostname = InetAddress.getLocalHost().getHostName();
        } catch (UnknownHostException e) {
            throw new RuntimeException(e);
        }
    }

    @GetMapping("/")
    public String getGreeting() {
        return String.format("%s -> From IP [%s] : [%s]", catalogProperties.getGreeting(), ip, hostname);
    }
}
