import kotlinx.coroutines.*
import java.io.File
import kotlin.properties.Delegates
import kotlin.reflect.KProperty

// Interface
interface Printable {
    fun print()
}

// Abstract class
abstract class Shape {
    abstract fun area(): Double
}

// Data class
data class Person(val name: String, var age: Int)

// Sealed class
sealed class Result {
    data class Success(val data: String) : Result()
    data class Error(val message: String) : Result()
}

// Enum class
enum class Direction {
    NORTH, SOUTH, EAST, WEST
}

// Object declaration (Singleton)
object DatabaseConnection {
    fun connect() = println("Connected to database")
}

// Companion object
class MyClass {
    companion object {
        fun create(): MyClass = MyClass()
    }
}

// Extension function
fun String.removeFirstAndLast() = this.substring(1, this.length - 1)

// Higher-order function
fun operation(x: Int, y: Int, op: (Int, Int) -> Int): Int = op(x, y)

// Infix function
infix fun Int.plusAndSquare(other: Int) = (this + other) * (this + other)

// Operator overloading
data class Point(val x: Int, val y: Int) {
    operator fun plus(other: Point) = Point(x + other.x, y + other.y)
}

// Property delegation
class Example {
    var p: String by Delegate()
}

class Delegate {
    operator fun getValue(thisRef: Any?, property: KProperty<*>): String {
        return "$thisRef, thank you for delegating '${property.name}' to me!"
    }

    operator fun setValue(thisRef: Any?, property: KProperty<*>, value: String) {
        println("$value has been assigned to '${property.name}' in $thisRef.")
    }
}

// Lazy property
val lazyValue: String by lazy {
    println("Computed!")
    "Hello"
}

// Observable property
var observableProperty: Int by Delegates.observable(0) { prop, old, new ->
    println("$old -> $new")
}

// Class with primary and secondary constructors
class Rectangle(val height: Int, val width: Int) {
    constructor(size: Int) : this(size, size)

    fun area() = height * width
}

// Function with default arguments
fun greet(name: String = "World") = println("Hello, $name!")

// Null safety
fun nullSafetyDemo(str: String?) {
    // Safe call
    println(str?.length)

    // Elvis operator
    val length = str?.length ?: -1

    // Not-null assertion
    println(str!!.length)
}

// Smart cast
fun smartCastDemo(x: Any) {
    if (x is String) {
        print(x.length) // x is automatically cast to String
    }
}

// When expression
fun describe(obj: Any): String =
    when (obj) {
        1 -> "One"
        "Hello" -> "Greeting"
        is Long -> "Long"
        !is String -> "Not a string"
        else -> "Unknown"
    }

// Coroutine
suspend fun fetchData(): String {
    delay(1000)
    return "Data"
}

// Main function
suspend fun main() {
    // Val and var
    val immutable = 42
    var mutable = 42
    mutable = 43

    // String template
    println("Immutable: $immutable, Mutable: $mutable")

    // Range and for loop
    for (i in 1..5) {
        println(i)
    }

    // List
    val fruits = listOf("apple", "banana", "kiwi")

    // Map
    val ages = mapOf("Alice" to 20, "Bob" to 22)

    // Lambda expression
    fruits.filter { it.length > 5 }
        .sortedBy { it }
        .map { it.uppercase() }
        .forEach { println(it) }

    // Destructuring declaration
    val (name, age) = Person("Alice", 20)

    // Use of interface
    val printablePerson = object : Printable {
        override fun print() = println("Printable object")
    }
    printablePerson.print()

    // Use of data class
    val person1 = Person("Alice", 20)
    val person2 = person1.copy(age = 21)

    // Use of sealed class
    val result: Result = Result.Success("Operation successful")
    when (result) {
        is Result.Success -> println(result.data)
        is Result.Error -> println(result.message)
    }

    // Use of enum
    val direction = Direction.NORTH

    // Use of object declaration
    DatabaseConnection.connect()

    // Use of companion object
    val myClass = MyClass.create()

    // Use of extension function
    println("Hello".removeFirstAndLast())

    // Use of higher-order function
    println(operation(2, 3) { a, b -> a + b })

    // Use of infix function
    println(2 plusAndSquare 3)

    // Use of operator overloading
    val point1 = Point(1, 2)
    val point2 = Point(3, 4)
    println(point1 + point2)

    // Use of property delegation
    val e = Example()
    e.p = "NEW"

    // Use of lazy property
    println(lazyValue)
    println(lazyValue)

    // Use of observable property
    observableProperty = 1
    observableProperty = 2

    // Use of class with multiple constructors
    val square = Rectangle(5)
    println(square.area())

    // Use of function with default arguments
    greet()
    greet("Kotlin")

    // Null safety demo
    nullSafetyDemo("Kotlin")
    nullSafetyDemo(null)

    // Smart cast demo
    smartCastDemo("Hello")

    // When expression demo
    println(describe(1))
    println(describe("Hello"))
    println(describe(4L))
    println(describe(listOf(1, 2, 3)))

    // Coroutine demo
    runBlocking {
        launch {
            println("Coroutine: ${fetchData()}")
        }
    }

    // File I/O
    File("test.txt").writeText("Hello, Kotlin!")
    println(File("test.txt").readText())

    // Try-catch expression
    val result2 = try {
        "2".toInt()
    } catch (e: NumberFormatException) {
        null
    }
    println(result2)
}
