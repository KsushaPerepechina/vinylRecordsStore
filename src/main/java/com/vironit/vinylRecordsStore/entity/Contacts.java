package com.vironit.vinylRecordsStore.entity;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Parameter;

/**
 * Контактные данные пользователя.
 */
@Entity
@Table(name = "contacts")
public class Contacts implements Serializable {

    @Id
    @Column(name = "id", unique = true, nullable = false)
    @GeneratedValue(generator = "gen")
    @GenericGenerator(name = "gen", strategy = "foreign",
            parameters = @Parameter(name = "property", value = "account"))
    private Long id;

    @OneToOne
    @PrimaryKeyJoinColumn
    private Account account;

    @Column(name = "phone", nullable = false)
    private String phone;

    @Column(name = "address", nullable = false)
    private String address;

    @Column(name = "city_region", nullable = false)
    private String cityAndRegion;

    public Contacts() {
    }

    public Contacts(Account account, String phone, String address/*, String cityAndRegion*/) {
        this.account = account;
        this.phone = phone;
        this.address = address;
        this.cityAndRegion = "13";
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
     * @return the account
     */
    public Account getAccount() {
        return account;
    }

    /**
     * @param account the account to set
     */
    public void setAccount(Account account) {
        this.account = account;
    }

    /**
     * @return the phone
     */
    public String getPhone() {
        return phone;
    }

    /**
     * @param phone the phone to set
     */
    public void setPhone(String phone) {
        this.phone = phone;
    }

    /**
     * @return the address
     */
    public String getAddress() {
        return address;
    }

    /**
     * @param address the address to set
     */
    public void setAddress(String address) {
        this.address = address;
    }

    /**
     * @return the cityAndRegion
     */
    public String getCityAndRegion() {
        return cityAndRegion;
    }

    /**
     * @param cityAndRegion the cityAndRegion to set
     */
    public void setCityAndRegion(String cityAndRegion) {
        this.cityAndRegion = cityAndRegion;
    }

}
