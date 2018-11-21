package com.philwest.example.restful.model;

import lombok.Data;
import lombok.NonNull;

/**
 * a simple model class that contains a few attributes of a computer that might be interesting to track.
 */
@Data
public class Computer {

    @NonNull
    private final String name;

    @NonNull
    private final String cpuType;

    @NonNull
    private final Integer numberOfCPUs;

    @NonNull
    private final Integer memoryInMB;

    @NonNull
    private final Boolean isVirtual;

}
