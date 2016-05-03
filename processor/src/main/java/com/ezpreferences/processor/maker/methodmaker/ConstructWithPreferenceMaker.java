package com.ezpreferences.processor.maker.methodmaker;

import com.ezpreferences.processor.maker.IMaker;
import com.squareup.javapoet.MethodSpec;
import com.squareup.javapoet.TypeName;

import javax.lang.model.element.Modifier;
import javax.lang.model.type.TypeMirror;

/**
 * Created by hujinrong on 16/5/3.
 */
public class ConstructWithPreferenceMaker implements IMaker<MethodSpec> {
    protected TypeMirror mSharedPreferenceTypeMirror ;
    public ConstructWithPreferenceMaker(TypeMirror sharedPreferenceTypeMirror) {
        this.mSharedPreferenceTypeMirror = sharedPreferenceTypeMirror ;
    }
    @Override
    public MethodSpec make() {
        MethodSpec methodSpec = MethodSpec.constructorBuilder()
                .addParameter(TypeName.get(this.mSharedPreferenceTypeMirror),"sharedPreference")
                .addStatement("mSharedPreference = sharedPreference")
                .addModifiers(Modifier.PUBLIC)
                .build();
        return methodSpec;
    }
}
