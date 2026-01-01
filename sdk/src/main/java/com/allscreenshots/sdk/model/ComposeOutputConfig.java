package com.allscreenshots.sdk.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Output configuration for composed screenshots.
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ComposeOutputConfig {

    @JsonProperty("layout")
    private LayoutType layout;

    @JsonProperty("format")
    private ImageFormat format;

    @JsonProperty("quality")
    private Integer quality;

    @JsonProperty("columns")
    private Integer columns;

    @JsonProperty("spacing")
    private Integer spacing;

    @JsonProperty("padding")
    private Integer padding;

    @JsonProperty("background")
    private String background;

    @JsonProperty("alignment")
    private Alignment alignment;

    @JsonProperty("maxWidth")
    private Integer maxWidth;

    @JsonProperty("maxHeight")
    private Integer maxHeight;

    @JsonProperty("thumbnailWidth")
    private Integer thumbnailWidth;

    @JsonProperty("labels")
    private LabelConfig labels;

    @JsonProperty("border")
    private BorderConfig border;

    @JsonProperty("shadow")
    private ShadowConfig shadow;

    public ComposeOutputConfig() {
    }

    private ComposeOutputConfig(Builder builder) {
        this.layout = builder.layout;
        this.format = builder.format;
        this.quality = builder.quality;
        this.columns = builder.columns;
        this.spacing = builder.spacing;
        this.padding = builder.padding;
        this.background = builder.background;
        this.alignment = builder.alignment;
        this.maxWidth = builder.maxWidth;
        this.maxHeight = builder.maxHeight;
        this.thumbnailWidth = builder.thumbnailWidth;
        this.labels = builder.labels;
        this.border = builder.border;
        this.shadow = builder.shadow;
    }

    public static Builder builder() {
        return new Builder();
    }

    public LayoutType getLayout() { return layout; }
    public ImageFormat getFormat() { return format; }
    public Integer getQuality() { return quality; }
    public Integer getColumns() { return columns; }
    public Integer getSpacing() { return spacing; }
    public Integer getPadding() { return padding; }
    public String getBackground() { return background; }
    public Alignment getAlignment() { return alignment; }
    public Integer getMaxWidth() { return maxWidth; }
    public Integer getMaxHeight() { return maxHeight; }
    public Integer getThumbnailWidth() { return thumbnailWidth; }
    public LabelConfig getLabels() { return labels; }
    public BorderConfig getBorder() { return border; }
    public ShadowConfig getShadow() { return shadow; }

    public static class Builder {
        private LayoutType layout;
        private ImageFormat format;
        private Integer quality;
        private Integer columns;
        private Integer spacing;
        private Integer padding;
        private String background;
        private Alignment alignment;
        private Integer maxWidth;
        private Integer maxHeight;
        private Integer thumbnailWidth;
        private LabelConfig labels;
        private BorderConfig border;
        private ShadowConfig shadow;

        public Builder layout(LayoutType layout) { this.layout = layout; return this; }
        public Builder format(ImageFormat format) { this.format = format; return this; }
        public Builder quality(int quality) { this.quality = quality; return this; }
        public Builder columns(int columns) { this.columns = columns; return this; }
        public Builder spacing(int spacing) { this.spacing = spacing; return this; }
        public Builder padding(int padding) { this.padding = padding; return this; }
        public Builder background(String background) { this.background = background; return this; }
        public Builder alignment(Alignment alignment) { this.alignment = alignment; return this; }
        public Builder maxWidth(int maxWidth) { this.maxWidth = maxWidth; return this; }
        public Builder maxHeight(int maxHeight) { this.maxHeight = maxHeight; return this; }
        public Builder thumbnailWidth(int thumbnailWidth) { this.thumbnailWidth = thumbnailWidth; return this; }
        public Builder labels(LabelConfig labels) { this.labels = labels; return this; }
        public Builder border(BorderConfig border) { this.border = border; return this; }
        public Builder shadow(ShadowConfig shadow) { this.shadow = shadow; return this; }

        public ComposeOutputConfig build() {
            return new ComposeOutputConfig(this);
        }
    }
}
