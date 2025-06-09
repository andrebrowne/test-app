#!/bin/bash
set -eu
cat << EOF > rewrite.yml
---
type: specs.openrewrite.org/v1beta/recipe
name: com.example.ConvertPojoToRecordRecipe
recipeList:
  - com.example.java.convertPojoToRecord.ConvertPojoToRecordRecipe:
      fullyQualifiedClassName: com.example.testapp.web.TimeResponse
EOF
