package packageEx;
import java.util.Scanner;



public class MainUi {
	public static void main(String[] args) {
        MyVendingMachine vendingMachine = new MyVendingMachine();
        
        Scanner scanner = new Scanner(System.in);
        int choice;
        
        do {
            System.out.println("1. 제품 구매");
            System.out.println("2. 관리자");
            System.out.println("3. 종료");
            System.out.print("--> ");
            choice = scanner.nextInt();
            
            switch (choice) {
                case 1:
                	vendingMachine.purchaseProduct(scanner);
                    break;
                case 2:
                    vendingMachine.adminMenu();
                    break;
                case 3:
                    System.out.println("프로그램을 종료합니다.");
                    break;
                default:
                    System.out.println("잘못된 메뉴 선택입니다. 다시 선택해주세요.");
            }
            
        } while (choice != 3);
        
        scanner.close();
    }
}