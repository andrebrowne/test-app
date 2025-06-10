# OpenRewrite Test App

## Quickstart

Clone this repo and execute the command [run-recipes.sh](./bin/run-recipes.sh):

```shell
git clone https://github.com/andrebrowne/test-app.git
cd test-app
chmod a+x ./bin/*
./bin/run-recipes.sh
```

## Step-by-step

### 1. Install `test-app`

```shell
./mvnw clean install
```

### 2. Run OpenRewrite via `maven`

#### Upgrade to Spring Boot 3.0 with OpenRewrite

Execute the command:

```bash
./mvnw -U org.openrewrite.maven:rewrite-maven-plugin:run \
  -Drewrite.recipeArtifactCoordinates=org.openrewrite.recipe:rewrite-spring:RELEASE \
  -Drewrite.activeRecipes=org.openrewrite.java.spring.boot3.UpgradeSpringBoot_3_0 \
  -Drewrite.exportDatatables=true
```

#### Convert POJOs to Java records

| **_NOTE_** | Update the following maven command lines with the correct version of the `custom-recipes` library (e.g. `1.0.0-SNAPSHOT`, `1.0.0`, `1.0.1`, etc.) |
|------------|---------------------------------------------------------------------------------------------------------------------------------------------------|

Execute the command:

```bash
./mvnw -U org.openrewrite.maven:rewrite-maven-plugin:run \
  -Drewrite.configLocation=config-files/convert-pojo-to-record.yml \
  -Drewrite.recipeArtifactCoordinates=com.broadcom.springconsulting:custom-recipes:1.0.2 \
  -Drewrite.activeRecipes=com.broadcom.springconsulting.ConvertPojoToRecordRecipe \
  -Drewrite.exportDatatables=true
```

#### Remove Dependencies and Replace Custom Annotations

Execute the command:

```bash
./mvnw -U org.openrewrite.maven:rewrite-maven-plugin:run \
  -Drewrite.configLocation=config-files/replace-custom-annotations-and-remove-dependency.yml \
  -Drewrite.recipeArtifactCoordinates=com.broadcom.springconsulting:custom-recipes:1.0.2 \
  -Drewrite.activeRecipes=com.broadcom.springconsulting.RemoveCustomAnnotations 
  -Drewrite.exportDatatables=true
```

## Alternate Approach Using `rewrite.yml`

Install the app with `./mvnw clean install` then configure OpenRewrite using the default `rewrite.yml` configuration file.

**NOTE:** `rewrite.yml` needs the `.yml` extension. `.yaml` isn't automatically recognized.

### Upgrade to Spring Boot 3.0 with OpenRewrite with `rewrite.yml`

This recipe requires no configuration so just execute the command documented [above](#upgrade-to-spring-boot-30-with-openrewrite)

#### Convert POJOs to Java records with `rewrite.yml`

Create `rewrite.yml` in project root wth the following contents:

```yaml
---
type: specs.openrewrite.org/v1beta/recipe
name: com.broadcom.springconsulting.ConvertPojoToRecordRecipe
recipeList:
  - com.broadcom.springconsulting.java.convertPojoToRecord.ConvertPojoToRecordRecipe:
      fullyQualifiedClassName: com.broadcom.springconsulting.testapp.web.TimeResponse
```

then execute the command:

```bash
./mvnw -U org.openrewrite.maven:rewrite-maven-plugin:run \
  -Drewrite.recipeArtifactCoordinates=com.broadcom.springconsulting:custom-recipes:1.0.2 \
  -Drewrite.activeRecipes=com.broadcom.springconsulting.ConvertPojoToRecordRecipe \
  -Drewrite.exportDatatables=true
```

#### Remove Dependencies and Replace Custom Annotations with `rewrite.yml`

Create `rewrite.yml` in project root wth the following contents:

```yaml
---
type: specs.openrewrite.org/v1beta/recipe
name: com.broadcom.springconsulting.RemoveCustomAnnotations
recipeList:
  - com.broadcom.springconsulting.java.replaceCustomAnnotations.ReplaceEndpointAdapterRecipe
  - com.broadcom.springconsulting.java.replaceCustomAnnotations.ReplacePersistenceAdapterRecipe
  - com.broadcom.springconsulting.java.replaceCustomAnnotations.ReplaceUseCaseRecipe
  - org.openrewrite.maven.RemoveDependency:
      groupId: com.broadcom.springconsulting
      artifactId: custom-annotations
      scope: compile
```

then execute the command:

```bash
./mvnw -U org.openrewrite.maven:rewrite-maven-plugin:run \
  -Drewrite.recipeArtifactCoordinates=com.broadcom.springconsulting:custom-recipes:1.0.2 \
  -Drewrite.activeRecipes=com.broadcom.springconsulting.RemoveCustomAnnotations \
  -Drewrite.exportDatatables=true
```
