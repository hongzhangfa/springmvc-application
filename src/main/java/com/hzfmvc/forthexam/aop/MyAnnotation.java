package com.hzfmvc.forthexam.aop;

import java.lang.annotation.*;

/**
 * @Target Annotation所修饰的对象范围.
 * @Documented: 注解类型信息会被包括在生成的帮助文档中。
 * @Retention: (保留)注解说明,这种类型的注解会被保留到那个阶段.
 *
 * 1.RetentionPolicy.SOURCE —— 这种类型的Annotations只在源代码级别保留,编译时就会被忽略
 * 2.RetentionPolicy.CLASS —— 这种类型的Annotations编译时被保留,在class文件中存在,但JVM将会忽略
 * 3.RetentionPolicy.RUNTIME —— 这种类型的Annotations将被JVM保留,所以他们能在运行时被JVM或其他使用反射机制的代码所读取和使用.
 *
 */
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface MyAnnotation {
    String value() default "";
}
