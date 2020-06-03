package com.hdgi.app.controller;

import com.hdgi.app.models.Affordance;
import io.swagger.annotations.*;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

@RestController
@RequestMapping("/affordance")
@Api(value="Basic Pose Information APIs", tags = "Basic Affordance Information")
public class AffordanceDataController {

    ConcurrentMap<String, Affordance> affordances = new ConcurrentHashMap<>();

    @PostMapping("/new")
    @ApiOperation(value = "Add a new affordance instance to hdgi-ontology",
            notes = "Please follow the affordance model when creating a gesture",
            response = Affordance.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Successfully added the affordance"),
            @ApiResponse(code = 401, message = "You are not authorized to view this resource"),
            @ApiResponse(code = 403, message = "Accessing the resource you were trying to reach is forbidden"),
            @ApiResponse(code = 404, message = "The resource you were trying to reach is not found") })
    public Affordance addGesture(@ApiParam(value = "Affordance model object", required = true)
                                 @RequestBody Affordance affordance){
        affordances.put(affordance.getName(), affordance);
        return affordance;
    }
}
