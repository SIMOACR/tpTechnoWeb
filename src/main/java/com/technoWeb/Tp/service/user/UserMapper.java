package com.technoWeb.Tp.service.user;

import com.technoWeb.Tp.model.User;
import com.technoWeb.Tp.service.mapper.Mapper;

public class UserMapper implements Mapper<User, UserEntity> {

    @Override
    public User toModel(UserEntity entity) {
        return new User(
                entity.getId(),
                entity.getUserName(),
                entity.getPassword(),
                entity.getFirstName(),
                entity.getLastName()
        );
    }

    @Override
    public UserEntity fromModel(User model) {
        return new UserEntity(
                model.getId(),
                model.getUserName(),
                model.getPassword(),
                model.getFirstName(),
                model.getLastName()
        );
    }
}
