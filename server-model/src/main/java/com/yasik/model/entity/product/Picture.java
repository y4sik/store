package com.yasik.model.entity.product;

import javax.persistence.*;

@Entity
@Table(name = "picture")
public class Picture {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private long id;

    @Column(name = "picture_path")
    private String picturePath;

    public Picture(){}

    public Picture(String picturePath) {
        this.picturePath = picturePath;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getPicturePath() {
        return picturePath;
    }

    public void setPicturePath(String picturePath) {
        this.picturePath = picturePath;
    }

    @Override
    public String toString() {
        return "Picture{" +
                "id=" + id +
                ", picturePath='" + picturePath + '\'' +
                '}';
    }
}
