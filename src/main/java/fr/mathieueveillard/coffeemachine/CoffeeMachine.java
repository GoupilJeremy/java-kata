package fr.mathieueveillard.coffeemachine;

import java.util.ArrayList;
import java.util.Map;
import java.util.stream.Collectors;

public class CoffeeMachine {
    public interface Supplier {
        void order(String order);
    }

    public interface QuantityChecker {
        boolean isEmpty(String drink);
    }

    public interface Logger {
        void log(Drink drink, float profit);
    }

    private static Map<Drink, String> DRINK_TO_STRING = Map.of(Drink.COFFEE, "C", Drink.TEA, "T", Drink.CHOCOLATE, "H", Drink.ORANGE_JUICE, "O");

    private static Map<Drink, Float> PRICES = Map.of(Drink.COFFEE, .6F, Drink.TEA, .4F, Drink.CHOCOLATE, .5F, Drink.ORANGE_JUICE, .6F);

    private final Supplier supplier;

    private final QuantityChecker quantityChecker;

    private Logger logger;

    public CoffeeMachine(Supplier supplier, QuantityChecker quantityChecker) {
        this.supplier = supplier;
        this.quantityChecker = quantityChecker;
        this.logger = (drink, profit) -> {
        };
    }

    public void addLogger(Logger logger) {
        this.logger = logger;
    }

    public void order(Order order, float amountOfMoney) {
        final Drink drink = order.getDrink();
        final float change = computeChange(drink, amountOfMoney);
        if (change < 0) {
            this.forwardMessage("Not enough money, please insert " + formatAmountOfMoney(-change));
            return;
        }
        if (this.quantityChecker.isEmpty(drink.toString())) {
            this.forwardMessage("Sorry, there is not enough water or milk.");
            return;
        }
        this.placeEffectiveOrder(order);
    }

    public void forwardMessage(String message) {
        this.supplier.order("M:" + message);
    }

    private static float computeChange(Drink drink, float amountOfMoney) {
        return amountOfMoney - PRICES.get(drink);
    }

    private static String formatAmountOfMoney(float amount) {
        float roundedAmount = (float) Math.round(amount * 100) / 100;
        return roundedAmount + "€.";
    }

    private static String makeDrinkInstruction(Drink drink, boolean extraHot) {
        return DRINK_TO_STRING.get(drink) + (extraHot ? "h" : "");
    }

    private static String makeSugarInstruction(int numberOfSugars) {
        return numberOfSugars > 0 ? Integer.toString(numberOfSugars) : "";
    }

    private static String makeStickInstruction(int numberOfSugars) {
        return numberOfSugars > 0 ? "0" : "";
    }

    private void placeEffectiveOrder(Order order) {
        final Drink drink = order.getDrink();
        final ArrayList<String> parts = new ArrayList();
        parts.add(makeDrinkInstruction(drink, order.isExtraHot()));
        parts.add(makeSugarInstruction(order.getNumberOfSugars()));
        parts.add(makeStickInstruction(order.getNumberOfSugars()));
        final String instruction = parts.stream().collect(Collectors.joining(":"));
        this.supplier.order(instruction);
        this.logger.log(drink, PRICES.get(drink));
    }
}
