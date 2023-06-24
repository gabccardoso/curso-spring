package com.gabi.eStore.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class OrderService {

    @Autowired
    ShippingService shippingService;

    private double valorComDesconto(Double basic, Double discount){
        return (basic - basic*(discount/100));
    }

    public double valorTotal(Double basic, Double discount){
        return (valorComDesconto(basic, discount)+this.shippingService.frete(basic));
    }

}
