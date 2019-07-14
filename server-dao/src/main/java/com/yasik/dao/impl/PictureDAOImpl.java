package com.yasik.dao.impl;

import com.yasik.dao.PictureDAO;
import com.yasik.model.entity.product.Picture;
import org.springframework.stereotype.Repository;

@Repository
public class PictureDAOImpl extends GenericDAOImpl<Picture> implements PictureDAO {
    public PictureDAOImpl() {
        super(Picture.class);
    }
}
