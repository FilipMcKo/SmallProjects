package OnlineShop;



public class OnlineShop {

    public static void main(String[] args) {
        Basket basket = new Basket();

        Item orange = new Item(5.1,"Orange");
        Item apple = new Item(3.1,"Apple");
        Item banana = new Item(1.12,"Banana");
        Item broccoli = new Item(0.95,"Broccoli");
        Item coconut = new Item(9.99,"Coconut");
        basket.removeItems(orange);
        basket.addItems(orange,apple,banana);
        basket.addItems(orange, orange, broccoli, coconut);
        basket.removeItems(orange,coconut);
        basket.addItems(coconut, 5);
        basket.removeItems(coconut,4);
        basket.showItems();



    }

}
