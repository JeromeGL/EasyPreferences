package com.ezpreferences.processor.elemententity;

import java.util.HashSet;
import java.util.Set;

import javax.lang.model.element.Element;
import javax.lang.model.element.Modifier;

/**
 * Created by hujinrong on 16/4/30.
 */
public abstract class BaseElementEntity<T extends Element> {
    protected T mElement;
    protected Set<Modifier> mModifiers = new HashSet<>();
    public BaseElementEntity(T element) {
        this.mElement = element ;
        Set<Modifier> elementModifiers = this.mElement.getModifiers()  ;
        if( elementModifiers != null ) {
            mModifiers.addAll(elementModifiers);
        }
    }

    public boolean isFinal() {
        return mModifiers.contains(Modifier.FINAL);
    }

    public boolean isStatic() {
        return mModifiers.contains(Modifier.STATIC);
    }

    public boolean isPublic() {
        return mModifiers.contains(Modifier.PUBLIC);
    }

    public boolean isAbstract() {
        return mModifiers.contains(Modifier.ABSTRACT);
    }

    public T getElement() {
        return mElement;
    }


}
