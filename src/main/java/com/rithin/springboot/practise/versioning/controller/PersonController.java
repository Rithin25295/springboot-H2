package com.rithin.springboot.practise.versioning.controller;


import com.rithin.springboot.practise.versioning.Name;
import com.rithin.springboot.practise.versioning.PersonV1;
import com.rithin.springboot.practise.versioning.PersonV2;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class PersonController {


    //Mapping versioning
    @GetMapping("/v1/person")
    public PersonV1 personV1(){
        return new PersonV1("Rithin");
    }

    @GetMapping("/v2/person")
    public PersonV2 personV2(){
        return new PersonV2(new Name("Rithin","Sai"));
    }


    //Param versioning
    @GetMapping(value = "/person/param",params = "version=1")
    public PersonV1 paramV1(){
        return new PersonV1("Rithin");
    }

    @GetMapping(value = "/person/param",params = "version=2")
    public PersonV2 paramV2(){
        return new PersonV2(new Name("Rithin","Sai"));
    }

    //Header versioning
    @GetMapping(value = "/person/header",headers = "X-API-VERSION=1")
    public PersonV1 headerV1(){
        return new PersonV1("Rithin");
    }

    @GetMapping(value = "/person/header",headers = "X-API-VERSION=2")
    public PersonV2 headerV2(){
        return new PersonV2(new Name("Rithin","Sai"));
    }

    //Produces or Accept Header versioning
    @GetMapping(value = "/person/produces", produces = "application/person-app-v1+json")
    public PersonV1 producesV1(){
        return new PersonV1("Rithin");
    }

    @GetMapping(value = "/person/produces",produces = "application/person-app-v2+json")
    public PersonV2 producesV2(){
        return new PersonV2(new Name("Rithin","Sai"));
    }
}
