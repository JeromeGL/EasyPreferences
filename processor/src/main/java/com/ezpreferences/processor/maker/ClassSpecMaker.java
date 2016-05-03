package com.ezpreferences.processor.maker;

import com.ezpreferences.processor.elemententity.TypeElementEntity;
import com.ezpreferences.processor.utils.Utils;
import com.squareup.javapoet.*;

import java.util.List;

import javax.lang.model.element.Modifier;
import javax.lang.model.type.TypeMirror;
import javax.lang.model.util.Elements;


/**
 * Created by hujinrong on 16/5/1.
 */
public class ClassSpecMaker implements IMaker<TypeSpec> {

    private TypeElementEntity mTypeElementEntity ;
    private TypeMirror mSharedPreference ;
    private TypeMirror mContextTypeMirror ;
    public ClassSpecMaker(TypeElementEntity typeElementEntity, Elements elements) {
        this.mTypeElementEntity = typeElementEntity ;
        mSharedPreference = elements.getTypeElement("android.content.SharedPreferences").asType();
        mContextTypeMirror = elements.getTypeElement("android.content.Context").asType() ;
    }

    @Override
    public TypeSpec make() {
        TypeSpec.Builder builder = TypeSpec.classBuilder(mTypeElementEntity.getElement().getSimpleName()+"_")
                .addSuperinterface(TypeName.get(mTypeElementEntity.getTypeMirror()))
                .addModifiers(Modifier.FINAL)
                .addModifiers(Modifier.PUBLIC);
        builder.addField(new FieldSpecMaker(mSharedPreference).make());
        List<com.ezpreferences.processor.entity.MethodEntity> methodEntities = com.ezpreferences.processor.checker.MethodChecker.getMethodEntities();
        for(int i = 0 ; i < methodEntities.size() ;i++ ) {
            com.ezpreferences.processor.entity.MethodEntity methodEntity = methodEntities.get(i);
            MethodSpec methodSpec = Utils.makeMethod(methodEntity) ;
            builder.addMethod(methodSpec);
        }
        builder.addMethod(new com.ezpreferences.processor.maker.methodmaker.ConstructWithContextMaker(mTypeElementEntity.getElement().getSimpleName()+"_P",mContextTypeMirror).make());
        builder.addMethod(new com.ezpreferences.processor.maker.methodmaker.ConstructWithPreferenceMaker(mSharedPreference).make());
        return builder.build();
    }
}
