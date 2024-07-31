# Kotlin Self Learning

---
## Practice A-ZZZ

### Related Class
```
├── src
    ├── main
        ├── kotlin
            ├── com.thoughtworks.kotlin_basic
                ├── exceptions
                    ├── MaxSizeExceededException    -- exception which is throwed beyond ZZZ
                ├── util
                    ├── ColumnUtil                  -- util class that converts numbers to column sequence labels
    ├── test
        ├── kotlin
            ├── com.thoughtworks.kotlin_basic
                ├── util
                    ├── ColumnUtilTest              -- unit test
```

### Run Code

```kotlin
val columnUtil = ColumnUtil()
// convertToAlphabeticalLabels takes two Int parameter and returns a string array
columnUtil.convertToAlphabeticalLabels(1, 2)
```

### Run UnitTests

use shell script or IDE to run ```ColumnUtilTest```
```shell
./gradlew test --tests com.thoughtworks.kotlin_basic.util.ColumnUtilTest
```

---
## Request and process data

### Run Code
run ```Main.kt``` or call the function in ```ProductView```
```kotlin
// request products and inventories, then print products with inventory information
ProductView.showProductsInfo()
```

### Run UnitTests

use shell script or IDE to run ```ColumnUtilTest```
```shell
./gradlew test --tests com.thoughtworks.kotlin_basic.util.ProductViewModelTest
```