## Upgrade Spring Boot 3.0 with OpenRewrite

```bash
./mvnw -U org.openrewrite.maven:rewrite-maven-plugin:run -Drewrite.recipeArtifactCoordinates=org.openrewrite.recipe:rewrite-spring:RELEASE -Drewrite.activeRecipes=org.openrewrite.java.spring.boot3.UpgradeSpringBoot_3_0 -Drewrite.exportDatatables=true
```