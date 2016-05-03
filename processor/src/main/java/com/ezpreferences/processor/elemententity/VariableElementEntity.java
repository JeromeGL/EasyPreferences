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
