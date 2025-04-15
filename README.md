# Automated Test Case Generation

This project applies **Category-Partition Testing** and **Metamorphic Testing** to two Java classes:
- `Quadratic.java`: Solves quadratic equations
- `DateHelper.java`: Parses and formats date strings

## 🧪 Testing Techniques

### 1. Category-Partition Testing
- Systematically divides input space into partitions
- Covers edge cases, boundaries, and invalid inputs
- Applied to both `Quadratic` and `DateHelper`

### 2. Metamorphic Testing
- Defines algebraic and formatting relations
- Generates follow-up test cases automatically
- Checks correctness without fixed expected output

## ✅ Tools Used

- **JUnit 5** for test execution
- **PIT** for mutation testing
- **JaCoCo** for structural (branch) coverage
- **Ant** for test automation

## 📊 Results Summary

| Class        | Branch Coverage | Mutation Score |
|--------------|-----------------|----------------|
| Quadratic    | 97%             | 50%            |
| DateHelper   | 100%            | 27%            |

## 🚀 Running the Project

```bash
# Compile and run with Ant
ant run-tests

# Run JaCoCo for coverage
./run_coverage.sh

# Run PIT for mutation testing
ant run-mutation
```

## Folder Structure
```bash
src/
├── main/java/
│   ├── Quadratic.java
│   └── DateHelper.java
├── test/java/
│   ├── CategoryPartitionTest.java
│   ├── MetamorphicTest.java
│   └── MutationImprovementTest.java
```


Feel free to fork or clone for experimentation.

