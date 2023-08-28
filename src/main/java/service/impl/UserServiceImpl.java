package service.impl;

import base.service.impl.BaseServiceImpl;
import dto.DtoMapper;
import entity.User;
import entity.entitydto.SimpleUser;
import repository.UserRepository;
import service.UserService;

public class UserServiceImpl extends BaseServiceImpl<User, Long, UserRepository> implements UserService {

    public DtoMapper dtoMapper;

    public UserServiceImpl(UserRepository repository, DtoMapper dtoMapper) {
        super(repository);
        this.dtoMapper = dtoMapper;
    }

    @Override
    public SimpleUser findUserByUsername(String username) {
        return dtoMapper.userDtoMapper(repository.findUserByUsername(username));
    }

    @Override
    public User userAuthentication(String user_name, String password) {
        return repository.userAuthentication(user_name, password);
    }
}
