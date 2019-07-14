package com.yasik.service;

import com.yasik.model.entity.product.Picture;

public interface PictureService {

    Picture addPictureForProduct(Picture picture);

    long deletePicture(long id);

}
