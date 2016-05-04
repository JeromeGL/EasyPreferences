/**
 * Copyright (C) hujinrong. hujinrong888@gmail.com
 *
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not
 * use this file except in compliance with the License. You may obtain a copy of
 * the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed To in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS, WITHOUT
 * WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the
 * License for the specific language governing permissions and limitations under
 * the License.
 */
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
