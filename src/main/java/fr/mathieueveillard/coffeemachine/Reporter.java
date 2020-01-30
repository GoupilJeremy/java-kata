package fr.mathieueveillard.coffeemachine;

import java.util.HashMap;

public class Reporter implements CoffeeMachine.Logger {

    private final HashMap<Drink, Integer> amounts = new HashMap();

    private float turnover = 0;

    public Reporter() {
        this.amounts.put(Drink.COFFEE, 0);
        this.amounts.put(Drink.TEA, 0);
        this.amounts.put(Drink.CHOCOLATE, 0);
    }

    @Override
    public void log(Drink drink, float profit) {
        this.amounts.replace(drink, this.amounts.get(drink) + 1);
        this.turnover += profit;
    }

    public int getNumberOfOrders(Drink drink) {
        return this.amounts.get(drink);
    }

    public float getTurnover() {
        return this.turnover;
    }
}
