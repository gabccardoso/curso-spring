package com.gabi.eStore.Service;


import org.springframework.stereotype.Service;

@Service
public class ShippingService {

    public Double frete(Double basic){
        if(basic<100) return 20.00;
        else if(basic<200) return 12.00;
        return 0.0;
    }

}
