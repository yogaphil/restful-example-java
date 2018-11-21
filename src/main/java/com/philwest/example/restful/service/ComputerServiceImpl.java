package com.philwest.example.restful.service;

import com.philwest.example.restful.model.Computer;

import java.util.LinkedList;
import java.util.List;

import org.springframework.lang.Nullable;
import org.springframework.util.StringUtils;

/**
 * this service retrieves instances of Computers.  initial implementation just holds a few examples in
 * memory, but later this may be expanded to pull from MongoDB or maybe use JPA or some other persistence
 * mechanism.
 */
public class ComputerServiceImpl implements ComputerService {
    /* to keep things simple, just creating some sample objects to work with rather than
       integrating with a datastore of some type.
     */
    private static final Computer first = new Computer("Apple II+",
            "Motorola 6502", 1, 0, Boolean.FALSE);
    private static final Computer laptop = new Computer("2011 17in MacBook Pro",
            "Intel Core i7 2760QM", 1, 16384, Boolean.FALSE);

    /** returns a list of all computers currently stored in the system. */
    @Override
    public List<Computer> getAllComputers() {
        // this is obviously not ideal, just temporarily providing some data until
        // connected to something better like MongoDB
        List<Computer> result = new LinkedList<>();
        result.add(first);
        result.add(laptop);
        return result;
    }

    /** returns my first computer. */
    @Override
    public Computer getFirstComputer() {
        return first;
    }

    /** returns my recently deceased laptop. */
    @Override
    public Computer getLaptop() {
        return laptop;
    }

    /** returns null or the computer with the matching name if it exists in the system. */
    @Override
    @Nullable
    public Computer findByName(String name) {
        if (StringUtils.isEmpty(name)) {
            return null;
        }
        // once connected to Mongo or something, use appropriate search methods...
        for (Computer c : getAllComputers()) {
            if (c.getName().equals(name)) {
                return c;
            }
        }
        return null;
    }
}
