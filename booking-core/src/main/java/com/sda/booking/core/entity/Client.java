package com.sda.booking.core.entity;

import com.sda.booking.core.base.BaseEntity;
import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "clients", schema = "booking")
public class Client extends BaseEntity {

    @Column(name = "name", length = 40, nullable = false)
    private String name;

    @Column(name = "email", length = 40, nullable = false)
    private String eMail;

    @Column(name = "phone", length = 40, nullable = false)
    private String phone;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Client)) return false;
        Client client = (Client) o;
        return Objects.equals(getName(), client.getName()) &&
                Objects.equals(geteMail(), client.geteMail()) &&
                Objects.equals(getPhone(), client.getPhone());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getName(), geteMail(), getPhone());
    }

    @Override
    public String toString() {
        return "Client{" +
                "name='" + name + '\'' +
                ", eMail='" + eMail + '\'' +
                ", phone='" + phone + '\'' +
                '}';
    }
}
