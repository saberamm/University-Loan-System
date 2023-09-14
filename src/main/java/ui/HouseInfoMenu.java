package ui;

import entity.HouseInfo;
import entity.Student;
import util.ApplicationContext;
import validation.TypeValidator;

import static ui.UserMenu.scanner;

public class HouseInfoMenu {
    public static void addHouse(Student student) {
        HouseInfo houseInfo = new HouseInfo();
        System.out.print("Enter the house rent number :");
        houseInfo.setRentNumber(TypeValidator.getDigitString(12));
        System.out.print("Enter your house address :");
        houseInfo.setHouseAddress(scanner.next());
        scanner.nextLine();
        while (!ApplicationContext.getHouseInfoService().isValid(houseInfo)) {
            System.out.print("Enter the house rent number :");
            houseInfo.setRentNumber(TypeValidator.getDigitString(12));
            System.out.print("Enter your house address :");
            houseInfo.setHouseAddress(scanner.next());
            scanner.nextLine();
        }
        student.setHouseInfo(houseInfo);
    }
}
