package com.awl.training.beans;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Basket implements Serializable {

    private static final long serialVersionUID = -3213415192138386781L;
    
    private String sessionId;
    private List<OrderItem> items = new ArrayList<OrderItem>();
    
    public Basket() {
        
    }
    
    public Basket(String sessionId) {
        this.sessionId = sessionId;
    }

    public String getSessionId() {
        return sessionId;
    }
    
    public List<OrderItem> getItems(){
        return items;
    }

}
