plugins {
    id("java")
}

group = "tt"
version = "1.0-SNAPSHOT"

repositories {
    mavenLocal()
    mavenCentral()
}

dependencies {
    testImplementation(platform("org.junit:junit-bom:5.10.0"))
    testImplementation("org.junit.jupiter:junit-jupiter")

    implementation("com.google.guava:guava:32.1.1-jre")
    implementation("quil:processing-core:3.5.4")
}

tasks.test {
    useJUnitPlatform()
}