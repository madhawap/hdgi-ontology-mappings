package com.hdgi.app.models;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel(description = "Details about a pose")
public class Pose {
    @ApiModelProperty(notes = "The name of the pose", example = "Right_ForearmPose_1")
    private String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
