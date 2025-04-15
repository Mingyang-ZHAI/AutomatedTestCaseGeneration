#!/bin/bash

# Directory configuration
SRC_MAIN=src/main/java
SRC_TEST=src/test/java
BIN_MAIN=bin/main
BIN_TEST=bin/test
LIB_DIR=lib
REPORT_DIR=coverage-report
EXEC_FILE=coverage.exec

# Clean previous outputs
rm -rf "$BIN_MAIN" "$BIN_TEST" "$REPORT_DIR" "$EXEC_FILE"
mkdir -p "$BIN_MAIN" "$BIN_TEST"

# Step 1: Compile main Java source files
echo "[1] Compiling main Java classes..."
javac -d "$BIN_MAIN" -cp "$LIB_DIR/*" $(find "$SRC_MAIN" -name "*.java")
if [ $? -ne 0 ]; then
  echo "Main class compilation failed"
  exit 1
fi

# Step 2: Compile test Java source files
echo "[2] Compiling test Java classes..."
javac -d "$BIN_TEST" -cp "$BIN_MAIN:$LIB_DIR/*" $(find "$SRC_TEST" -name "*.java")
if [ $? -ne 0 ]; then
  echo "Test class compilation failed"
  exit 1
fi

# Step 3: Run tests with JaCoCo agent and JUnit 5
echo "[3] Running JUnit 5 tests with JaCoCo agent..."
java -javaagent:"$LIB_DIR/jacocoagent.jar=destfile=$EXEC_FILE" \
     -jar "$LIB_DIR/junit-platform-console-standalone-1.9.3.jar" \
     --class-path "$BIN_MAIN:$BIN_TEST" \
     --scan-class-path

# Step 4: Generate JaCoCo HTML report (only for main classes)
echo "[4] Generating JaCoCo HTML report..."
java -jar "$LIB_DIR/jacococli.jar" report "$EXEC_FILE" \
  --classfiles "$BIN_MAIN" \
  --sourcefiles "$SRC_MAIN" \
  --html "$REPORT_DIR" \
  --name "JaCoCo Report"

# Completion message
echo "Coverage report generated at $REPORT_DIR/index.html"
