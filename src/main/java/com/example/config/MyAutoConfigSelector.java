package com.example.config;

import org.springframework.context.annotation.DeferredImportSelector;
import org.springframework.core.type.AnnotationMetadata;

public class MyAutoConfigSelector implements DeferredImportSelector {

  @Override
  public String[] selectImports(AnnotationMetadata importingClassMetadata) {
    return new String[]{
        "com.example.config.autoconfig.DispatcherServletConfig",
        "com.example.config.autoconfig.TomcatWebServerConfig"
    };
  }
}
