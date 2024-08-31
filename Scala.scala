import scala.util.{Try, Success, Failure}
import scala.concurrent.{Future, Await}
import scala.concurrent.ExecutionContext.Implicits.global
import scala.concurrent.duration._
import scala.language.postfixOps

// Object (Singleton)
object ScalaShowcase {
  // Main method
  def main(args: Array[String]): Unit = {
    // Trait
    trait Printable {
      def print(): Unit
    }

    // Class definition with constructor parameters
    class Person(val name: String, var age: Int) extends Printable {
      // Method overriding
      override def print(): Unit = println(s"Name: $name, Age: $age")

      // Auxiliary constructor
      def this(name: String) = this(name, 0)
    }

    // Case class
    case class Point(x: Int, y: Int)

    // Object (companion object)
    object Person {
      def apply(name: String): Person = new Person(name)
    }

    // Abstract class
    abstract class Shape {
      def area: Double
    }

    // Class inheritance
    class Circle(radius: Double) extends Shape {
      override def area: Double = math.Pi * radius * radius
    }

    // Traits with default implementations
    trait Loggable {
      def log(message: String): Unit = println(s"Log: $message")
    }

    // Mixing in traits
    class LoggablePerson(name: String) extends Person(name) with Loggable {
      def logInfo(): Unit = log(s"Person info - Name: $name")
    }

    // Value classes
    class Wrapper(val underlying: Int) extends AnyVal {
      def doubled: Int = underlying * 2
    }

    // Enumerations
    object WeekDay extends Enumeration {
      type WeekDay = Value
      val Mon, Tue, Wed, Thu, Fri, Sat, Sun = Value
    }

    // Pattern matching
    def describe(x: Any): String = x match {
      case 5 => "five"
      case true => "truth"
      case "hello" => "hi!"
      case Nil => "the empty list"
      case _ => "something else"
    }

    // Higher-order functions
    def twice(f: Int => Int, x: Int) = f(f(x))

    // Partial functions
    val divide: PartialFunction[Int, Int] = {
      case d: Int if d != 0 => 42 / d
    }

    // Currying
    def multiply(x: Int)(y: Int) = x * y

    // By-name parameters
    def byNameAssert(predicate: => Boolean) =
      if (!predicate) throw new AssertionError

    // Implicit parameters and conversions
    implicit def int2Integer(x: Int) = Integer.valueOf(x)
    def takesInteger(x: Integer) = println(s"Took an Integer: $x")

    // Type aliases
    type Result[T] = Either[String, T]

    // Generics
    def identity[T](x: T): T = x

    // Variance
    class Box[+T](val content: T)

    // Lazy evaluation
    lazy val x = { println("Computing x"); 5 }

    // For comprehensions
    val pairs = for {
      i <- 1 to 3
      j <- 1 to i
    } yield (i, j)

    // Try, Success, and Failure
    def divide(x: Int, y: Int): Try[Int] = Try(x / y)

    // Futures
    def longRunningTask(): Future[Int] = Future {
      Thread.sleep(1000)
      42
    }

    // Partial application
    val add = (x: Int, y: Int) => x + y
    val add5 = add(5, _: Int)

    // Structural types
    def useClose(closeable: { def close(): Unit }): Unit = {
      closeable.close()
    }

    // Self-type annotations
    trait User {
      def username: String
    }
    trait Tweeter {
      self: User =>  // self-type annotation
      def tweet(tweetText: String) = println(s"$username: $tweetText")
    }

    // Path-dependent types
    class Outer {
      class Inner
      def createInner: Inner = new Inner
    }

    // Existential types
    def printContents(l: List[_]): Unit = l.foreach(println)

    // Implicit classes (extension methods)
    implicit class StringOps(s: String) {
      def encrypt: String = s.map(c => (c + 1).toChar)
    }

    // Type classes
    trait Stringify[T] {
      def stringify(x: T): String
    }
    implicit object IntStringify extends Stringify[Int] {
      def stringify(x: Int): String = x.toString
    }
    def print[T](x: T)(implicit stringifier: Stringify[T]): Unit = {
      println(stringifier.stringify(x))
    }

    // Usage examples
    val person = new Person("Alice", 30)
    person.print()

    val point = Point(3, 4)
    println(s"Point: $point")

    val circle = new Circle(5.0)
    println(s"Circle area: ${circle.area}")

    val logPerson = new LoggablePerson("Bob")
    logPerson.logInfo()

    val wrapper = new Wrapper(5)
    println(s"Doubled: ${wrapper.doubled}")

    println(s"Today is ${WeekDay.Mon}")

    println(describe(5))

    println(twice(_ + 3, 7))

    println(divide(10))

    println(multiply(3)(4))

    byNameAssert(5 > 3)

    takesInteger(5)  // Uses implicit conversion

    val result: Result[Int] = Right(42)

    println(identity("Hello"))

    val box = new Box("content")

    println(s"Lazy x: $x")

    println(s"Pairs: $pairs")

    divide(10, 2) match {
      case Success(result) => println(s"Division result: $result")
      case Failure(exception) => println(s"Division failed: ${exception.getMessage}")
    }

    val futureResult = longRunningTask()
    println(s"Future result: ${Await.result(futureResult, 2 seconds)}")

    println(s"add5(10) = ${add5(10)}")

    useClose(new { def close() = println("Closed!") })

    val outer = new Outer
    val inner: outer.Inner = outer.createInner

    printContents(List(1, "two", 3.0))

    println("hello".encrypt)

    print(42)  // Uses type class

    // Placeholder syntax
    val isEven = (_ % 2 == 0)
    println(s"Is 4 even? ${isEven(4)}")

    // String interpolation
    val name = "World"
    println(s"Hello, $name!")
    println(f"Pi is approximately ${math.Pi}%.3f")

    // Multi-line strings
    val poem = """
      |Roses are red,
      |Violets are blue,
      |Scala is awesome,
      |And so are you!
      """.stripMargin
    println(poem)

    // Pattern matching in variable definitions
    val (a, b) = (1, 2)
    println(s"a = $a, b = $b")

    // Case classes in pattern matching
    val somePoint = Point(10, 20)
    somePoint match {
      case Point(x, y) if x > y => println(s"x > y: $x > $y")
      case Point(x, y) => println(s"x <= y: $x <= $y")
    }

    // Infix notation for methods with one argument
    println(1 to 5)
    println(1.to(5))  // equivalent

    // Tail recursion
    @annotation.tailrec
    def factorial(n: Int, acc: Int = 1): Int = {
      if (n <= 1) acc
      else factorial(n - 1, n * acc)
    }
    println(s"Factorial of 5: ${factorial(5)}")

    // Partially applied functions
    def log(date: String)(message: String) = println(s"$date: $message")
    val todayLog = log("2023-05-20") _
    todayLog("Scala is fun!")

    // Varargs
    def sum(numbers: Int*) = numbers.sum
    println(s"Sum: ${sum(1, 2, 3, 4, 5)}")

    // Named arguments
    def greet(name: String, greeting: String = "Hello") = s"$greeting, $name!"
    println(greet(greeting = "Hi", name = "Scala"))

    // By-name parameters for custom control structures
    def myWhile(condition: => Boolean)(body: => Unit): Unit = {
      if (condition) {
        body
        myWhile(condition)(body)
      }
    }
    var i = 0
    myWhile (i < 5) {
      println(s"i = $i")
      i += 1
    }
  }
}

// Run the showcase
ScalaShowcase.main(Array.empty)
