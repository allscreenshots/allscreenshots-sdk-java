package com.allscreenshots.sdk.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Preview of a placement in a compose layout.
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class PlacementPreview {

    @JsonProperty("index")
    private Integer index;

    @JsonProperty("x")
    private Integer x;

    @JsonProperty("y")
    private Integer y;

    @JsonProperty("width")
    private Integer width;

    @JsonProperty("height")
    private Integer height;

    @JsonProperty("label")
    private String label;

    public PlacementPreview() {
    }

    public Integer getIndex() { return index; }
    public Integer getX() { return x; }
    public Integer getY() { return y; }
    public Integer getWidth() { return width; }
    public Integer getHeight() { return height; }
    public String getLabel() { return label; }
}
