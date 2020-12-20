package com.technoWeb.Tp.service.user;

import com.technoWeb.Tp.exception.NoContentException;
import com.technoWeb.Tp.exception.NotFoundException;
import com.technoWeb.Tp.exception.UnauthorizedException;
import com.technoWeb.Tp.exception.UserErrorMessages;
import com.technoWeb.Tp.model.User;
import com.technoWeb.Tp.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class UserService {

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private UserMapper userMapper;

    public List<User> findAll() {
        List<UserEntity> userEntities = userRepository.findAll();
        if (!userEntities.isEmpty())
            return userEntities.stream()
                    .map(userMapper::toModel)
                    .collect(Collectors.toList());
        else
            throw new NoContentException(UserErrorMessages.NO_USERS.name());
    }

    public User findById(long id) {
        return userRepository.findById(id).map(userMapper::toModel)
                .orElseThrow(() -> new NotFoundException(UserErrorMessages.USER_NOT_FOUND.name()));
    }

    public User create(User user) {
        UserEntity userEntity = userMapper.fromModel(user);
        long id = user.getId();
        if(id == 0) {
            userEntity.setId(id);
            userEntity.setPassword(
                    passwordEncoder.encode(
                            user.getPassword()
                    )
            );
            try {
                return userMapper.toModel(userRepository.save(userEntity));
            }
            catch(org.springframework.dao.DataIntegrityViolationException e)
            {
                throw new UnauthorizedException(UserErrorMessages.USER_NAME_DUPLICATED.name());
            }
        } else {
            throw new UnauthorizedException(UserErrorMessages.USER_ALREADY_EXIST.name());
        }
    }

    public User update(User user) {
        UserEntity userEntity = userMapper.fromModel(user);
        long id = user.getId();
        if(id != 0) {
            userEntity.setId(id);
            userEntity.setPassword(
                    passwordEncoder.encode(
                            user.getPassword()
                    )
            );
            try {
                return userMapper.toModel(userRepository.save(userEntity));
            }
            catch(org.springframework.dao.DataIntegrityViolationException e)
            {
                throw new UnauthorizedException(UserErrorMessages.USER_NAME_DUPLICATED.name());
            }
        } else {
            throw new UnauthorizedException(UserErrorMessages.NEW_USER.name());
        }
    }


    public User delete(long id) {
        Optional<UserEntity> userEntityOptional = userRepository.findById(id);
        if (userEntityOptional.isPresent()) {
            UserEntity userEntity = userEntityOptional.get();
            userRepository.deleteById(id);
            return userMapper.toModel(userEntity);
        } else
            throw new NotFoundException(UserErrorMessages.USER_NOT_FOUND.name());
    }


}
