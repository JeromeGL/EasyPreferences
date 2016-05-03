package com.ezpreferences.processor.utils;

import javax.annotation.processing.Messager;
import javax.tools.Diagnostic;

/**
 * Created by hujinrong on 16/5/3.
 */
public class Logger {
    private static Logger sLogger ;
    public static Logger getLogger(Messager messager ) {
        if( sLogger == null ) {
            sLogger = new Logger(messager);
        }
        return sLogger ;
    }

    private Messager mMessager ;
    private Logger(Messager messager) {
        this.mMessager = messager ;
    }

    public void n(String message) {
        this.mMessager.printMessage(Diagnostic.Kind.NOTE,message);
    }

    public void e(String message) {
        this.mMessager.printMessage(Diagnostic.Kind.ERROR,message);
    }
}
