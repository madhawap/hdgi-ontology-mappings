package com.hdgi.app.controller;

import com.hdgi.app.models.Pose;
import io.swagger.annotations.*;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

@RestController
@RequestMapping("/pose")
@Api(value="Basic Pose Information APIs", tags = "Basic Pose Information")
public class PoseDataController {

    ConcurrentMap<String, Pose> poses = new ConcurrentHashMap<>();

    @PostMapping("/new")
    @ApiOperation(value = "Add a new pose instance to hdgi-ontology",
            notes = "Please follow the pose model when creating a gesture",
            response = Pose.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Successfully added the pose"),
            @ApiResponse(code = 401, message = "You are not authorized to view this resource"),
            @ApiResponse(code = 403, message = "Accessing the resource you were trying to reach is forbidden"),
            @ApiResponse(code = 404, message = "The resource you were trying to reach is not found") })
    public Pose addGesture(@ApiParam(value = "Pose model object", required = true)
                           @RequestBody Pose pose){
        poses.put(pose.getName(), pose);
        return pose;
    }

}
