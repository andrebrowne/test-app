#!/bin/bash
set -eu

# https://docs.openrewrite.org/reference/rewrite-maven-plugin
# https://docs.openrewrite.org/recipes
# https://docs.openrewrite.org/recipes/java
# https://docs.openrewrite.org/recipes/java/spring
# https://docs.openrewrite.org/recipes/java/spring/data

./mvnw \
  -U org.openrewrite.maven:rewrite-maven-plugin:run \
  -D skipTests \
  -D rewrite.exportDatatables=true \
  -D rewrite.recipeArtifactCoordinates=org.openrewrite.recipe:rewrite-spring:RELEASE \
  -D rewrite.activeRecipes=org.openrewrite.java.spring.boot3.UpgradeSpringBoot_3_3 \

#./mvnw clean test --quiet

./mvnw \
  -U org.openrewrite.maven:rewrite-maven-plugin:run \
  -D skipTests \
  -D rewrite.exportDatatables=true \
  -D rewrite.recipeArtifactCoordinates=org.openrewrite.recipe:rewrite-spring:RELEASE \
  -D rewrite.activeRecipes=org.openrewrite.java.spring.boot3.UpgradeSpringBoot_3_4 \

#./mvnw clean test --quiet

./mvnw \
  -U org.openrewrite.maven:rewrite-maven-plugin:run \
  -D skipTests \
  -D rewrite.recipeArtifactCoordinates=org.openrewrite.recipe:rewrite-spring:RELEASE \
  -D rewrite.activeRecipes=org.openrewrite.java.spring.ExpandProperties \
  -D rewrite.exportDatatables=true \

#./mvnw clean test --quiet

./mvnw \
  -U org.openrewrite.maven:rewrite-maven-plugin:run \
  -D skipTests \
  -D rewrite.recipeArtifactCoordinates=org.openrewrite.recipe:rewrite-migrate-java:RELEASE \
  -D rewrite.activeRecipes=org.openrewrite.java.migrate.UpgradeBuildToJava21 \
  -D rewrite.exportDatatables=true \

#./mvnw clean test --quiet

#./mvnw \
#  -U org.openrewrite.maven:rewrite-maven-plugin:run \
#  -D skipTests \
#  -D rewrite.recipeArtifactCoordinates=org.openrewrite.recipe:rewrite-spring:RELEASE \
#  -D rewrite.activeRecipes=org.openrewrite.java.spring.boot3.ReplaceMockBeanAndSpyBean \
#  -D rewrite.exportDatatables=true \
#

#./mvnw \
#  -U org.openrewrite.maven:rewrite-maven-plugin:run \
#  -D skipTests \
#  -Drewrite.recipeArtifactCoordinates=org.openrewrite.recipe:rewrite-migrate-java:RELEASE \
#  -Drewrite.activeRecipes=org.openrewrite.java.migrate.lombok.LombokBestPractices \
#  -Drewrite.exportDatatables=true

./mvnw clean test --quiet
