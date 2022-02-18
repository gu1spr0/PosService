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

    //DATA RESPUESTA ANULACION
    public static final int AN_IAUTORIZACION = 52;
    public static final int AN_ICOMPRA = 74;
    public static final int AN_IRECIBO = 108;
    public static final int AN_IRRN = 130;
    public static final int AN_ITERMINAL = 164;
    public static final int AN_IFECHA = 190;
    public static final int AN_IHORA = 208;
    public static final int AN_IRESPUESTA = 226;
    public static final int AN_IDIGITOS = 240;
    public static final int AN_IERROR = 258;
    public static final int AN_IBIN = 406;

    public static final int AN_LAUTORIZACION = 12;
    public static final int AN_LCOMPRA = 24;
    public static final int AN_LRECIBO = 12;
    public static final int AN_LRRN = 24;
    public static final int AN_LTERMINAL = 16;
    public static final int AN_LFECHA = 8;
    public static final int AN_LHORA = 8;
    public static final int AN_LRESPUESTA = 4;
    public static final int AN_LDIGITOS = 8;
    public static final int AN_LERROR = 138;
    public static final int AN_LBIN = 12;

    //DATA RESPUESTA CIERRE CON TRANSACCION
    public static final int CT_IAUTORIZACION = 50;
    public static final int CT_IRESPUESTA = 72;

    public static final int CT_LAUTORIZACION = 12;
    public static final int CT_LRESPUESTA = 4;

    //DATA RESPUESTA INICIALIZACION
    public static final int IN_IRESPUESTA = 50;

    public static final int IN_LRESPUESTA = 4;

    //MENSAJES
    public static final String RES_1 = "Proceso en paso 1";
    public static final String RES_2 = "Proceso en paso 2";
    public static final String RES_3 = "Proceso en paso 3";
    public static final String RES_4 = "Proceso en paso 4";
    public static final String RES_5 = "Proceso en paso 5";
    public static final String RES_FINAL = "Proceso finalizado!";
    public static final String RES_INCOMPLETE = "Datos incompletos";
    public static final String RES_NOT_VALID = "Paso no válido";



}
