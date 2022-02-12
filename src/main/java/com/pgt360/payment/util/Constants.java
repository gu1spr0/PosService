package com.pgt360.payment.util;

public class Constants {
    //STATES
    public static final String STATE_ACTIVE = "AC";
    public static final String STATE_INACTIVE = "AN";
    public static final String STATE_BLOCKED = "BL";
    public static final String STATE_DELETED = "EL";

    //FLUJO POS LITERAL
    public static final String FLOW_NONE="NONE";
    public static final String FLOW_CHIP="CHIP";
    public static final String FLOW_CHIP_MULTI="MULTICHIP";
    public static final String FLOW_CTL="CTL";
    public static final String FLOW_CTL_MULTI="MULTICTL";
    public static final String FLOW_DELETED="DELETED";
    public static final String FLOW_DELETED_MULTI="MULTIDELETED";
    public static final String FLOW_CLOSE="CLOSE";
    public static final String FLOW_CLOSE_MULTI="MULTICLOSE";
    public static final String FLOW_INIT="INIT";

    //FLUJO POS LITERAL
    public static final int NUMBER_FLOW_NONE=0;
    public static final int NUMBER_FLOW_INIT=1;
    public static final int NUMBER_FLOW_CHIP=2;
    public static final int NUMBER_FLOW_CHIP_MULTI=3;
    public static final int NUMBER_FLOW_CTL=4;
    public static final int NUMBER_FLOW_CTL_MULTI=5;
    public static final int NUMBER_FLOW_DELETED=6;
    public static final int NUMBER_FLOW_DELETED_MULTI=7;
    public static final int NUMBER_FLOW_CLOSE=8;
    public static final int NUMBER_FLOW_CLOSE_MULTI=9;

    //DATA RESPUESTA VENTA
    public static final int BEGIN_COD_AUTORIZACION = 50;
    public static final int BEGIN_MONTO_COMPRA = 72;
    public static final int BEGIN_NUM_RECIBO = 106;
    public static final int BEGIN_RRN = 128;
    public static final int BEGIN_TERMINAL_ID = 162;
    public static final int BEGIN_FECHA_TRANSAC = 188;
    public static final int BEGIN_HORA_TRANSAC = 206;
    public static final int BEGIN_COD_RESPUESTA = 224;
    public static final int BEGIN_TIPO_CUENTA = 238;
    public static final int BEGIN_NUM_CUOTAS = 252;
    public static final int BEGIN_ULT_DIGITOS = 266;
    public static final int BEGIN_MSG_ERROR = 284;
    public static final int BEGIN_BIN_TARJETA = 432;

    public static final int TAM_COD_AUTORIZACION = 12;
    public static final int TAM_MONTO_COMPRA = 24;
    public static final int TAM_NUM_RECIBO = 12;
    public static final int TAM_RRN = 24;
    public static final int TAM_TERMINAL_ID = 16;
    public static final int TAM_FECHA_TRANSAC = 8;
    public static final int TAM_HORA_TRANSAC = 8;
    public static final int TAM_COD_RESPUESTA = 4;
    public static final int TAM_TIPO_CUENTA = 4;
    public static final int TAM_NUM_CUOTAS= 4;
    public static final int TAM_ULT_DIGITOS = 8;
    public static final int TAM_MSG_ERROR = 138;
    public static final int TAM_BIN_TARJETA = 12;



}
