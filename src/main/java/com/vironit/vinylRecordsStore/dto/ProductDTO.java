package com.vironit.vinylRecordsStore.dto;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import org.springframework.hateoas.ResourceSupport;

/**
 * Адаптер товара.
 */
public class ProductDTO extends ResourceSupport {
    
    private long productId;

    private String style;

    @Pattern(regexp = "^[^#$%^&*()']*$")
    private String artist;

    @Pattern(regexp = "^[^#$%^&*()']*$")
    private String album;

    @Pattern(regexp = "^[^#$%^&*()']*$")
    private String country;

    @NotNull
    private Integer year;

    @NotNull
    private Double price;

    @NotNull
    private Integer volume;

    @NotNull
    private boolean inStock;

    public ProductDTO() {
    }

    public ProductDTO(String style, String artist, String album, String country, int year, double price) {
        this.style = style;
        this.artist = artist;
        this.album = album;
        this.country = country;
        this.year = year;
        this.price = price;
    }

    /**
     * @return the id
     */
    public long getProductId() {
        return productId;
    }

    /**
     * @param productId the id to set
     */
    public void setProductId(long productId) {
        this.productId = productId;
    }

    /**
     * @return the artist
     */
    public String getArtist() {
        return artist;
    }

    /**
     * @param artist the artist to set
     */
    public void setArtist(String artist) {
        this.artist = artist;
    }

    public String getAlbum() {
        return album;
    }

    public void setAlbum(String album) {
        this.album = album;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public Integer getYear() {
        return year;
    }

    public void setYear(Integer year) {
        this.year = year;
    }

    /**
     * @return the price
     */
    public Double getPrice() {
        return price;
    }

    /**
     * @param price the price to set
     */
    public void setPrice(Double price) {
        this.price = price;
    }

    /**
     * @return the volume
     */
    public Integer getVolume() {
        return volume;
    }

    /**
     * @param volume the volume to set
     */
    public void setVolume(Integer volume) {
        this.volume = volume;
    }

    /**
     * @return the style
     */
    public String getStyle() {
        return style;
    }

    /**
     * @param style the style to set
     */
    public void setStyle(String style) {
        this.style = style;
    }

    public boolean isInStock() {
        return inStock;
    }

    public void setInStock(boolean inStock) {
        this.inStock = inStock;
    }
}
