val context = "myblog"
val servletVersion = "5.0.0"
val jstlVersion = "2.0.0"
val junitVersion = "5.8.2"
val log4jVersion = "2.17.1"
val fluentjVersion = "3.22.0"
val jSoupVersion = "1.14.3"
val deployment_port: String by project

plugins {
    java
    war
    id("com.bmuschko.cargo") version "2.8.0"
}

group = "com.knowledgespike"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
}

tasks.war {
    this.archiveFileName.set("${context}.war")
}


dependencies {
    compileOnly("jakarta.servlet:jakarta.servlet-api:${servletVersion}")
    implementation("jakarta.servlet.jsp.jstl:jakarta.servlet.jsp.jstl-api:${jstlVersion}")
    implementation("org.glassfish.web:jakarta.servlet.jsp.jstl:2.0.0")
    implementation("org.apache.logging.log4j:log4j-core:${log4jVersion}")
    implementation("org.apache.logging.log4j:log4j-api:${log4jVersion}")
    implementation("org.apache.logging.log4j:log4j-jakarta-web:${log4jVersion}")


    testImplementation("org.junit.jupiter:junit-jupiter-api:${junitVersion}")
    testImplementation("org.assertj:assertj-core:${fluentjVersion}")
    testImplementation("org.jsoup:jsoup:${jSoupVersion}")
    testRuntimeOnly("org.junit.jupiter:junit-jupiter-engine")
}

tasks.getByName<Test>("test") {
    useJUnitPlatform()
}

cargo {
    containerId = "tomee9x"
    port = deployment_port.toInt()

    withGroovyBuilder {
        "deployable" {
            "setContext"(context)
        }

        "remote" {
            "setHostname"("localhost")

            "setUsername"("tomee")
            "setPassword"("tomee")
        }
    }
}

tasks.test {
    useJUnitPlatform()
    testLogging {
        events("passed", "skipped", "failed")
    }
}

