package com.sda.booking.core.entity;

import com.sda.booking.core.base.BaseEntity;

import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "hosts", schema = "booking")
public class Host extends BaseEntity {

}
