package com.yasik.service.impl;

import com.yasik.dao.PictureDAO;
import com.yasik.dao.ProductDAO;
import com.yasik.model.entity.product.Picture;
import com.yasik.model.entity.product.Product;
import com.yasik.model.graph.GraphType;
import com.yasik.service.PictureService;
import com.yasik.service.exception.EmptyObjectException;
import com.yasik.service.exception.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Objects;

@Service
public class PictureServiceImpl implements PictureService {

    @Autowired
    private PictureDAO pictureDAO;

    @Autowired
    private ProductDAO productDAO;

    @Override
    @Transactional
    public Picture addPictureForProduct(Picture picture) {
        Product currentProduct = picture.getProduct();
        if (currentProduct != null) {
            long productId = currentProduct.getId();
            if (!Objects.equals(productId, null)) {
                Product product = productDAO.getById(productId, GraphType.PURE_ENTITY);
                if (product == null) {
                    throw new EntityNotFoundException("Can't add Picture. There is no Product with " +
                            "id [" + currentProduct.getId() + "]!");
                }
                return pictureDAO.persist(picture);
            }
        }
        throw new EmptyObjectException("Picture does't contain Product ID!");
    }

    @Override
    @Transactional
    public long deletePicture(long id) {
        Picture picture = pictureDAO.getById(id, GraphType.PURE_ENTITY);
        if (picture == null) {
            throw new EntityNotFoundException("Can't delete Picture. Invalid Id [" + id + "]!");
        }
        pictureDAO.remove(picture);
        return id;
    }
}
