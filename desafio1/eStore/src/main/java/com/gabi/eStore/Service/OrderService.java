package com.gabi.eStore.Service;

import org.springframework.stereotype.Service;

@Service
public class OrderService {

    public double valorComDesconto(Double basic, Double discount){
        return (basic - basic*(discount/100));
    }

}
