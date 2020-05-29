package com.rithin.springboot.practise.controller.filtering;


import com.fasterxml.jackson.databind.ser.FilterProvider;
import com.fasterxml.jackson.databind.ser.impl.SimpleBeanPropertyFilter;
import com.fasterxml.jackson.databind.ser.impl.SimpleFilterProvider;
import org.springframework.http.converter.json.MappingJacksonValue;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Arrays;
import java.util.List;

@RestController
public class FilteringController {


    @GetMapping("/filtering")
    public MappingJacksonValue retrieveSomeBean(){
        SomeBean someBean =  new SomeBean("value1", "value2","value3");

        //What to filter
        SimpleBeanPropertyFilter filter = SimpleBeanPropertyFilter.filterOutAllExcept("field1","field2");

        //give the filter to filter provider
        FilterProvider filters = new SimpleFilterProvider().addFilter("someBeanFilter",filter);

        //Map the object to get filtered
        MappingJacksonValue mapper = new MappingJacksonValue(someBean);

        //Apply the filter from filter provider
        mapper.setFilters(filters);

        //return the filtered bean
        return mapper;
    }

    @GetMapping("/filtering-list")
    public MappingJacksonValue retrieveListOfBeans(){
        List<SomeBean> list = Arrays.asList(new SomeBean("value1", "value2","value3"),
                new SomeBean("value4","value5","value6"));

        SimpleBeanPropertyFilter filter = SimpleBeanPropertyFilter.filterOutAllExcept("field2","field3");

        FilterProvider filters = new SimpleFilterProvider().addFilter("someBeanFilter",filter);

        MappingJacksonValue mapper = new MappingJacksonValue(list);

        mapper.setFilters(filters);

        return mapper;
    }
}
