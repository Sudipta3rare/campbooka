package com.threeraredyn.campbooka.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;


@Entity
@Table(name="media")
public class Media {
    @Id
    private int id;
    private int pic1;
    private int pic2;
    
    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }
    public int getPic1() {
        return pic1;
    }
    public void setPic1(int pic1) {
        this.pic1 = pic1;
    }
    public int getPic2() {
        return pic2;
    }
    public void setPic2(int pic2) {
        this.pic2 = pic2;
    }
    public int getPic3() {
        return pic3;
    }
    public void setPic3(int pic3) {
        this.pic3 = pic3;
    }
    private int pic3;
}
