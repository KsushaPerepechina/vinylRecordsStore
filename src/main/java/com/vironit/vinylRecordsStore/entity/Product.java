package com.vironit.vinylRecordsStore.entity;

import java.io.Serializable;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

/**
 * Товар.
 */
@Entity
@Table(name = "product")
public class Product implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", insertable = false, updatable = false, nullable = false)
    private Long id;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "style_id", nullable = false)
    private Style style;

    @OneToOne(mappedBy = "product", cascade = CascadeType.ALL)
    private Storage storage;

    @Column(name = "artist", nullable = false)
    @Pattern(regexp = "^[^#$%^&*()']*$")
    private String artist;

    @Column(name = "album", nullable = false)
    @Pattern(regexp = "^[^#$%^&*()']*$")
    private String album;

    @Column(name = "country", nullable = false)
    @Pattern(regexp = "^[^#$%^&*()']*$")
    private String country;

    @Column(name = "year", nullable = false)
    @NotNull
    private Integer year;

    @Column(name = "price", nullable = false)
    @NotNull
    private Double price;

    @Column(name = "volume")
    @NotNull
    private Integer volume;

    public Product() {
    }

    public Product(Style style, String artist, String album, String country, int year, double price) {
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
    public Long getId() {
        return id;
    }

    /**
     * @param id the id to set
     */
    public void setId(Long id) {
        this.id = id;
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
     * @return the storage
     */
    public Storage getStorage() {
        return storage;
    }

    /**
     * @param storage the storage to set
     */
    public void setStorage(Storage storage) {
        this.storage = storage;
    }

    /**
     * @return the style
     */
    public Style getStyle() {
        return style;
    }

    /**
     * @param style the style to set
     */
    public void setStyle(Style style) {
        this.style = style;
    }
}
