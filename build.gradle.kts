plugins {
    java
    id("org.springframework.boot") version "3.4.1"
    id("io.spring.dependency-management") version "1.1.7"
}


group = "co.edu.uniquindio"
version = "0.0.1-SNAPSHOT"
description = "Breve descripción de la aplicación"


java {
    toolchain {
        languageVersion = JavaLanguageVersion.of(21)
    }
}


configurations {
    compileOnly {
        extendsFrom(configurations.annotationProcessor.get())
    }
}


repositories {
    mavenCentral()
}


dependencies {
    implementation("org.springframework.boot:spring-boot-starter-web")
    implementation("org.springframework.boot:spring-boot-starter-validation")
    compileOnly("org.projectlombok:lombok")
    annotationProcessor("org.projectlombok:lombok")
    testImplementation("org.springframework.boot:spring-boot-starter-test")
    testRuntimeOnly("org.junit.platform:junit-platform-launcher")
    implementation ("org.springframework.boot:spring-boot-starter-data-jpa")
    implementation ("mysql:mysql-connector-java:8.0.33")
    implementation ("org.springframework.boot:spring-boot-starter-thymeleaf")
    implementation ("org.springframework.boot:spring-boot-starter-web")
    testImplementation ("org.springframework.boot:spring-boot-starter-test")
    compileOnly("org.projectlombok:lombok")
    annotationProcessor("org.projectlombok:lombok")
    testImplementation("org.springframework.boot:spring-boot-starter-test")
    testRuntimeOnly("org.junit.platform:junit-platform-launcher")
    implementation("org.springdoc:springdoc-openapi-starter-webmvc-ui:2.8.0")
    implementation("org.mapstruct:mapstruct:1.6.3")
    annotationProcessor("org.mapstruct:mapstruct-processor:1.6.3")
    implementation("org.springframework.boot:spring-boot-starter-security")
    implementation("io.jsonwebtoken:jjwt-api:0.12.6")
    runtimeOnly("io.jsonwebtoken:jjwt-impl:0.12.6")
    runtimeOnly("io.jsonwebtoken:jjwt-jackson:0.12.6")
}


tasks.withType<Test> {
    useJUnitPlatform()
}
