package com.zhou.service;

import org.springframework.stereotype.Component;

@Component
public class PaymentFallbackService implements PaymentHysrixService{
    @Override
    public String getPaymentInfo_OK(Long id) {
        return "---one---";
    }

    @Override
    public String getPaymentInfo_Timeout(Long id) {
        return "---two---";
    }
}
