package com.hdgi.app.models;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.util.TreeMap;

@ApiModel(description = "Details about a gesture")
public class Gesture {

    @ApiModelProperty(notes = "The name of the gesture", example = "Right hand swipe")
    private String name;
    @ApiModelProperty(notes = "The category which the gesture belongs to", example = "Hand Gesture")
    private String gestureCategory;
    @ApiModelProperty(notes = "Details of the requested gesture")
    private TreeMap<String, String> gestureDetails = new TreeMap<>();

//    TODO Consider adding a unique ID for a gesture
//    @ApiModelProperty(notes = "The unique id of the gesture", example = "0001")
//    private String id;

//    public String getId() {
//        return id;
//    }
//
//    public void setId(String id) {
//        this.id = id;
//    }

    public TreeMap<String, String> getGestureDetails() {
        return gestureDetails;
    }

    public void setGestureDetails(TreeMap<String, String> gestureDetails) {
        this.gestureDetails = gestureDetails;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getGestureCategory() {
        return gestureCategory;
    }

    public void setGestureCategory(String gestureCategory) {
        this.gestureCategory = gestureCategory;
    }

}
