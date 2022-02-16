package com.pgt360.payment.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.ObjectUtils;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.Base64;
import java.util.Date;

public class NettyUtil {
    public static Logger log = LoggerFactory.getLogger(NettyUtil.class);
    public static byte[] convertObjectToByteArray(Object obj) throws IOException {
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        ObjectOutputStream os = new ObjectOutputStream(out);
        os.writeObject(obj);
        return out.toByteArray();
    }

    public static Object convertByteArrayToObject(byte[] data) throws IOException, ClassNotFoundException {
        ByteArrayInputStream in = new ByteArrayInputStream(data);
        ObjectInputStream is = new ObjectInputStream(in);
        return is.readObject();
    }
    public static String convertNullToEmptyString(String valor){
        if(ObjectUtils.isEmpty(valor) || valor.equals("null"))
            return "";
        return valor;
    }
    public static String convertDate(Date date){
        String format = "dd/MM/yyyy HH:mm:ss aa";
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(format);
        return simpleDateFormat.format(date);
    }
    public static String convertFileToBase64(String path){
        File file =  new File(path);
        byte[] bytesFile = convertFileToByteArray(file);
        return Base64.getEncoder().encodeToString(bytesFile);
    }
    public static byte[] convertFileToByteArray(File file){
        FileInputStream fis =  null;
        byte[] fileArray = new byte[(int) file.length()];
        try{
            fis = new FileInputStream(file);
            fis.read(fileArray);
            fis.close();
        } catch(IOException ioExp) {
            ioExp.printStackTrace();
        } finally {
            if(fis != null){
                try{
                    fis.close();
                } catch (IOException e){
                    e.printStackTrace();
                }
            }
        }
        return fileArray;
    }
    public static String currentPath(){
        return new File("").getAbsolutePath();
    }
    public static Date currentDate(){
        return new Date();
    }
    public static String decodeBase64String(String encodedString){
        byte[] decodedBytes = Base64.getDecoder().decode(encodedString);
        return new String(decodedBytes);
    }


    public static String bytesToHex(byte[] bytes) {
        char[] HEX_ARRAY = "0123456789ABCDEF".toCharArray();
        char[] hexChars = new char[bytes.length * 2];
        for (int j = 0; j < bytes.length; j++) {
            int v = bytes[j] & 0xFF;
            hexChars[j * 2] = HEX_ARRAY[v >>> 4];
            hexChars[j * 2 + 1] = HEX_ARRAY[v & 0x0F];
        }
        log.info("[TAREA]: Conversion BYTES a HEX = "+new String(hexChars));
        return new String(hexChars);
    }

    public static String hex2a(String hexx){
        String result = "";
        char[] charArray = hexx.toCharArray();
        for(int i = 0; i<charArray.length; i=i+2){
            String st = ""+charArray[i]+""+charArray[i+1];
            char ch = (char)Integer.parseInt(st, 16);
            result = result + ch;
        }
        log.info("[TAREA]: Conversion HEXA a CADENA = "+result);
        return result;
    }
    public static String asciiToHex(String str){
        char[] ch = str.toCharArray();
        StringBuilder builder = new StringBuilder();
        for(char c : ch){
            builder.append(Integer.toHexString((int)c).toUpperCase());
        }
        log.info("[TAREA]: Conversion ASCII a HEXA = "+builder.toString());
        return builder.toString();

    }
    public static String xor(String str){
        byte[] data = str.getBytes(StandardCharsets.UTF_8);
        byte x = 0;
        int tam = data.length;
        for (byte datum : data) {
            x ^= datum;
        }
        String hex = "0"+Integer.toHexString(x);
        String xo = hex.substring(hex.length()-2, hex.length());
        log.info("[TAREA]: Valor xor = "+xo);
        return xo;
    }
    public static boolean isNaN(float v) {
        return (v != v);
    }
    public static String validarMonto(float monto){
        String montos = String.valueOf(monto);
        String[] n = montos.split("\\.");
        log.error("[0]="+n[0]);
        log.error("[1]="+n[1]);
        String num = n[0]+n[1];
        log.error("NUMERO DESCOMPUESTO:"+num);
        num = "000000000000"+num;
        final String substring = num.substring(num.length() - 12);
        log.info("[TAREA]: Validando monto = "+ substring);
        return substring;

    }
    public static String validarRef(String pId){
        String recibo = pId.toString();
        recibo = "000000" + recibo;
        final String substring = recibo.substring(recibo.length() - 6);
        log.info("[TAREA]: Validando referencia para anulacion = "+ substring);
        return substring;
    }
    public static float redondearMonto(float numero){
        String format = new DecimalFormat("##.##").format(numero);
        log.info("[TAREA]: Monto redondeado = "+format);
        return Float.parseFloat(format);
    }
}
