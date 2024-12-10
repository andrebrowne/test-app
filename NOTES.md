## Upgrade Spring Boot 3.0 with OpenRewrite

*upgrade Spring Boot 3*
```bash
./mvnw -U org.openrewrite.maven:rewrite-maven-plugin:run -Drewrite.recipeArtifactCoordinates=org.openrewrite.recipe:rewrite-spring:RELEASE -Drewrite.activeRecipes=org.openrewrite.java.spring.boot3.UpgradeSpringBoot_3_0 -Drewrite.exportDatatables=true
```

*convert pojo to record*
Create `rewrite.yml` in project root wth the following contents:
```yaml
---
type: specs.openrewrite.org/v1beta/recipe
name: com.broadcom.springconsulting.ConvertPojoToRecordRecipe
recipeList:
  - com.broadcom.springconsulting.java.convertPojoToRecord.ConvertPojoToRecordRecipe:
      fullyQualifiedClassName: com.broadcom.springconsulting.testapp.web.TimeResponse
```
*NOTE:* `rewrite.yml` needs the `.yml` extension. `.yaml` isn't automatically recognized.

Execute the command
```bash
./mvnw -U org.openrewrite.maven:rewrite-maven-plugin:run -Drewrite.recipeArtifactCoordinates=com.broadcom.springconsulting:custom-recipes:1.0.0-SNAPSHOT -Drewrite.activeRecipes=com.broadcom.springconsulting.ConvertPojoToRecordRecipe -Drewrite.exportDatatables=true
```

*remove custom-annotations*
Create `rewrite.yml` in project root wth the following contents:
```yaml
---
type: specs.openrewrite.org/v1beta/recipe
name: com.broadcom.springconsulting.ConvertPojoToRecordRecipe
recipeList:
  - com.broadcom.springconsulting.java.replaceCustomAnnotations.ReplaceEndpointAdapterRecipe:
  - com.broadcom.springconsulting.java.replaceCustomAnnotations.ReplacePersistenceAdapterRecipe:
  - com.broadcom.springconsulting.java.replaceCustomAnnotations.ReplaceUseCaseRecipe:
  - org.openrewrite.maven.RemoveDependency:
      groupId: com.broadcom.springconsulting
      artifactId: custom-annotations
      scope: compile
```
```bash
./mvnw -U org.openrewrite.maven:rewrite-maven-plugin:run -Drewrite.recipeArtifactCoordinates=com.broadcom.springconsulting:custom-recipes:1.0.0 -Drewrite.activeRecipes=com.broadcom.springconsulting.java.replaceCustomAnnotations.ReplaceEndpointAdapterRecipe,com.broadcom.springconsulting.java.replaceCustomAnnotations.ReplacePersistenceAdapterRecipe,com.broadcom.springconsulting.java.replaceCustomAnnotations.ReplaceUseCaseRecipe,org.openrewrite.maven.RemoveDependency -Drewrite.exportDatatables=true
```
