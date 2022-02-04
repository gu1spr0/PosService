package com.pgt360.payment.service;

import com.pgt360.payment.service.dto.netty.ResponseDto;

public interface PagoService {
    ResponseDto payChipSingleCommerce(float pAmount);
    ResponseDto payChipMultiCommerce(float pAmount, int pCommerceId);
    ResponseDto payContactlessSingleCommerce(float pAmount);
    ResponseDto payContactlessMultiCommerce(float pAmount, int pCommerceId);
    ResponseDto cancelTransactionSingleCommerce(String pTransaction);
    ResponseDto cancelTransactionMultiCommerce(String pTransaction, int pCommerceId);
    ResponseDto closeSingleCommerce(int pConfirm);
    ResponseDto closeMultiCommerce(int pConfirm, int pCommerceId);
    ResponseDto initDevice(int pCommerceId, int pConfirm);
}
