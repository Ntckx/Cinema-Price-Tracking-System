package com.ntck.cinematickettrackingsystem.Pricing.discountStrategy.Coupon;

import com.ntck.cinematickettrackingsystem.Pricing.discountStrategy.DiscountStrategy;

public abstract class Coupon implements DiscountStrategy {

    // Prevent the illegal boundary
    protected double preventNegative(double price){
        return Math.max(price,0);
    }
}