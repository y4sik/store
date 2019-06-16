package com.yasik.web.dto;

public interface GenericDTO<Entity, DtoEntity> {
    Entity convertToEntity(DtoEntity entity);

    DtoEntity convertToDto(Entity entity);
}
