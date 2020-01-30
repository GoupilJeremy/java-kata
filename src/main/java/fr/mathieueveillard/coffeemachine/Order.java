package fr.mathieueveillard.coffeemachine;

public class Order {
    private Drink drink;
    private boolean extraHot = false;
    private int numberOfSugars = 0;

    public Order(Drink drink) {
        this.drink = drink;
    }

    public Order extraHot() {
        this.extraHot = true;
        return this;
    }

    public Order withSugar(int numberOfSugars) {
        this.numberOfSugars = numberOfSugars;
        return this;
    }

    public Drink getDrink() {
        return drink;
    }

    public boolean isExtraHot() {
        return this.extraHot;
    }

    public int getNumberOfSugars() {
        return numberOfSugars;
    }
}
