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
package com.ezpreferences.processor.checker;

import com.ezpreferences.processor.elemententity.BaseElementEntity;
import com.ezpreferences.processor.elemententity.ExecutableElementEntity;
import com.ezpreferences.processor.elemententity.TypeElementEntity;
import com.ezpreferences.processor.utils.Constant;

import java.util.List;

/**
 * Created by hujinrong on 16/5/3.
 */
public class TypeChecker implements IChecker {

    List<BaseElementEntity> mBaseEntities;
    private String mMessage ;
    public TypeChecker(List<BaseElementEntity> baseEntities) {
        this.mBaseEntities = baseEntities ;
    }

    @Override
    public boolean check() {
        for(int i = 0 ; i < mBaseEntities.size();i++ ) {
            BaseElementEntity baseElementEntity = mBaseEntities.get(i) ;
            if( baseElementEntity instanceof TypeElementEntity) {
                TypeElementEntity typeElementEntity = (TypeElementEntity) baseElementEntity;
                if( !typeElementEntity.isTopLevel() ) {
                    mMessage = typeElementEntity.getElement().getSimpleName()+ Constant.WARNING_TYPE_TOP_LEVEL;
                    return false ;
                }

                if( !typeElementEntity.isInterface() ) {
                    mMessage = typeElementEntity.getElement().getSimpleName() + Constant.WARNING_TYPE_INTERFACE;
                    return false ;
                }

            } else if( baseElementEntity instanceof ExecutableElementEntity) {
                com.ezpreferences.processor.checker.MethodChecker methodChecker = new com.ezpreferences.processor.checker.MethodChecker((ExecutableElementEntity) baseElementEntity);
                boolean isOk = methodChecker.check() ;
                if( !isOk ) {
                    mMessage = methodChecker.errorMessage() ;
                    return false ;
                }
            }
        }
        return true ;
    }

    @Override
    public String errorMessage() {
        return mMessage;
    }
}
