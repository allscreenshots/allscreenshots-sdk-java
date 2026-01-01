package com.allscreenshots.sdk.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;
import java.util.Map;

/**
 * Response from a layout preview request.
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class LayoutPreviewResponse {

    @JsonProperty("layout")
    private String layout;

    @JsonProperty("resolvedLayout")
    private String resolvedLayout;

    @JsonProperty("canvasWidth")
    private Integer canvasWidth;

    @JsonProperty("canvasHeight")
    private Integer canvasHeight;

    @JsonProperty("placements")
    private List<PlacementPreview> placements;

    @JsonProperty("metadata")
    private Map<String, Object> metadata;

    public LayoutPreviewResponse() {
    }

    public String getLayout() { return layout; }
    public String getResolvedLayout() { return resolvedLayout; }
    public Integer getCanvasWidth() { return canvasWidth; }
    public Integer getCanvasHeight() { return canvasHeight; }
    public List<PlacementPreview> getPlacements() { return placements; }
    public Map<String, Object> getMetadata() { return metadata; }
}
