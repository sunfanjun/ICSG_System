package com.zjut.springbootprj.pojo;

import lombok.Data;

@Data
public class DetectResult {
    private double xmin;
    private double xmax;
    private double ymin;
    private double ymax;
    private String name;
    private double confidence;

}
