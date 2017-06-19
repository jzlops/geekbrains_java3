package ru.tikhonov.term2.ui;

import ru.tikhonov.term2.db.ProductTableOps;

import java.util.Scanner;

/**
 * Edit by Tikhonov Sergey
 * Консольный интерфейс
 */
public class UI {
    private ProductTableOps ops;

    public UI(ProductTableOps ops) {
        this.ops = ops;
    }

    public void dialog() {
        Scanner scanner = new Scanner(System.in);
        String command;
        while (scanner.hasNext()) {
            command = scanner.next();
            if ("/exit".equals(command)) {
                break;
            }
            if ("/cost".equals(command.toLowerCase())) {
                String prodTitle = scanner.next();
                System.out.printf("%s%n", ops.getCostByTitle(prodTitle));
                System.out.printf("exit %s%n", command);
            }

            if ("/changecost".equals(command.toLowerCase())) {
                try {
                    String prodTitle = scanner.next();
                    float prodCost = scanner.nextFloat();
                    System.out.printf("%s%n", ops.setCostByTitle(prodTitle, prodCost));
                    System.out.printf("exit %s%n", command);
                } catch (RuntimeException e) {
                    System.out.printf("Unrecognised argument %n exit %s%n",command);
                    scanner.nextLine();
                }
            }
            if ("/costbyrange".equals(command.toLowerCase())) {
                try {
                    float beginCost = scanner.nextFloat();
                    float endCost = scanner.nextFloat();
                    System.out.printf("%s%n", ops.printProductByCostRange(beginCost, endCost));
                    System.out.printf("exit %s%n", command);
                } catch (RuntimeException e) {
                    System.out.printf("Unrecognised argument %n exit %s%n",command);
                    scanner.nextLine();
                }
            }
        }
    }
}
