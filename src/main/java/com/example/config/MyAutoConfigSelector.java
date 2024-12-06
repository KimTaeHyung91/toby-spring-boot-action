package com.example.config;

import java.util.ArrayList;
import java.util.List;
import org.springframework.boot.context.annotation.ImportCandidates;
import org.springframework.context.annotation.DeferredImportSelector;
import org.springframework.core.type.AnnotationMetadata;

public class MyAutoConfigSelector implements DeferredImportSelector {

  private final ClassLoader classLoader;

  public MyAutoConfigSelector(ClassLoader classLoader) {
    this.classLoader = classLoader;
  }

  // 하드코딩 되어있는 정보를 설정 파일로 분리해서 좀 더 유연하게 대응 할 수 있게 처리
  @Override
  public String[] selectImports(AnnotationMetadata importingClassMetadata) {
    List<String> autoConfigs = new ArrayList<>();

    ImportCandidates.load(MyAutoConfiguration.class, classLoader).forEach(autoConfigs::add);
    return autoConfigs.toArray(new String[0]);
  }
}
