package util;

import dto.DtoMapper;
import dto.impl.DtoMapperImpl;
import jakarta.persistence.EntityManager;
import repository.UserRepository;
import repository.impl.UserRepositoryImpl;
import service.UserService;
import service.impl.UserServiceImpl;

public class ApplicationContext {
    static EntityManager entityManager = HibernateUtils.getEntityManagerFactory().createEntityManager();

    //------------ user application context ------------------//

    private static final UserService userService;

    static {
        DtoMapper dtoMapper = new DtoMapperImpl();
        UserRepository userRepository = new UserRepositoryImpl(entityManager);
        userService = new UserServiceImpl(userRepository, dtoMapper);
    }

    public static UserService getUserService() {
        return userService;
    }
}