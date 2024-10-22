package machine;

import coffeeTypes.Coffee;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.logging.Logger;

import static coffeeTypes.Coffee.*;

public class CoffeeMachine {

    static Logger logger = Logger.getLogger(CoffeeMachine.class.getName());
    public CoffeeMachine() {
        setInitialState();
    }

    public static void main(String[] args) {

        CoffeeMachine coffeeMachine = new CoffeeMachine();
        displayOptions(new Scanner(System.in));
    }

    public static void setInitialState(){
       Coffee.water = 400;
       Coffee.milk = 540;
       Coffee.coffeeBeans = 120;
       Coffee.disposableCups = 9;
       Coffee.money = 550;
    }

    public static void displayOptions(Scanner scanner) {
        List<String> options = new ArrayList<>();
        options.add("buy");
        options.add("fill");
        options.add("take");
        options.add("clean");
        options.add("remaining");
        options.add("exit");

        String userChoice = "";

       while (!userChoice.equals(options.get(5))) {
           logger.info("\n\nWrite action (buy, fill, take, clean, remaining, exit):");

           userChoice = scanner.next();
           switch(userChoice) {
               case "buy":
                   if (Coffee.cupsSinceCleaning >= Coffee.CLEANING_THRESHOLD) {
                       logger.info("I need cleaning!");
                       break;
                   }
                   logger.info("\nWhat do you want to buy? 1 - espresso, 2 - latte, 3 - cappuccino, back - to main menu:");
                   boolean valid = false;
                   while (!valid) {
                       if (scanner.hasNextInt()) {
                           int coffeeType = scanner.nextInt();
                           makeCoffee(coffeeType);
                           valid = true;
                       } else {
                           String input = scanner.next();
                           if ("back".equalsIgnoreCase(input)) {
                               valid = true;  // will break the loop if 'back' is entered
                           } else {
                               logger.info("Invalid input. Please select an option from the menu.");
                           }
                       }
                   }
                   break;
               case "fill":
                   fillMachine(scanner);
                   break;
               case "take":
                   logger.info("I gave you $" + Coffee.money);
                   Coffee.money = 0;
                   break;
               case "clean":
                   performCleaning();
                   Coffee.cupsSinceCleaning = 0;
                   break;
               case "remaining":
                   displayCurrentState();
                   break;
               case "exit":
                   return;
               default:
                   logger.info("Invalid option");
           }
       }

    }

    private static void fillMachine(Scanner scanner) {
        logger.info("Write how many ml of water you want to add:");
        Coffee.water += scanner.nextInt();
        logger.info("Write how many ml of milk you want to add:");
        Coffee.milk += scanner.nextInt();
        logger.info("Write how many grams of coffee beans you want to add:");
        Coffee.coffeeBeans += scanner.nextInt();
        logger.info("Write how many disposable cups you want to add:");
        Coffee.disposableCups += scanner.nextInt();
    }

    public static void displayCurrentState(){
        logger.info("\nThe coffee machine has:");
        logger.info(Coffee.water + " ml of water");
        logger.info(Coffee.milk + " ml of milk");
        logger.info(Coffee.coffeeBeans + " g of coffee beans");
        logger.info(Coffee.disposableCups + " disposable cups");
        logger.info("$" + Coffee.money + " of money");
    }

    private static void performCleaning() {
        logger.info("I have been cleaned!");
    }

}