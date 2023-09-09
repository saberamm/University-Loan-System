package util;

import dto.DtoMapper;
import dto.impl.DtoMapperImpl;
import jakarta.persistence.EntityManager;
import repository.*;
import repository.impl.*;
import service.*;
import service.impl.*;

import java.util.Scanner;

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

    //------------ CreditCard application context ------------------//

    private static final CreditCardService creditCardService;

    static {
        CreditCardRepository creditCardRepository = new CreditCardRepositoryImpl(entityManager);
        creditCardService = new CreditCardServiceImpl(creditCardRepository);
    }

    public static CreditCardService getCreditCardService() {
        return creditCardService;
    }

    //------------ HouseInfo application context ------------------//

    private static final HouseInfoService houseInfoService;

    static {
        HouseInfoRepository houseInfoRepository = new HouseInfoRepositoryImpl(entityManager);
        houseInfoService = new HouseInfoServiceImpl(houseInfoRepository);
    }

    public static HouseInfoService getHouseInfoService() {
        return houseInfoService;
    }

    //------------ Installment application context ------------------//

    private static final InstallmentService installmentService;

    static {
        InstallmentRepository installmentRepository = new InstallmentRepositoryImpl(entityManager);
        installmentService = new InstallmentServiceImpl(installmentRepository);
    }

    public static InstallmentService getInstallmentService() {
        return installmentService;
    }

    //------------ Loan application context ------------------//

    private static final LoanService loanService;

    static {
        LoanRepository loanRepository = new LoanRepositoryImpl(entityManager);
        loanService = new LoanServiceImpl(loanRepository);
    }

    public static LoanService getLoanService() {
        return loanService;
    }


    //------------ Student application context ------------------//

    private static final StudentService studentService;

    static {
        StudentRepository studentRepository = new StudentRepositoryImpl(entityManager);
        studentService = new StudentServiceImpl(studentRepository);
    }

    public static StudentService getStudentService() {
        return studentService;
    }


    //------------ University application context ------------------//

    private static final UniversityService universityService;

    static {
        UniversityRepository universityRepository = new UniversityRepositoryImpl(entityManager);
        universityService = new UniversityServiceImpl(universityRepository);
    }

    public static UniversityService getUniversityService() {
        return universityService;
    }
}