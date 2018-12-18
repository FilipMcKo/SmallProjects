package OnlineShop;

import java.util.HashMap;
import java.util.Map;

public class Basket {

    private Map<Item, Integer> orderedItems = new HashMap<>();
    private Double total = 0d;

    public Basket() {
    }

    public Basket(Item...items){
        int count = 0;
        for (Item item : items) {
            count = orderedItems.containsKey(item) ? orderedItems.get(item) : 0;
            orderedItems.put(item, count + 1);
        }

    }

    public void addItems(Item... args) {
        int count = 0;
        for (Item item : args) {
            count = orderedItems.containsKey(item) ? orderedItems.get(item) : 0;
            orderedItems.put(item, count + 1);
        }
    }

    public void addItems(Item item, int amount) {
        if(amount<1) throw new IllegalArgumentException("addItems: Amount has to be greater than 0!");
        if(orderedItems.containsKey(item)) orderedItems.put(item,orderedItems.get(item)+amount);
        else orderedItems.put(item,amount);
    }


    public void removeItems(Item... args) {
        int count = 0;
        for (Item item : args) {
            if(!orderedItems.containsKey(item)) throw new NoSuchItemException("removeItems: You can't remove more items than you have in your basket!");
            count = orderedItems.containsKey(item) ? orderedItems.get(item) : 0;
            if (count < 2) orderedItems.remove(item);
            else orderedItems.put(item, count - 1);
        }
    }

    public void removeItems(Item item, int amount) {
        if(amount<1) throw new IllegalArgumentException("removeItems: Amount has to be greater than 0!");
        if(orderedItems.containsKey(item)&&orderedItems.get(item)>amount) orderedItems.put(item,orderedItems.get(item)-amount);
        else if(orderedItems.containsKey(item)&&orderedItems.get(item)==amount) orderedItems.remove(item);
        else throw new NoSuchItemException("removeItems: You can't remove more items than you have in your basket!");
    }

    public void showItems() {
        this.total = 0d;
        for (Item key : orderedItems.keySet()) {
            System.out.printf("%12s %3d x %5.2f$ = %6.2f$\n", key.getName(), orderedItems.get(key), key.getPrice(), (orderedItems.get(key) * key.getPrice()));
            this.total += orderedItems.get(key) * key.getPrice();
        }
        System.out.printf("%27s %6.2f$\n","Total:", this.total);
    }

    public Double getTotal(){
        this.total = 0d;
        for (Item key : orderedItems.keySet()) {
            total += orderedItems.get(key) * key.getPrice();
        }
        return this.total;
    }

    public boolean contains(Item item){
        if(orderedItems.containsKey(item)) return true;
        return false;
    }
    public int numberOf(Item item){
        return orderedItems.get(item);
    }

}
