package com.example.controller;

import com.example.domain.JobsProperty;
import com.example.domain.lvip.LvipProperty;
import com.example.server.PropertyService;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RestController
public class TestController {

    @Resource
    private PropertyService propertyService;

    @RequestMapping("/lvip/{proName}")
    public LvipProperty getLvipProperty(@PathVariable("proName") String proName) {
        return propertyService.getLvipProperty(proName);
    }

    @RequestMapping("/jobs/{proName}")
    public JobsProperty getJobsProperty(@PathVariable("proName") String proName) {
        return propertyService.getJobsProperty(proName);
    }

    @RequestMapping("/update/{proName}/{vlaue}")
    public String updateProperty(@PathVariable("proName") String proName, @PathVariable("vlaue") String vlaue) {
        propertyService.updateProperty(proName, vlaue);
        return "update success";
    }

}
