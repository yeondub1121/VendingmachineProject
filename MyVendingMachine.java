package packageEx;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class MyVendingMachine implements VendingMachineInterface {

    private int profit;
    private List<Product> productList;
    private Admin manager;
    private int productCount;
    private int totalSales;

    public MyVendingMachine() {
        profit = 0;
        productList = new ArrayList<>();
        totalSales = 0;

        Scanner scanner = new Scanner(System.in);

        System.out.println("-------------------------");
        System.out.println("[VendingMachine]");
        System.out.println("-------------------------");
        System.out.println("관리자를 생성합니다.");
        String idString;
        int id;
        do {
            System.out.print("새로운 id를 생성하세요(숫자만 생성 가능): ");
            idString = scanner.nextLine();

            if (!idString.matches("\\d+")) {
                System.out.println("아이디는 숫자로 입력해야 합니다.");
            }
        } while (!idString.matches("\\d+"));

        id = Integer.parseInt(idString);
        
        System.out.print("새로운 pw를 생성하세요(숫자,영문_대소문자 생성 가능):");
        String pw = scanner.nextLine();

        manager = new Admin();
        manager.setAdmin(id, pw);

        System.out.println("새로운 관리자 id: " + id);
        System.out.println("pw: " + getMaskedPassword(pw));

    }

    private String getMaskedPassword(String password) {
        StringBuilder maskedPassword = new StringBuilder();
        for (int i = 0; i < password.length(); i++) {
            maskedPassword.append("*");
        }
        return maskedPassword.toString();
    }

    @Override
    public void setProduct() {
        // 생략
    }

    @Override
    public int menuPrint() {
        // 생략
        return 0;
    }

    @Override
    public int calSum(int selNo, int num) {
        // 생략
        return 0;
    }

    @Override
    public void adminMenu() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("관리자 권환이 있어야 하는 메뉴입니다.");
        String idString;
        int id;
        do {
            System.out.print("id: ");
            idString = scanner.nextLine();
            if (!idString.matches("\\d+")) {
                System.out.println("아이디는 숫자입니다.");
            }
        } while (!idString.matches("\\d+"));

        id = Integer.parseInt(idString);

        System.out.print("pw: ");
        String pw = scanner.nextLine();

        if (manager.checkAdmin(id, pw)) {
            System.out.println("관리자 인증 성공");
            int choice;

            do {
                System.out.println("1번: 제품 등록");
                System.out.println("2번: 매출 확인");
                System.out.println("3번: 전체 제품정보 확인");
                System.out.println("4번: 재고 확인");
                System.out.println("5번: 재고 추가");
                System.out.println("6번: 이전 기능");
                System.out.print("--> ");
                String input = scanner.nextLine();

                if (input.matches("\\d+")) {
                    choice = Integer.parseInt(input);

                    switch (choice) {
                    case 1:
                        System.out.println("몇 개의 제품을 입력하시겠습니까?");
                        String productCountInput;
                        int productToRegister;
                        do {
                            productCountInput = scanner.nextLine();
                            if (!productCountInput.matches("\\d+")) {
                                System.out.println("문자를 입력하셨습니다. 숫자를 입력하세요.");
                            }
                        } while (!productCountInput.matches("\\d+"));

                        productToRegister = Integer.parseInt(productCountInput);
                        for (int i = 0; i < productToRegister; i++) {
                            System.out.println("******" + (productCount + i + 1) + "번째 제품******");
                            System.out.println("제품 이름: ");
                            String productName = scanner.nextLine();

                            System.out.println("제품 가격: ");
                            String priceInput;
                            do {
                                priceInput = scanner.nextLine();
                                if (!priceInput.matches("\\d+")) {
                                    System.out.println("문자를 입력하셨습니다. 숫자를 입력하세요.");
                                }
                            } while (!priceInput.matches("\\d+"));
                            int price = Integer.parseInt(priceInput);

                            System.out.println("제품 재고: ");
                            String stockInput;
                            do {
                                stockInput = scanner.nextLine();
                                if (!stockInput.matches("\\d+")) {
                                    System.out.println("문자를 입력하셨습니다. 숫자를 입력하세요.");
                                }
                            } while (!stockInput.matches("\\d+"));
                            int stock = Integer.parseInt(stockInput);

                            productList.add(new Product(productName, price, stock));
                        }

                        productCount += productToRegister;
                        break;
                        case 2:
                            System.out.println("매출 확인");
                            System.out.println("현재 총 매출액: " + totalSales+"원");
                            break;
                        case 3:
                            int totalProductCount = 0;
                            if (productList != null && !productList.isEmpty()) {
                                System.out.println("######################");
                                for (Product product : productList) {
                                    if (product.getStock() > 0) {
                                        System.out.println("제품 이름: " + product.getProductName());
                                        System.out.println("제품 가격: " + product.getPrice() + "원");
                                        System.out.println("제품 재고: " + product.getStock() + "개");
                                        System.out.println();
                                        totalProductCount++;
                                    }
                                }
                                System.out.println("총 " + totalProductCount + "개의 제품이 있습니다.");
                                System.out.println("######################");
                            } else {
                                System.out.println("######################");
                                System.out.println("총 0개의 상품이 있습니다.");
                                System.out.println("######################");
                            }
                            break;
                        case 4:
                           
                            System.out.print("어떤 상품의 재고를 확인할까요? ");
                            String productName = scanner.nextLine();

                            // 등록된 상품인지 확인
                            boolean productExists = false;
                            int productIndex = -1;
                            for (int i = 0; i < productList.size(); i++) {
                                if (productList.get(i).getProductName().equals(productName)) {
                                    productExists = true;
                                    productIndex = i;
                                    break;
                                }
                            }

                            if (productExists) {
                                System.out.println(productName + " 재고: " + productList.get(productIndex).getStock()+"개");
                            } else {
                                System.out.println("없는 상품입니다.");
                            }
                            break;
                        case 5:
                            // 재고 추가 기능
                        	System.out.println("재고 추가");
                            System.out.println("어떤 상품의 재고를 추가할까요?(제품이름)");
                            String productNameToStock = scanner.nextLine();

                            // 등록된 상품인지 확인
                            boolean productExistsToAddStock = false;
                            int productIndexToAddStock = -1;
                            for (int i = 0; i < productList.size(); i++) {
                                if (productList.get(i).getProductName().equals(productNameToStock)) {
                                    productExistsToAddStock = true;
                                    productIndexToAddStock = i;
                                    break;
                                }
                            }

                            if (productExistsToAddStock) {
                                System.out.println("몇 개 추가할까요?");
                                String stockToAddInput;
                                do {
                                    stockToAddInput = scanner.nextLine();
                                    if (!stockToAddInput.matches("\\d+")) {
                                        System.out.println("문자를 입력하셨습니다. 숫자를 입력하세요.");
                                    }
                                } while (!stockToAddInput.matches("\\d+"));
                                int stockToAdd = Integer.parseInt(stockToAddInput);

                                int currentStock = productList.get(productIndexToAddStock).getStock();
                                productList.get(productIndexToAddStock).setStock(currentStock + stockToAdd);
                                
                                System.out.println(productNameToStock+" 상품 총 재고: " + (productList.get(productIndexToAddStock).getStock())+ "개");
                            } else {
                            	System.out.println("몇 개 추가할까요?");
                                String stockToAddInput;
                                stockToAddInput = scanner.nextLine();
                                System.out.println("없는 상품입니다.");
                            }
                            break;
                        case 6:
                           
                            break;
                        default:
                            System.out.println("유효하지 않은 메뉴 번호입니다.");
                            break;
                    }
                } else {
                    System.out.println("문자를 입력하셨습니다.");
                    choice = 0; // 잘못된 입력을 처리하기 위해 choice를 0으로 초기화
                }
            } while (choice != 6);
        } else {
            System.out.println("관리자 권한이 없습니다.");
        }
    }

    @Override
    public void purchaseProduct(Scanner scanner) {
        if (productList.isEmpty()) {
            System.out.println("등록된 상품이 없습니다.");
            return;
        }
        
        System.out.println("구매 할 제품의 번호를 입력해주세요.");
        int count = 0;
        for (int i = 0; i < productList.size(); i++) {
            Product product = productList.get(i);
            if (product.getStock() > 0) {
                System.out.println((count + 1)+". "+ product.getProductName()+"  "+ product.getPrice()+"원" +"  "+product.getStock()+"개" );
                count++;
            }
        }

        if (count == 0) {
            System.out.println("구매 가능한 제품이 없습니다.");
            return;
        }

        System.out.print("--> ");
        int productNumber = scanner.nextInt();
        scanner.nextLine(); // 개행 문자 처리

        if (productNumber > 0 && productNumber <= count) {
            count = 0;
            for (Product product : productList) {
                if (product.getStock() > 0) {
                    count++;
                    if (count == productNumber) {
                        Product selectedProduct = product;

                        System.out.print("수량: ");
                        int quantity = scanner.nextInt();
                        scanner.nextLine(); // 개행 문자 처리

                        if (selectedProduct.getStock() >= quantity) {
                            int totalPrice = selectedProduct.getPrice() * quantity;
                            System.out.println(selectedProduct.getProductName() + "을(를) " + quantity + "개 선택하셨습니다.");
                            System.out.println("총 금액: " + totalPrice);

                            selectedProduct.decreaseStock(quantity);
                            totalSales += totalPrice;
                        } else {
                        	System.out.println(selectedProduct.getProductName() + "을(를) " + quantity + "개 선택하셨습니다.");
                            System.out.println("재고가 부족합니다.");
                        }
                        return;
                    }
                }
            }
        }

        System.out.println("유효하지 않은 제품 번호입니다.");
    }


    public static void main(String[] args) {
        MyVendingMachine vendingMachine = new MyVendingMachine();

        Scanner scanner = new Scanner(System.in);
        int choice;

        do {
            System.out.println("1번: 제품 구매");
            System.out.println("2번: 관리자");
            System.out.println("3번: 종료");
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
                    break;
            }
        } while (choice != 3);
        scanner.close();
    }

	@Override
	public void checkStock(String name) {
		// TODO Auto-generated method stub
		
	}

	
	
	@Override
	public void addStock(String name, int theNbOfPrct) {
	    // TODO: Implement the logic to add stock to a product
	}
}