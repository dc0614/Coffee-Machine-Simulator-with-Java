package coffeeTypes;

import java.util.HashMap;
import java.util.Map;
import java.util.logging.Logger;

import static java.lang.String.*;

public abstract class Coffee {
    protected Map<String, Integer> ingredients;
    protected int cost;
    public static int water;
    public static int milk;
    public static int coffeeBeans;
    public static int disposableCups;
    public static int money;

    public static final int CLEANING_THRESHOLD = 10;
    public static int cupsSinceCleaning = 0;

    static final String ENOUGH_RESOURCES = "I have enough resources, making you a coffee!";
    static final String WATER_I = "Water";
    static final String COFFEEBEANS_I = "Coffee Beans";

    static Logger logger = Logger.getLogger(Coffee.class.getName());

    protected Coffee() {
        this.ingredients = new HashMap<>();
    }

    public int getCost() {
        return cost;
    }

    public Map<String, Integer> getIngredients() {
        return ingredients;
    }



        public static void makeCoffee(int coffeeType) {
            if (!checkResources(coffeeType)) {
                return;
            }
            switch (coffeeType) {
                case 1:
                    water -= 250;
                    milk -= 0;
                    coffeeBeans -= 16;
                    disposableCups--;
                    money += 4;
                    logger.info(ENOUGH_RESOURCES);
                    cupsSinceCleaning++;
                    break;
                case 2:
                    water -= 350;
                    milk -= 75;
                    coffeeBeans -= 20;
                    disposableCups--;
                    money += 7;
                    logger.info(ENOUGH_RESOURCES);
                    cupsSinceCleaning++;
                    break;
                case 3:
                    water -= 200;
                    milk -= 100;
                    coffeeBeans -= 12;
                    disposableCups--;
                    money += 6;
                    logger.info(ENOUGH_RESOURCES);
                    cupsSinceCleaning++;
                    break;
                default:
                    logger.info("Invalid coffee type");
            }
        }


    private static boolean checkResources(int coffeeType) {
        switch (coffeeType) {
            case 1:
                return checkIndividualResource(WATER_I, water, 250) && checkIndividualResource(COFFEEBEANS_I, coffeeBeans, 16) && checkIndividualResource("Cups", disposableCups, 1);
            case 2:
                return checkIndividualResource(WATER_I, water, 350) && checkIndividualResource("Milk", milk, 75) && checkIndividualResource(COFFEEBEANS_I, coffeeBeans, 20) && checkIndividualResource("Cups", disposableCups, 1);
            case 3:
                return checkIndividualResource(WATER_I, water, 200) && checkIndividualResource("Milk", milk, 100) && checkIndividualResource(COFFEEBEANS_I, coffeeBeans, 12) && checkIndividualResource("Cups", disposableCups, 1);
            default:
                logger.info("Invalid coffee type");
                return false;
        }
    }

    private static boolean checkIndividualResource(String resourceName, int resourceAvailable, int resourceNeeded) {
        if (resourceAvailable >= resourceNeeded) {
            return true;
        } else {
            logger.info(String.format("Sorry, not enough %s!", resourceName));
            return false;
        }
    }
}

class Espresso extends Coffee {
    public Espresso() {
        super();
        this.ingredients.put(WATER_I, 250);
        this.ingredients.put(COFFEEBEANS_I, 16);
        this.cost = 4;
    }
}

class Latte extends Coffee {
    public Latte() {
        super();
        this.ingredients.put(WATER_I, 350);
        this.ingredients.put(COFFEEBEANS_I, 20);
        this.ingredients.put("Milk", 75);
        this.cost = 7;
    }
}

class Cappuccino extends Coffee {
    public Cappuccino() {
        super();
        this.ingredients.put(WATER_I, 200);
        this.ingredients.put(COFFEEBEANS_I, 12);
        this.ingredients.put("Milk", 100);
        this.cost = 6;
    }
}
