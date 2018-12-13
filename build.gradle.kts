plugins {
    `java-library`
    jacoco
}

repositories {
    mavenCentral()
}

dependencies {
    implementation("com.google.guava:guava:26.0-jre")
    testImplementation("junit:junit:4.12")
}

val jacocoTestReport by tasks.named("jacocoTestReport")
jacocoTestReport.dependsOn(tasks.named("test"))