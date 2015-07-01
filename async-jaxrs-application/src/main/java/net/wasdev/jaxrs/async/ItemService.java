package net.wasdev.jaxrs.async;

import java.util.Arrays;
import java.util.Collection;
import java.util.concurrent.TimeUnit;

public class ItemService {

    public Collection<Item> listItems() {
        try {
            System.out.println("Wait for 5 seconds");
            Thread.sleep(TimeUnit.SECONDS.toMillis(5));
        } catch (InterruptedException e) {
        }
        return Arrays.asList(new Item(), new Item());
    }

    public void addItem(Item newItem) {
    }

    public Item get(int id) {
        return null;
    }
}
