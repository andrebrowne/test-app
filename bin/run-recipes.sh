#!/bin/bash
set -eu

check_and_build_library() {
    local lib_group_id="$1"
    local lib_artifact_id="$2"
    local lib_version="$3"
    local git_repo_url="$4"

    # Validate required parameters
    if [[ -z "$lib_group_id" || -z "$lib_artifact_id" || -z "$lib_version" || -z "$git_repo_url" ]]; then
        echo "Error: Missing required parameters"
        echo "Usage: check_and_build_library <group_id> <artifact_id> <version> <git_repo_url>"
        return 1
    fi

    # Configuration
    local local_m2_repo="$HOME/.m2/repository"
    local lib_path="$local_m2_repo/$(echo "$lib_group_id" | tr '.' '/')/$lib_artifact_id/$lib_version/$lib_artifact_id-$lib_version.jar"
    local clone_dir="/tmp/$(basename "$git_repo_url" .git)-$(date +%Y%m%d-%H%M%S)"

    # Check if the library exists
    if [[ -f "$lib_path" ]]; then
        echo "Library found at: $lib_path"
        return 0
    else
        echo "Library $lib_group_id:$lib_artifact_id:$lib_version not found. Cloning and building..."

        # Clone the repository
        git clone --depth 1 --branch "$lib_version" --single-branch "$git_repo_url" "$clone_dir"
        if [[ $? -ne 0 ]]; then
            echo "Failed to clone repository."
            return 1
        fi

        # Build and install the library
        pushd "$clone_dir" > /dev/null || return 1
        ./mvnw clean install -DskipTests --quiet
        local build_status=$?
        popd > /dev/null

        # Clean up clone directory
        rm -rf "$clone_dir"

        if [[ $build_status -ne 0 ]]; then
            echo "Build failed."
            return 1
        fi

        echo "Library successfully built and installed."
        return 0
    fi
}

check_and_build_library "com.broadcom.springconsulting" "custom-annotations" "1.0.1" "https://github.com/andrebrowne/custom-annotations.git"
check_and_build_library "com.broadcom.springconsulting" "custom-recipes" "1.0.2" "https://github.com/andrebrowne/custom-recipes.git"
./mvnw clean install -DskipTests --quiet
echo "Checking for local recipes"
echo "Running UpgradeSpringBoot_3_0 recipe..."
./mvnw \
  -U org.openrewrite.maven:rewrite-maven-plugin:run \
  -D skipTests \
  -D rewrite.exportDatatables=true \
  -D rewrite.recipeArtifactCoordinates=org.openrewrite.recipe:rewrite-spring:RELEASE \
  -D rewrite.activeRecipes=org.openrewrite.java.spring.boot3.UpgradeSpringBoot_3_0

echo "Running ConvertPojoToRecordRecipe recipe..."
./mvnw \
  -U org.openrewrite.maven:rewrite-maven-plugin:run \
  -D skipTests \
  -D rewrite.exportDatatables=true \
  -D rewrite.recipeArtifactCoordinates=com.broadcom.springconsulting:custom-recipes:1.0.2 \
  -D rewrite.activeRecipes=com.broadcom.springconsulting.ConvertPojoToRecordRecipe \
  -D rewrite.configLocation=config-files/convert-pojo-to-record.yml

echo "Running RemoveCustomAnnotations recipe..."
./mvnw \
  -U org.openrewrite.maven:rewrite-maven-plugin:run \
  -D skipTests \
  -D rewrite.exportDatatables=true \
  -D rewrite.recipeArtifactCoordinates=com.broadcom.springconsulting:custom-recipes:1.0.2 \
  -D rewrite.activeRecipes=com.broadcom.springconsulting.RemoveCustomAnnotations \
  -D rewrite.configLocation=config-files/replace-custom-annotations-and-remove-dependency.yml

echo "Validating changes"
./mvnw clean test --quiet
git --no-pager diff
git add .
git status
