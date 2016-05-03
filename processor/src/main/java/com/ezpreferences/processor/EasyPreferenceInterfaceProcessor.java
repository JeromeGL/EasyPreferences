package com.ezpreferences.processor;

import com.ezpreferences.interfaces.EasyPreferenceInterface;
import com.ezpreferences.processor.checker.IChecker;
import com.ezpreferences.processor.checker.TypeChecker;
import com.ezpreferences.processor.elemententity.BaseElementEntity;
import com.ezpreferences.processor.elemententity.ExecutableElementEntity;
import com.ezpreferences.processor.elemententity.TypeElementEntity;
import com.ezpreferences.processor.maker.ClassSpecMaker;
import com.ezpreferences.processor.maker.IMaker;
import com.google.auto.service.AutoService;
import com.squareup.javapoet.JavaFile;
import com.squareup.javapoet.TypeSpec;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Set;

import javax.annotation.processing.Processor;
import javax.annotation.processing.RoundEnvironment;
import javax.lang.model.element.Element;
import javax.lang.model.element.ExecutableElement;
import javax.lang.model.element.TypeElement;

/**
 * Created by hujinrong on 16/5/3.
 */
@AutoService(Processor.class)
public class EasyPreferenceInterfaceProcessor extends BaseProcessor {
    @Override
    public boolean process(Set<? extends TypeElement> annotations, RoundEnvironment roundEnv) {
        for(Element element:roundEnv.getElementsAnnotatedWith(EasyPreferenceInterface.class)) {
            TypeElement typeElement = (TypeElement) element;
            List<BaseElementEntity> entities = new ArrayList<>();

            TypeElementEntity typeEntity = new TypeElementEntity(typeElement);
            entities.add(typeEntity);

            List<Element> methodElements = getMethodElements(typeElement);
            for(int i = 0 ; i < methodElements.size() ;i++ ) {
                ExecutableElement executableElement = (ExecutableElement) methodElements.get(i);
                ExecutableElementEntity executableEntity = new ExecutableElementEntity(executableElement);
                if( executableEntity.isStatic() ) {
                    continue ;
                }
                entities.add(executableEntity);
            }

            mLogger.n("before validate");

            //validate
            IChecker checker = new TypeChecker(entities);
            if( !checker.check() ) {
                mLogger.e(checker.errorMessage());
                return false ;
            }
            mLogger.n("after validate");

            IMaker<TypeSpec> classSpecMakerIMaker = new ClassSpecMaker(typeEntity,mElements);

            try {
                JavaFile javaFile = JavaFile.builder(typeEntity.getPackageElement().getQualifiedName().toString(), classSpecMakerIMaker.make())
                        .build();
                javaFile.writeTo(mFiler);
            }catch(Throwable throwable) {
                mLogger.e(throwable.getMessage());
            }

        }
        return true ;
    }

    @Override
    public Set<String> getSupportedAnnotationTypes() {
        return Collections.singleton(EasyPreferenceInterface.class.getCanonicalName());
    }
}
