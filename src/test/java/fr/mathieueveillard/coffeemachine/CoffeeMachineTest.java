package fr.mathieueveillard.coffeemachine;

import org.junit.jupiter.api.Test;

import static org.mockito.Mockito.*;

public class CoffeeMachineTest {

    @Test
    public void should_allow_to_order_coffee() {
        final CoffeeMachine.Supplier supplier = mock(CoffeeMachine.Supplier.class);
        final CoffeeMachine.QuantityChecker quantityChecker = mock(CoffeeMachine.QuantityChecker.class);
        final CoffeeMachine coffeeMachine = new CoffeeMachine(supplier, quantityChecker);
        final Order order = new Order(Drink.COFFEE);
        coffeeMachine.order(order, .6F);
        verify(supplier).order("C::");
    }

    @Test
    public void should_allow_to_order_tea() {
        final CoffeeMachine.Supplier supplier = mock(CoffeeMachine.Supplier.class);
        final CoffeeMachine.QuantityChecker quantityChecker = mock(CoffeeMachine.QuantityChecker.class);
        final CoffeeMachine coffeeMachine = new CoffeeMachine(supplier, quantityChecker);
        final Order order = new Order(Drink.TEA);
        coffeeMachine.order(order, .4F);
        verify(supplier).order("T::");
    }

    @Test
    public void should_allow_to_order_chocolate() {
        final CoffeeMachine.Supplier supplier = mock(CoffeeMachine.Supplier.class);
        final CoffeeMachine.QuantityChecker quantityChecker = mock(CoffeeMachine.QuantityChecker.class);
        final CoffeeMachine coffeeMachine = new CoffeeMachine(supplier, quantityChecker);
        final Order order = new Order(Drink.CHOCOLATE);
        coffeeMachine.order(order, .5F);
        verify(supplier).order("H::");
    }

    @Test
    public void should_allow_to_add_sugar() {
        final CoffeeMachine.Supplier supplier = mock(CoffeeMachine.Supplier.class);
        final CoffeeMachine.QuantityChecker quantityChecker = mock(CoffeeMachine.QuantityChecker.class);
        final CoffeeMachine coffeeMachine = new CoffeeMachine(supplier, quantityChecker);
        final Order order = new Order(Drink.COFFEE).withSugar(1);
        coffeeMachine.order(order, .6F);
        verify(supplier).order("C:1:0");
    }

    @Test
    public void should_accept_any_message_as_well() {
        final CoffeeMachine.Supplier supplier = mock(CoffeeMachine.Supplier.class);
        final CoffeeMachine.QuantityChecker quantityChecker = mock(CoffeeMachine.QuantityChecker.class);
        final CoffeeMachine coffeeMachine = new CoffeeMachine(supplier, quantityChecker);
        coffeeMachine.forwardMessage("This is a message");
        verify(supplier).order("M:This is a message");
    }

    @Test
    public void should_not_place_the_order_as_is_if_there_is_enough_money() {
        final CoffeeMachine.Supplier supplier = mock(CoffeeMachine.Supplier.class);
        final CoffeeMachine.QuantityChecker quantityChecker = mock(CoffeeMachine.QuantityChecker.class);
        final CoffeeMachine coffeeMachine = new CoffeeMachine(supplier, quantityChecker);
        final Order order = new Order(Drink.COFFEE);
        coffeeMachine.order(order, .1F);
        verify(supplier).order("M:Not enough money, please insert 0.5€.");
    }

    @Test
    public void should_not_place_the_order_as_is_if_there_is_enough_money_2() {
        final CoffeeMachine.Supplier supplier = mock(CoffeeMachine.Supplier.class);
        final CoffeeMachine.QuantityChecker quantityChecker = mock(CoffeeMachine.QuantityChecker.class);
        final CoffeeMachine coffeeMachine = new CoffeeMachine(supplier, quantityChecker);
        final Order order = new Order(Drink.COFFEE);
        coffeeMachine.order(order, .2F);
        verify(supplier).order("M:Not enough money, please insert 0.4€.");
        verify(supplier, never()).order("C::");
    }

    @Test
    public void should_allow_to_order_orange_juice() {
        final CoffeeMachine.Supplier supplier = mock(CoffeeMachine.Supplier.class);
        final CoffeeMachine.QuantityChecker quantityChecker = mock(CoffeeMachine.QuantityChecker.class);
        final CoffeeMachine coffeeMachine = new CoffeeMachine(supplier, quantityChecker);
        final Order order = new Order(Drink.ORANGE_JUICE);
        coffeeMachine.order(order, .6F);
        verify(supplier).order("O::");
    }

    @Test
    public void should_allow_to_order_extra_hot_coffee() {
        final CoffeeMachine.Supplier supplier = mock(CoffeeMachine.Supplier.class);
        final CoffeeMachine.QuantityChecker quantityChecker = mock(CoffeeMachine.QuantityChecker.class);
        final CoffeeMachine coffeeMachine = new CoffeeMachine(supplier, quantityChecker);
        final Order order = new Order(Drink.COFFEE).extraHot();
        coffeeMachine.order(order, .6F);
        verify(supplier).order("Ch::");
    }

    @Test
    public void should_log_each_transaction() {
        final CoffeeMachine.Supplier supplier = mock(CoffeeMachine.Supplier.class);
        final CoffeeMachine.QuantityChecker quantityChecker = mock(CoffeeMachine.QuantityChecker.class);
        final CoffeeMachine.Logger logger = mock(CoffeeMachine.Logger.class);
        final CoffeeMachine coffeeMachine = new CoffeeMachine(supplier, quantityChecker);
        coffeeMachine.addLogger(logger);
        final Order order = new Order(Drink.COFFEE);
        coffeeMachine.order(order, .6F);
        verify(logger).log(Drink.COFFEE, .6F);
    }

    @Test
    public void should_not_forward_the_order_of_coffee_if_there_is_a_shortage_of_water() {
        final CoffeeMachine.Supplier supplier = mock(CoffeeMachine.Supplier.class);
        final CoffeeMachine.QuantityChecker quantityChecker = mock(CoffeeMachine.QuantityChecker.class);
        when(quantityChecker.isEmpty("COFFEE")).thenReturn(true);
        final CoffeeMachine coffeeMachine = new CoffeeMachine(supplier, quantityChecker);
        final Order order = new Order(Drink.COFFEE);
        coffeeMachine.order(order, .6F);
        verify(supplier).order("M:Sorry, there is not enough water or milk.");
        verify(supplier, never()).order("C::");
    }
}
