package OnlineShop;

import org.junit.*;
import org.junit.Test;

import static org.junit.Assert.*;

public class BasketTest {
    private Basket basket;
    private Item orange = new Item(5.1, "Orange");
    private Item apple = new Item(3.1, "Apple");
    private Item banana = new Item(1.12, "Banana");
    private Item broccoli = new Item(0.95, "Broccoli");
    private Item coconut = new Item(9.99, "Coconut");

    @Before
    public void setUp() {
        basket = new Basket(orange, apple, banana);
    }

    @After
    public void tearDown() {
        System.out.flush();   //???????
    }

    @Test
    public void shouldAddItems() {
        basket.addItems(broccoli, broccoli);
        assertTrue(basket.contains(broccoli));
        assertFalse(basket.contains(coconut));
        basket.addItems(coconut, 5);
        assertTrue(basket.contains(coconut));
        assertEquals(basket.numberOf(orange), 1);
        assertEquals(basket.numberOf(apple), 1);
        assertEquals(basket.numberOf(banana), 1);
        assertEquals(basket.numberOf(broccoli), 2);
        assertEquals(basket.numberOf(coconut), 5);
    }

    @Test
    public void shouldRemoveItems1() {
        basket.removeItems(orange);
        assertFalse(basket.contains(orange));
        assertTrue(basket.contains(banana));
        basket.removeItems(apple, banana);
        assertFalse(basket.contains(apple));
        assertFalse(basket.contains(banana));
    }

    @Test
    public void shouldRemoveItems2() {
        basket.removeItems(orange, 1);
        assertFalse(basket.contains(orange));
        assertTrue(basket.contains(banana));
        basket.removeItems(apple, 1);
        basket.removeItems(banana, 1);
        assertFalse(basket.contains(apple));
        assertFalse(basket.contains(banana));
    }

    @Test(expected = IllegalArgumentException.class)
    public void shouldThrowIllegalArgumentException1() {
        basket.addItems(broccoli, -5);
    }

    @Test(expected = NoSuchItemException.class)
    public void shouldThrowNoSuchItemException1() {
        basket.removeItems(orange);
        basket.removeItems(coconut);
    }

    @Test(expected = NoSuchItemException.class)
    public void shouldThrowNoSuchItemException2() {
        basket.removeItems(orange, 2);
    }

    @Test(expected = IllegalArgumentException.class)
    public void shouldThrowIllegalArgumentException2() {
        basket.removeItems(orange, -5);
    }

    @Test
    public void shouldSumUpTotal() {
        Double sum = orange.getPrice() + apple.getPrice() + banana.getPrice();
        assertEquals(sum, basket.getTotal());
    }
}