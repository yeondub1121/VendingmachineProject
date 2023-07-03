package packageEx;

import java.util.Scanner;

public interface VendingMachineInterface {
    void setProduct();
    int menuPrint();
    void adminMenu();
    int calSum(int selNo, int num);
    void checkStock(String name);
    void addStock(String name, int theNbOfPrct);
	void purchaseProduct(Scanner scanner);
	
}