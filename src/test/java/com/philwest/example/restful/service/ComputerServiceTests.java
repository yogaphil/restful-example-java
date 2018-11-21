package com.philwest.example.restful.service;

import com.philwest.example.restful.controller.ComputerController;
import com.philwest.example.restful.model.Computer;

import java.util.LinkedList;
import java.util.List;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.skyscreamer.jsonassert.JSONAssert;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

/** perform tests of the ComputerService class. */
@RunWith(SpringRunner.class)
@WebMvcTest(useDefaultFilters = false)
public class ComputerServiceTests {

    private MockMvc mvc;

    @MockBean
    private ComputerService computerService;

    @Before
    public void setup() {
        this.mvc = MockMvcBuilders.standaloneSetup(new ComputerController(computerService)).build();
    }

    private Computer getMockComputer() {
        return new Computer("Neptune", "Intel Xeon E5-2670 v0", 2,
                65535, Boolean.FALSE);
    }

    private List<Computer> getMockResultList() {
        List<Computer> result = new LinkedList<>();

        for (int i = 0; i < 3; i++) {
            result.add(getMockComputer());
        }

        return result;
    }

    private String getExpectedJSONforMockComputer() {
        return "{name:Neptune,cpuType:\"Intel Xeon E5-2670 v0\",numberOfCPUs:2,memoryInMB:65535,isVirtual:false}";
    }

    @Test
    public void canRequestFirstComputer() throws Exception {

        Mockito.when(computerService.getFirstComputer()).thenReturn(getMockComputer());

        RequestBuilder requestBuilder = MockMvcRequestBuilders.get("/computers/first")
                .accept(MediaType.APPLICATION_JSON);
        MvcResult result = mvc.perform(requestBuilder).andReturn();

        Assert.assertEquals(200, result.getResponse().getStatus());
        JSONAssert.assertEquals(getExpectedJSONforMockComputer(),
                result.getResponse().getContentAsString(), false);
    }

    @Test
    public void canRequestLaptop() throws Exception {

        Mockito.when(computerService.getLaptop()).thenReturn(getMockComputer());

        RequestBuilder requestBuilder = MockMvcRequestBuilders.get("/computers/laptop")
                .accept(MediaType.APPLICATION_JSON);
        MvcResult result = mvc.perform(requestBuilder).andReturn();

        Assert.assertEquals(200, result.getResponse().getStatus());
        JSONAssert.assertEquals(getExpectedJSONforMockComputer(), result.getResponse().getContentAsString(), false);
    }

    @Test
    public void canRequestByName() throws Exception {

        Mockito.when(computerService.findByName(Mockito.anyString())).thenReturn(getMockComputer());

        RequestBuilder requestBuilder = MockMvcRequestBuilders.get("/computers/computerName")
                .accept(MediaType.APPLICATION_JSON);
        MvcResult result = mvc.perform(requestBuilder).andReturn();

        Assert.assertEquals(200, result.getResponse().getStatus());
        JSONAssert.assertEquals(getExpectedJSONforMockComputer(), result.getResponse().getContentAsString(), false);
    }

    @Test
    public void canRequestAll() throws Exception {

        Mockito.when(computerService.getAllComputers()).thenReturn(getMockResultList());

        RequestBuilder requestBuilder = MockMvcRequestBuilders.get("/computers")
                .accept(MediaType.APPLICATION_JSON);
        MvcResult result = mvc.perform(requestBuilder).andReturn();

        Assert.assertEquals(200, result.getResponse().getStatus());
    }
}
