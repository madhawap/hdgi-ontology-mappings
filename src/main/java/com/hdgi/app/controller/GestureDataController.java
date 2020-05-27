package com.hdgi.app.controller;

import com.hdgi.app.models.Gesture;
import io.swagger.annotations.*;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

@RestController
@RequestMapping("/gesture")
@Api(value="Basic Gesture Information APIs", tags = "Basic Gesture Information")
public class GestureDataController {

    ConcurrentMap<String, Gesture> gestures = new ConcurrentHashMap<>();

    @GetMapping("/{gestureId}")
    @ApiOperation(value = "Find Gestures by gesture ID",
            notes = "Provide a gesture ID if you already know them",
            response = Gesture.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Successfully retrieved a gesture"),
            @ApiResponse(code = 401, message = "You are not authorized to view this resource"),
            @ApiResponse(code = 403, message = "Accessing the resource you were trying to reach is forbidden"),
            @ApiResponse(code = 404, message = "The resource you were trying to reach is not found") })
    public Gesture getGesture(@ApiParam(value = "ID value for the gesture you need to retrieve", required = true)
                              @PathVariable String gestureId) {
        return gestures.get(gestureId);
    }

    @GetMapping("/")
    @ApiOperation(value = "Find all the freely available gestures",
            response = List.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Successfully retrieved the free gesture list"),
            @ApiResponse(code = 401, message = "You are not authorized to view this resource"),
            @ApiResponse(code = 403, message = "Accessing the resource you were trying to reach is forbidden"),
            @ApiResponse(code = 404, message = "The resource you were trying to reach is not found") })
    public List<Gesture> getAllGestures(){
        return new ArrayList<Gesture>(gestures.values());
    }

    @PostMapping("/new")
    @ApiOperation(value = "Add a new gesture instance to hdgi-ontology",
            notes = "Please follow the gesture model when creating a gesture",
            response = Gesture.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Successfully added the gesture"),
            @ApiResponse(code = 401, message = "You are not authorized to view this resource"),
            @ApiResponse(code = 403, message = "Accessing the resource you were trying to reach is forbidden"),
            @ApiResponse(code = 404, message = "The resource you were trying to reach is not found") })
    public Gesture addGesture(@ApiParam(value = "Gesture model object", required = true)
                              @RequestBody Gesture gesture){
        gestures.put(gesture.getId(), gesture);
        return gesture;
    }
}
