package net.wasdev.jaxrs.async;

import java.util.concurrent.atomic.AtomicInteger;

public class Item {
    private final static AtomicInteger next = new AtomicInteger();
    
    private long id;
    
    public Item() {
        id = next.incrementAndGet();
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }
    
    
}
