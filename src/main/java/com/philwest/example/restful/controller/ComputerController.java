package com.philwest.example.restful.controller;

import com.philwest.example.restful.model.Computer;
import com.philwest.example.restful.service.ComputerService;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;


/**
 * the controller uses the service to work with instances of our model.
 */
@RestController
public class ComputerController {

    private ComputerService service;

    @Autowired
    public ComputerController(ComputerService computerService) {
        service = computerService;
    }

    @GetMapping("/computers/first")
    public Computer getFirstComputer() {
        return service.getFirstComputer();
    }

    @GetMapping("/computers/laptop")
    public Computer getLaptop() {
        return service.getLaptop();
    }

    @GetMapping("/computers/{name}")
    public Computer getComputerByName(@PathVariable String name) {
        return service.findByName(name);
    }

    @GetMapping("/computers")
    public List<Computer> getComputers() {
        return service.getAllComputers();
    }
}
