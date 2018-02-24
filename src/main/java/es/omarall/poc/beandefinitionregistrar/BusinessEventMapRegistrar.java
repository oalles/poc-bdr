package es.omarall.poc.beandefinitionregistrar;

import es.omarall.poc.beandefinitionregistrar.annotations.BusinessEvent;
import es.omarall.poc.beandefinitionregistrar.annotations.EnableBusinessEventMap;
import org.springframework.beans.factory.annotation.AnnotatedBeanDefinition;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.beans.factory.support.BeanDefinitionRegistry;
import org.springframework.beans.factory.support.GenericBeanDefinition;
import org.springframework.context.ResourceLoaderAware;
import org.springframework.context.annotation.ClassPathScanningCandidateComponentProvider;
import org.springframework.context.annotation.ImportBeanDefinitionRegistrar;
import org.springframework.core.io.ResourceLoader;
import org.springframework.core.type.AnnotationMetadata;
import org.springframework.core.type.filter.AnnotationTypeFilter;
import org.springframework.util.ClassUtils;
import org.springframework.util.ReflectionUtils;
import org.springframework.util.StringUtils;

import java.lang.reflect.Field;
import java.util.*;

public class BusinessEventMapRegistrar implements ImportBeanDefinitionRegistrar, ResourceLoaderAware {

    private ResourceLoader resourceLoader;

    @Override
    public void registerBeanDefinitions(AnnotationMetadata importingClassMetadata, BeanDefinitionRegistry registry) {
        GenericBeanDefinition gbd = new GenericBeanDefinition();
        gbd.setBeanClass(BusinessEventMap.class);
        Map<String, Class<?>> eventMap = new HashMap<>();

        // Add tipos
        ClassPathScanningCandidateComponentProvider scanner = new ClassPathScanningCandidateComponentProvider(false);
        scanner.setResourceLoader(this.resourceLoader);

        Set<String> basePackages = getBasePackages(importingClassMetadata);

        // Load @BusinessEvent
        AnnotationTypeFilter annotationTypeFilter = new AnnotationTypeFilter(
                BusinessEvent.class, true);
        scanner.addIncludeFilter(annotationTypeFilter);


        for (String basePackage : basePackages) {

            Set<BeanDefinition> candidateComponents = scanner
                    .findCandidateComponents(basePackage);

            for (BeanDefinition candidateComponent : candidateComponents) {
                if (candidateComponent instanceof AnnotatedBeanDefinition) {

                    AnnotatedBeanDefinition beanDefinition = (AnnotatedBeanDefinition) candidateComponent;
                    if (!beanDefinition.isAbstract()) {
                        // Retrieve type field
                        try {

                            Class<?> cl = Class.forName(beanDefinition.getBeanClassName());

                            Field field = ReflectionUtils.findField(cl, "key");
                            ReflectionUtils.makeAccessible(field);
                            String key = (String) field.get(null);
                            eventMap.put(key, cl);
                        } catch (Throwable e) {
                            e.printStackTrace();
                        }
                    }
                }
            }
        }


        gbd.getPropertyValues().addPropertyValue("eventMap", eventMap);
        registry.registerBeanDefinition("readyEventMap", gbd);
    }

    @Override
    public void setResourceLoader(ResourceLoader resourceLoader) {
        this.resourceLoader = resourceLoader;
    }

    protected Set<String> getBasePackages(AnnotationMetadata importingClassMetadata) {

        Map<String, Object> attributes = importingClassMetadata
                .getAnnotationAttributes(EnableBusinessEventMap.class.getCanonicalName());

        Set<String> basePackages = new HashSet<>();

        for (String pkg : (String[]) attributes.get("value")) {
            if (StringUtils.hasText(pkg)) {
                basePackages.add(pkg);
            }
        }
        for (String pkg : (String[]) attributes.get("basePackages")) {
            if (StringUtils.hasText(pkg)) {
                basePackages.add(pkg);
            }
        }

        if (basePackages.isEmpty()) {
            basePackages.add(
                    ClassUtils.getPackageName(importingClassMetadata.getClassName()));
        }
        return basePackages;
    }

}
