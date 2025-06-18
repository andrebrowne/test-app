#!/bin/bash
set -eu
cat << EOF > rewrite.yml
---
type: specs.openrewrite.org/v1beta/recipe
name: com.broadcom.springconsulting.ConvertPojoToRecordRecipe
recipeList:
  - com.broadcom.springconsulting.java.convertPojoToRecord.ConvertPojoToRecordRecipe:
      fullyQualifiedClassName: com.broadcom.springconsulting.testapp.web.TimeResponse
EOF
