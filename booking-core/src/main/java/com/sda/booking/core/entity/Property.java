package com.sda.booking.core.entity;

import com.sda.booking.core.base.BaseEntity;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "properties", schema = "booking")
public class Property extends BaseEntity {

    @Column(name = "name", length = 40, nullable = false)
    private String name;

    @ManyToOne
    @JoinColumn(name = "host_id")
    private Host host;

    @Column(name = "email", length = 40, nullable = false)
    private String eMail;

    @Column(name = "phone", length = 40, nullable = false)
    private String phone;

    @Column(name = "address", length = 40, nullable = false)
    private String address;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Host getHost() {
        return host;
    }

    public void setHost(Host host) {
        this.host = host;
    }

    public String geteMail() {
        return eMail;
    }

    public void seteMail(String eMail) {
        this.eMail = eMail;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Property)) return false;
        Property property = (Property) o;
        return Objects.equals(getName(), property.getName()) &&
                Objects.equals(getHost(), property.getHost()) &&
                Objects.equals(geteMail(), property.geteMail()) &&
                Objects.equals(getPhone(), property.getPhone()) &&
                Objects.equals(getAddress(), property.getAddress());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getName(), getHost(), geteMail(), getPhone(), getAddress());
    }

    @Override
    public String toString() {
        return "Property{" +
                "name='" + name + '\'' +
                ", host=" + host +
                ", eMail='" + eMail + '\'' +
                ", phone='" + phone + '\'' +
                ", address='" + address + '\'' +
                '}';
    }
}
