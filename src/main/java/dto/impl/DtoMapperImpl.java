package dto.impl;

import dto.DtoMapper;
import entity.User;
import entity.entitydto.SimpleUser;

public class DtoMapperImpl implements DtoMapper {
    @Override
    public SimpleUser userDtoMapper(User user) {
        if (user == null) {
            return null;
        }
        SimpleUser simpleUser = new SimpleUser();
        simpleUser.setFirstname(user.getFirstName());
        simpleUser.setLastname(user.getLastName());
        simpleUser.setUsername(user.getUsername());
        simpleUser.setBirthDate(user.getBirthDate());

        return simpleUser;
    }
}
