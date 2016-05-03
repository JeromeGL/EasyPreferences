package com.ezpreferences.processor.maker.methodmaker;

import com.ezpreferences.processor.maker.IMaker;
import com.squareup.javapoet.MethodSpec;
import com.squareup.javapoet.TypeName;

import javax.lang.model.element.Modifier;
import javax.lang.model.type.TypeMirror;

/**
 * Created by hujinrong on 16/5/3.
 */
public class ConstructWithContextMaker implements IMaker<MethodSpec> {
    protected String mSharedPreferenceName;
    protected TypeMirror mContextTypeMirror ;
    public ConstructWithContextMaker(String name, TypeMirror contextTypeMirror) {
        this.mSharedPreferenceName = name ;
        this.mContextTypeMirror = contextTypeMirror ;
    }
    @Override
    public MethodSpec make() {
        MethodSpec methodSpec = MethodSpec.constructorBuilder()
                .addParameter(TypeName.get(mContextTypeMirror),"context")
                .addStatement("mSharedPreference = context.getApplicationContext().getSharedPreferences(\"$L\",Context.MODE_PRIVATE);", mSharedPreferenceName)
                .addModifiers(Modifier.PUBLIC)
                .build();
        return methodSpec;
    }
}
