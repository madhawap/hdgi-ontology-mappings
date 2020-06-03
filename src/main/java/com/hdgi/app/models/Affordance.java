package com.hdgi.app.models;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel(description = "Details about a affordance")
public class Affordance {
    @ApiModelProperty(notes = "The name of the affordance", example = "Go to previous channel")
    private String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
