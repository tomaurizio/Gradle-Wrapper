plugins {
    `java-library`
}

repositories {
    mavenCentral()
}

dependencies {
    implementation("com.google.guava:guava:26.0-jre")
    implementation("org.slf4j:slf4j-api:1.7.25")

    implementation("org.zeromq:jeromq:0.4.3")

    testImplementation("junit:junit:4.12")
    testImplementation("net.jodah:concurrentunit:0.4.3")

    runtimeOnly("ch.qos.logback:logback-classic:1.2.3")
}
