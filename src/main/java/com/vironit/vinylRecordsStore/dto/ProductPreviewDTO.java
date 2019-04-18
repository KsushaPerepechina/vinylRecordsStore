package com.vironit.vinylRecordsStore.dto;

import org.springframework.hateoas.ResourceSupport;

/**
 * Адаптер товара.
 */
public class ProductPreviewDTO extends ResourceSupport {
    
    private long productId;
    private String style;
    private String artist;
    private String album;
    private Double price;

    public ProductPreviewDTO() {
    }

    public long getProductId() {
        return productId;
    }

    public void setProductId(long productId) {
        this.productId = productId;
    }

    public String getStyle() {
        return style;
    }

    public void setStyle(String style) {
        this.style = style;
    }

    public String getArtist() {
        return artist;
    }

    public void setArtist(String artist) {
        this.artist = artist;
    }

    public String getAlbum() {
        return album;
    }

    public void setAlbum(String album) {
        this.album = album;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }
}
