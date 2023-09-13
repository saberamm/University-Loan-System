package ui;

import entity.HouseInfo;
import entity.Student;
import util.ApplicationContext;

import static ui.UserMenu.scanner;

public class HouseInfoMenu {
    public static void addHouse(Student student) {
        HouseInfo houseInfo = new HouseInfo();
        System.out.print("Enter the house rent number :");
        houseInfo.setRentNumber(scanner.next());
        System.out.print("Enter your house address :");
        houseInfo.setHouseAddress(scanner.next());
        while (!ApplicationContext.getHouseInfoService().isValid(houseInfo)) {
            System.out.print("Enter the house rent number :");
            houseInfo.setRentNumber(scanner.next());
            System.out.print("Enter your house address :");
            houseInfo.setHouseAddress(scanner.next());
        }
        student.setHouseInfo(houseInfo);
    }
}
