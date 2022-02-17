package com.pgt360.payment.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class SincronizacionUtil {
    Logger log = LoggerFactory.getLogger(SincronizacionUtil.class);
    public void doWait(long l){
        synchronized (this){
            try {
                this.wait(l);
            } catch (InterruptedException ie){
                log.error(ie.getMessage());
            }
        }
    }
    public void doNotify(){
        synchronized (this){
            this.notify();
        }
    }
    public void doWait(){
        synchronized (this){
            try{
                this.wait();
            } catch (InterruptedException ie){
                log.error(ie.getMessage());
            }
        }
    }
}
