package com.pgt360.payment.service;

import com.pgt360.payment.service.dto.netty.ResponseDto;

public interface PagoService {
    public ResponseDto payChipSingleCommerce(float pAmount);
    public ResponseDto payChipMultiCommerce(float pAmount, int pCommerceId);
    public ResponseDto payContactlessSingleCommerce(float pAmount);
    public ResponseDto payContactlessMultiCommerce(float pAmount, int pCommerceId);
    public ResponseDto cancelTransactionSingleCommerce(int pTransaction);
    public ResponseDto cancelTransactionMultiCommerce(int pTransaction, int pCommerceId);
    public ResponseDto closeSingleCommerce(int pConfirm);
    public ResponseDto closeMultiCommerce(int pConfirm, int pCommerceId);
    public ResponseDto initDevice(int pCommerceId, int pConfirm);
}