plugins {
    `java-library`
    jacoco
    `build-dashboard`
}

repositories {
    mavenCentral()
}

dependencies {
    implementation("com.google.guava:guava:26.0-jre")
    implementation("org.slf4j:slf4j-api:1.7.25")

    testImplementation("junit:junit:4.12")
    testImplementation("net.jodah:concurrentunit:0.4.3")

    runtimeOnly("ch.qos.logback:logback-classic:1.2.3")
}

val jacocoTestReport by tasks.named("jacocoTestReport")
jacocoTestReport.dependsOn(tasks.named("test"))

val buildDashboard by tasks.named("buildDashboard")
buildDashboard.dependsOn(jacocoTestReport)
