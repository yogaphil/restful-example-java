package com.philwest.example.restful.service;

import com.philwest.example.restful.model.Computer;
import java.util.List;
import org.springframework.lang.Nullable;


public interface ComputerService {
    List<Computer> getAllComputers();

    Computer getFirstComputer();

    Computer getLaptop();

    @Nullable
    Computer findByName(String name);
}
