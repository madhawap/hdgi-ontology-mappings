package com.hdgi.app.models;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel(description = "Details about a gesture")
public class Gesture {
    @ApiModelProperty(notes = "The unique id of the gesture", example = "0001")
    private String id;
    @ApiModelProperty(notes = "The name of the gesture", example = "Right hand swipe")
    private String name;
    @ApiModelProperty(notes = "The category which the gesture belongs to", example = "Hand Gesture")
    private String category;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

}
