package com.technoWeb.Tp.service.mapper;

public interface Mapper<M, E> {
    M toModel(E entity);
    E fromModel(M model);
}
