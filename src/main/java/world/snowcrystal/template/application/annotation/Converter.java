package world.snowcrystal.template.application.annotation;

import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;
import org.springframework.core.annotation.AliasFor;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target({ElementType.TYPE})
@Retention(RetentionPolicy.CLASS)
public @interface Converter {
    @AliasFor(
            annotation = Mapper.class
    )
    Class<?>[] uses() default {};

    @AliasFor(
            annotation = Mapper.class
    )
    ReportingPolicy unmappedSourcePolicy() default ReportingPolicy.IGNORE;

    @AliasFor(
            annotation = Mapper.class
    )
    ReportingPolicy unmappedTargetPolicy() default ReportingPolicy.WARN;

    @AliasFor(
            annotation = Mapper.class
    )
    ReportingPolicy typeConversionPolicy() default ReportingPolicy.IGNORE;

    @AliasFor(
            annotation = Mapper.class
    )
    Class<? extends Exception> unexpectedValueMappingException() default IllegalArgumentException.class;

    @AliasFor(
            annotation = Mapper.class
    )
    Class<?>[] imports() default {};

    @AliasFor(
            annotation = Mapper.class
    )
    String componentModel() default "default";

    @AliasFor(
            annotation = Mapper.class
    )
    String implementationName() default "<CLASS_NAME>Impl";

    @AliasFor(
            annotation = Mapper.class
    )
    String implementationPackage() default "<PACKAGE_NAME>";


}
