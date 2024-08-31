<?php

declare(strict_types=1);

// Namespace declaration
namespace PHPShowcase;

// Use statements
use Exception;
use PDO;
use DateTime;
use Countable;
use ArrayAccess;

// Interface
interface Printable {
    public function printInfo(): string;
}

// Trait
trait Loggable {
    public function log(string $message): void {
        echo "Logging: $message\n";
    }
}

// Abstract class
abstract class Shape {
    abstract public function getArea(): float;
}

// Class definition with inheritance
class Rectangle extends Shape implements Printable, Countable, ArrayAccess {
    use Loggable;

    private float $width;
    private float $height;
    private array $properties = [];

    // Constructor
    public function __construct(float $width, float $height) {
        $this->width = $width;
        $this->height = $height;
    }

    // Method implementation
    public function getArea(): float {
        return $this->width * $this->height;
    }

    // Interface method implementation
    public function printInfo(): string {
        return "Rectangle: width = {$this->width}, height = {$this->height}";
    }

    // Magic method
    public function __toString(): string {
        return $this->printInfo();
    }

    // Countable interface implementation
    public function count(): int {
        return count($this->properties);
    }

    // ArrayAccess interface implementation
    public function offsetExists($offset): bool {
        return isset($this->properties[$offset]);
    }

    public function offsetGet($offset): mixed {
        return $this->properties[$offset] ?? null;
    }

    public function offsetSet($offset, $value): void {
        if (is_null($offset)) {
            $this->properties[] = $value;
        } else {
            $this->properties[$offset] = $value;
        }
    }

    public function offsetUnset($offset): void {
        unset($this->properties[$offset]);
    }
}

// Anonymous class
$circle = new class(5) extends Shape {
    private float $radius;

    public function __construct(float $radius) {
        $this->radius = $radius;
    }

    public function getArea(): float {
        return pi() * $this->radius ** 2;
    }
};

// Function with type hints and return type
function add(int $a, int $b): int {
    return $a + $b;
}

// Variadic function
function sum(...$numbers): int {
    return array_sum($numbers);
}

// Arrow function (PHP 7.4+)
$multiply = fn($a, $b) => $a * $b;

// Generator function
function countTo(int $n): Generator {
    for ($i = 1; $i <= $n; $i++) {
        yield $i;
    }
}

// Class constant
class MathConstants {
    public const PI = 3.14159;
}

// Enum (PHP 8.1+)
enum Status: string {
    case DRAFT = 'draft';
    case PUBLISHED = 'published';
    case ARCHIVED = 'archived';
}

// Error handling
function divide(int $a, int $b): float {
    if ($b === 0) {
        throw new Exception("Division by zero");
    }
    return $a / $b;
}

// Main execution
try {
    // Object creation and method calls
    $rectangle = new Rectangle(5, 3);
    echo $rectangle->printInfo() . "\n";
    echo "Area: " . $rectangle->getArea() . "\n";
    $rectangle->log("Rectangle created");

    // Using anonymous class
    echo "Circle area: " . $circle->getArea() . "\n";

    // Function calls
    echo "5 + 3 = " . add(5, 3) . "\n";
    echo "Sum of 1, 2, 3, 4, 5 = " . sum(1, 2, 3, 4, 5) . "\n";
    echo "4 * 7 = " . $multiply(4, 7) . "\n";

    // Generator usage
    foreach (countTo(5) as $number) {
        echo $number . " ";
    }
    echo "\n";

    // Class constant usage
    echo "PI: " . MathConstants::PI . "\n";

    // Enum usage
    $status = Status::PUBLISHED;
    echo "Status: " . $status->value . "\n";

    // Array manipulation
    $fruits = ['apple', 'banana', 'cherry'];
    $fruits[] = 'date';
    array_push($fruits, 'elderberry');
    echo "Fruits: " . implode(', ', $fruits) . "\n";

    // Associative array
    $person = [
        'name' => 'John Doe',
        'age' => 30,
        'city' => 'New York'
    ];
    echo "Person: " . json_encode($person) . "\n";

    // String manipulation
    $str = "Hello, World!";
    echo strtoupper($str) . "\n";
    echo substr($str, 0, 5) . "\n";
    echo str_replace("World", "PHP", $str) . "\n";

    // Date and time
    $now = new DateTime();
    echo "Current date and time: " . $now->format('Y-m-d H:i:s') . "\n";

    // File operations
    file_put_contents('test.txt', 'Hello, PHP!');
    echo "File contents: " . file_get_contents('test.txt') . "\n";

    // Database operations (PDO)
    $pdo = new PDO('sqlite::memory:');
    $pdo->exec("CREATE TABLE users (id INTEGER PRIMARY KEY, name TEXT)");
    $pdo->exec("INSERT INTO users (name) VALUES ('Alice')");
    $stmt = $pdo->query("SELECT * FROM users");
    $user = $stmt->fetch(PDO::FETCH_ASSOC);
    echo "User: " . $user['name'] . "\n";

    // Error handling
    echo "10 / 2 = " . divide(10, 2) . "\n";
    echo "10 / 0 = " . divide(10, 0) . "\n"; // This will throw an exception

} catch (Exception $e) {
    echo "Caught exception: " . $e->getMessage() . "\n";
} finally {
    echo "Execution completed\n";
}

// Closure
$greeting = function($name) {
    return "Hello, $name!";
};
echo $greeting("PHP") . "\n";

// Variable variables
$foo = 'bar';
$$foo = 'baz';
echo $bar . "\n"; // Outputs: baz

// Heredoc syntax
$name = "World";
echo <<<EOT
Hello, $name!
This is a heredoc string.
EOT;

// Nowdoc syntax
echo <<<'EOT'
This is a nowdoc string.
Variables like $name are not interpolated.
EOT;

// Null coalescing operator
$username = $_GET['user'] ?? 'Guest';

// Spaceship operator
echo 1 <=> 2 . "\n"; // Outputs: -1
echo 2 <=> 2 . "\n"; // Outputs: 0
echo 2 <=> 1 . "\n"; // Outputs: 1

// Null coalescing assignment operator (PHP 7.4+)
$array['key'] ??= 'default';

// Match expression (PHP 8.0+)
$result = match ($status) {
    Status::DRAFT => 'The post is a draft',
    Status::PUBLISHED => 'The post is published',
    Status::ARCHIVED => 'The post is archived',
};
echo $result . "\n";

// Named arguments (PHP 8.0+)
function greet(string $name, string $greeting = 'Hello'): string {
    return "$greeting, $name!";
}
echo greet(greeting: 'Hi', name: 'PHP') . "\n";

// Attributes (PHP 8.0+)
#[Attribute]
class RouteAttribute {
    public function __construct(public string $path) {}
}

#[RouteAttribute('/home')]
function home() {
    return 'Welcome home!';
}

// Reflection
$reflection = new ReflectionFunction('home');
$attributes = $reflection->getAttributes(RouteAttribute::class);
foreach ($attributes as $attribute) {
    echo "Route: " . $attribute->newInstance()->path . "\n";
}
