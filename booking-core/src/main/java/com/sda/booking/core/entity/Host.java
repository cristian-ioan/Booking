package com.sda.booking.core.entity;

import com.sda.booking.core.base.BaseEntity;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "hosts", schema = "booking")
public class Host extends BaseEntity {

    @Column(name = "name", length = 40, nullable = false)
    private String name;

    @Column(name = "email", length = 40, nullable = false)
    private String eMail;

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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Host)) return false;
        Host host = (Host) o;
        return Objects.equals(name, host.name) &&
                Objects.equals(eMail, host.eMail);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, eMail);
    }

    @Override
    public String toString() {
        return "Host{" +
                "name='" + name + '\'' +
                ", eMail='" + eMail + '\'' +
                '}';
    }

}
