#!/bin/bash

BASE_DIR=$(git rev-parse --show-toplevel)
cd "$BASE_DIR/apps/web-app" || return

./gradlew spotlessApply
./gradlew check
