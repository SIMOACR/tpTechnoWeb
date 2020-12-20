package com.technoWeb.Tp.service.series;

import com.technoWeb.Tp.model.SeriesWithUserName;
import com.technoWeb.Tp.repository.UserRepository;
import com.technoWeb.Tp.service.mapper.Mapper;
import com.technoWeb.Tp.service.user.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class SeriesWithUserNameMapper implements Mapper<SeriesWithUserName, SeriesEntity> {

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private UserRepository userRepository;

    @Override
    public SeriesWithUserName toModel(SeriesEntity entity) {
        return new SeriesWithUserName(
                entity.getId(),
                entity.getTitle(),
                entity.getDescription(),
                entity.isPublicAccess(),
                entity.getUserEntity().getUserName()
        );
    }

    @Override
    public SeriesEntity fromModel(SeriesWithUserName model) {
        return new SeriesEntity(
                model.getId(),
                model.getTitle(),
                model.getDescription(),
                model.isPublicAccess(),
                userRepository.findByUserName(model.getUserName()).get()
        );
    }
}
