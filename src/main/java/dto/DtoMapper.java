package dto;

import entity.User;
import entity.entitydto.SimpleUser;

public interface DtoMapper {
    SimpleUser userDtoMapper(User user);
}
