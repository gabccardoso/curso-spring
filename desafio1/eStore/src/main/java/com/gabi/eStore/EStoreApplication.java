package com.gabi.eStore;

import com.gabi.eStore.Entities.Order;
import com.gabi.eStore.Service.OrderService;
import com.gabi.eStore.Service.ShippingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
public class EStoreApplication implements CommandLineRunner {

    @Autowired
    private OrderService orderService;

	public static void main(String[] args) {
		SpringApplication.run(EStoreApplication.class, args);
	}

    @Override
    public void run(String ...args){
        Order order = new Order(1034, 150.00, 20.00);
        System.out.println("Pedido código: " + order.getCode() + "Valor total: R$ "+
                (this.orderService.valorTotal(order.getBasic(), order.getDiscount())));
    }
}
