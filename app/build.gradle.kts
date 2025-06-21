plugins {
    application
    checkstyle
    id("org.sonarqube") version "6.2.0.5505"
    id("com.github.ben-manes.versions") version "0.52.0"

}

group = "hexlet.code"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
}

dependencies {
    testImplementation(platform("org.junit:junit-bom:5.10.0"))
    testImplementation("org.junit.jupiter:junit-jupiter")
}

tasks.test {
    useJUnitPlatform()
}

application {
    mainClass.set("hexlet.code.App")
}

sonar {
    properties {
        property("sonar.projectKey", "Katherini17_java-project-78")
        property("sonar.organization", "katherini-17-projects")
        property("sonar.host.url", "https://sonarcloud.io")
    }
}
