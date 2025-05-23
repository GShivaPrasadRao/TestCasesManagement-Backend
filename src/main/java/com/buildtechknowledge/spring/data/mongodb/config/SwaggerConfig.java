//package com.buildtechknowledge.spring.data.mongodb.config;
//
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import springfox.documentation.builders.ApiInfoBuilder;
//import springfox.documentation.builders.PathSelectors;
//import springfox.documentation.builders.RequestHandlerSelectors;
//import springfox.documentation.service.ApiInfo;
//import springfox.documentation.spi.DocumentationType;
//import springfox.documentation.spring.web.plugins.Docket;
//import springfox.documentation.swagger.web.*;
//import springfox.documentation.swagger2.annotations.EnableSwagger2;
//
//@Configuration
//public class SwaggerConfig {
//
//    @Bean
//    public Docket api() {
//        return new Docket(DocumentationType.SWAGGER_2).select()
//                .apis(RequestHandlerSelectors.basePackage("com.buildtechknowledge.spring.data.mongodb.controller"))
//                .paths(PathSelectors.any())
//                .build();
//    }
//
//    private ApiInfo apiInfo() {
//        return new ApiInfoBuilder()
//                .title("Canvas Test Automation API")
//                .description("Test Case management API")
//                .version("1.0.0")
//                .build();
//    }
//
///*Swagger Information*/
//
////    @Bean
////    UiConfiguration uiConfig() {
////        return UiConfigurationBuilder.builder()
////                .deepLinking(true)
////                .displayOperationId(false)
////                .defaultModelsExpandDepth(1)
////                .defaultModelExpandDepth(1)
////                .defaultModelRendering(ModelRendering.EXAMPLE)
////                .displayRequestDuration(false)
////                .docExpansion(DocExpansion.NONE)
////                .filter(false)
////                .maxDisplayedTags(null)
////                .operationsSorter(OperationsSorter.ALPHA)
////                .showExtensions(false)
////                .tagsSorter(TagsSorter.ALPHA)
////                .supportedSubmitMethods(UiConfiguration.Constants.DEFAULT_SUBMIT_METHODS)
////                .validatorUrl(null)
////                .build();
////
////    }
//}