package one;

import annotation.TransactionalService;
import org.springframework.core.type.AnnotationMetadata;
import org.springframework.core.type.StandardAnnotationMetadata;
import org.springframework.util.ClassUtils;
import org.springframework.util.CollectionUtils;
import org.springframework.util.ReflectionUtils;

import java.lang.annotation.Annotation;
import java.lang.annotation.Target;
import java.lang.reflect.AnnotatedElement;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * 项目名称:   pinkstone
 * 包:        one
 * 类名称:     TransactionMainClass
 * 类描述:     测试 模式注解与功能注解 @TransactionService
 * 创建人:     huangyang
 * 创建时间:   2019/11/25 20:03
 */
@TransactionalService(name = "transaction_main")
public class TransactionMainClass {

    public static void main(String[] args){

        DoAnnotationByStandard();
    }

    // 1. 基于反射获取注解信息
    private static void DoReflectAnnotation() {

        // Class implement AnnotatedElement
        AnnotatedElement element = TransactionMainClass.class;
        // 获取全部注解
        List<Annotation> annotations = Arrays.asList(element.getAnnotations());
        // 获取指定注解
        TransactionalService ts = element.getAnnotation(TransactionalService.class);
        // 1 get and print
        System.out.println("transactionService_name by get: " + ts.name());
        // 2 reflect and print
        System.out.println("======= all Annotation =====");
        ReflectionUtils.doWithMethods(TransactionalService.class
                // 获取键值映射
                ,method -> System.out.printf("@TransactionService.%s() = %s\n",
                        method.getName(), ReflectionUtils.invokeMethod(method, ts))
                // 方法过滤，无参方法
                ,method -> method.getParameterCount() == 0);

        // 排除 Annotation 接口的方法
        System.out.println("======= no Annotation =====");
        ReflectionUtils.doWithMethods(TransactionalService.class
                // 获取键值映射
                ,method -> System.out.printf("@TransactionService.%s() = %s\n",
                        method.getName(), ReflectionUtils.invokeMethod(method, ts))
                // 方法过滤，无参方法
                ,method -> !method.getDeclaringClass().equals(Annotation.class));
    }

    /* 通过反射获取注解属性 start */
    // 2 基于 递归 + 反射 获取元注解信息
    private static void DoAnnotation() {

        // Class implement AnnotatedElement
        AnnotatedElement element = TransactionMainClass.class;
        // 获取指定注解
        TransactionalService ts = element.getAnnotation(TransactionalService.class);

        // 所有注解默认实现了 Annotation 接口
        Set<Annotation> metaAnnotations = getAllMetaAnnotations(ts);

        // print
        metaAnnotations.forEach(TransactionMainClass::printAnnotationAttribute);
    }

    // 递归获取所有注解信息
    private static Set<Annotation> getAllMetaAnnotations(Annotation annotation) {

        // 获取元注解
        Annotation[] metaAnnotations = annotation.annotationType().getAnnotations();
        // empty annotation
        if (metaAnnotations.length == 0) return Collections.emptySet();

        // 获取所有非Java标准元注解集合
        Set<Annotation> metaAnnotationsSet = Stream.of(metaAnnotations)
                // 排除 Java 标准注解，如@Target、@Document，互相依赖，递归会进入死循环
                // 通过包名排除 <java.lang.annotation>
                .filter(metaAnnotation -> !Target.class.getPackage().equals(metaAnnotation.annotationType().getPackage()))
                .collect(Collectors.toSet());

        // 递归查找元注解的元注解集合
        Set<Annotation> metaMetaAnnotationsSet = metaAnnotationsSet.stream()
                .map(TransactionMainClass::getAllMetaAnnotations)
                .collect(HashSet::new, Set::addAll, Set::addAll);

        // 添加递归结果
        metaAnnotationsSet.addAll(metaMetaAnnotationsSet);

        return metaAnnotationsSet;
    }

    // 打印注解属性
    private static void printAnnotationAttribute(Annotation annotation) {

        Class<?> annotationType = annotation.annotationType();

        ReflectionUtils.doWithMethods(annotationType,
                method -> System.out.printf("@%s.%s() = %s\n",
                        annotationType.getSimpleName(),
                        method.getName(),
                        ReflectionUtils.invokeMethod(method, annotation)),
                method -> !method.getDeclaringClass().equals(Annotation.class));
    }
    /* 2 通过反射获取注解属性 end */

    /**
     * Spring Framework 4.0 开始, AnnotationMetadata#getMetaAnnotationTypes(String)方法能获取所有元注解类型集合
     *
     * 再结合 getAnnotationAttributes(String)方法，返回注解所关联的属性信息，以 Map 结构存储
     *
     * 另外，AnnotationMetadata 存在两种实现:
     *
     *    - 基于 ASM 的 AnnotationMetadataReadingVisitor
     *
     *    - 基于 Java反射API 的 StandardAnnotationMetadata
     */

    /* 3 通过SpringFramework4.0 的 StandardAnnotationMetadata 实现属性获取 start */
    private static void DoAnnotationByStandard() {

        AnnotationMetadata metadata = new StandardAnnotationMetadata(TransactionMainClass.class);

        Set<String> metaAnnotationTypes = metadata.getAnnotationTypes().stream()
                // 获取单注解的元注解集合
                .map(metadata::getMetaAnnotationTypes)
                .collect(LinkedHashSet::new, Set::addAll, Set::addAll);

        metaAnnotationTypes.forEach(metaAnnotationType -> {
            Map<String, Object> annotationAttributes = metadata.getAnnotationAttributes(metaAnnotationType);
            if (!CollectionUtils.isEmpty(annotationAttributes)) {
                annotationAttributes.forEach((k, v) -> System.out.printf("注解 @%s.%s = %s\n",
                        ClassUtils.getShortName(metaAnnotationType), k, v));
            }
        });
    }
    /* 3 通过SpringFramework4.0 的 StandardAnnotationMetadata 实现属性获取  end */

}
