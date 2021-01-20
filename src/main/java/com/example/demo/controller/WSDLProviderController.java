package com.example.demo.controller;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/wsdl")
public class WSDLProviderController {

    @Value("classpath:odm.wsdl")
    private Resource wsdlResource;

    @Value("${RULES_ENDPOINT}")
    private String endpoint;

    @GetMapping
    @ResponseBody
    public ResponseEntity<String> serveFile() throws IOException {
        String wsdlString = new String(wsdlResource.getInputStream().readAllBytes());
        String wsdlStringWithEndpoint = wsdlString.replace("{{RULES_ENDPOINT}}", endpoint);
        System.out.println("WSDL pronto: " + wsdlStringWithEndpoint);
        return ResponseEntity.ok().contentType(MediaType.TEXT_XML).body(wsdlStringWithEndpoint);
    }
}
