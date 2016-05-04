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
package com.ezpreferences.processor.elemententity;

import javax.lang.model.element.VariableElement;
import javax.lang.model.type.TypeMirror;

/**
 * Created by hujinrong on 16/4/30.
 */
public class VariableElementEntity extends BaseElementEntity<VariableElement> {
    String mName;

    public VariableElementEntity(VariableElement element) {
        super(element);
        this.mElement = element ;
        this.mName = element.getSimpleName().toString();
    }

    public String getType() {
        return this.mElement.asType().toString();
    }

    public TypeMirror getTypeMirror() {
        return this.mElement.asType() ;
    }

    public boolean isField() {
        return this.mElement.getKind().isField() ;
    }

    public String getmName() {
        return mName;
    }

}
