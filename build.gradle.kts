plugins {
    application
    java
}

group = "com.exemplo"
version = "1.0"

repositories {
    mavenCentral()
}

dependencies {
    implementation("io.javalin:javalin:5.6.2")
    implementation("com.fasterxml.jackson.core:jackson-databind:2.17.0")
    implementation("org.slf4j:slf4j-simple:2.0.9")   // <— adicione isto
    testImplementation("org.junit.jupiter:junit-jupiter:5.10.2")
}


application {
    mainClass.set("org.example.Main") // ajuste se estiver em um pacote
}

java {
    sourceCompatibility = JavaVersion.VERSION_17
    targetCompatibility = JavaVersion.VERSION_17
}

tasks.test {
    useJUnitPlatform()
}
