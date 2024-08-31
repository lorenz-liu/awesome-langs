package main

import (
    "context"
    "encoding/json"
    "fmt"
    "io"
    "log"
    "net/http"
    "os"
    "sync"
    "time"
)

// Interface
type Printer interface {
    Print() string
}

// Struct
type Person struct {
    Name string `json:"name"`
    Age  int    `json:"age"`
}

// Method
func (p Person) Print() string {
    return fmt.Sprintf("%s is %d years old", p.Name, p.Age)
}

// Function with multiple return values
func divide(a, b float64) (float64, error) {
    if b == 0 {
        return 0, fmt.Errorf("division by zero")
    }
    return a / b, nil
}

// Variadic function
func sum(nums ...int) int {
    total := 0
    for _, num := range nums {
        total += num
    }
    return total
}

// Closure
func counter() func() int {
    count := 0
    return func() int {
        count++
        return count
    }
}

// Goroutine and channel
func worker(id int, jobs <-chan int, results chan<- int) {
    for j := range jobs {
        fmt.Printf("worker %d started job %d\n", id, j)
        time.Sleep(time.Second)
        fmt.Printf("worker %d finished job %d\n", id, j)
        results <- j * 2
    }
}

// Error handling
func checkError(err error) {
    if err != nil {
        log.Fatal(err)
    }
}

// Defer
func deferExample() {
    defer fmt.Println("This will be printed last")
    fmt.Println("This will be printed first")
}

// Interface implementation
type CustomWriter struct{}

func (cw CustomWriter) Write(p []byte) (n int, err error) {
    fmt.Print(string(p))
    return len(p), nil
}

// Struct embedding
type Employee struct {
    Person
    Position string
}

// Type assertion
func printType(i interface{}) {
    switch v := i.(type) {
    case int:
        fmt.Printf("Integer: %v\n", v)
    case string:
        fmt.Printf("String: %v\n", v)
    default:
        fmt.Printf("Unknown type: %T\n", v)
    }
}

func main() {
    // Variable declaration
    var name string = "John"
    age := 30

    // Pointer
    agePtr := &age
    fmt.Printf("Age: %d\n", *agePtr)

    // Struct initialization
    person := Person{Name: name, Age: age}

    // JSON marshaling
    jsonData, err := json.Marshal(person)
    checkError(err)
    fmt.Printf("JSON: %s\n", jsonData)

    // JSON unmarshaling
    var decodedPerson Person
    err = json.Unmarshal(jsonData, &decodedPerson)
    checkError(err)

    // Interface usage
    var printer Printer = person
    fmt.Println(printer.Print())

    // Error handling
    result, err := divide(10, 2)
    if err != nil {
        log.Printf("Error: %v\n", err)
    } else {
        fmt.Printf("Result: %.2f\n", result)
    }

    // Slice
    numbers := []int{1, 2, 3, 4, 5}
    fmt.Printf("Sum: %d\n", sum(numbers...))

    // Map
    colors := map[string]string{
        "red":   "#ff0000",
        "green": "#00ff00",
        "blue":  "#0000ff",
    }
    fmt.Printf("Red: %s\n", colors["red"])

    // For loop
    for i := 0; i < 3; i++ {
        fmt.Printf("Iteration %d\n", i)
    }

    // Range loop
    for key, value := range colors {
        fmt.Printf("%s: %s\n", key, value)
    }

    // Closure usage
    increment := counter()
    fmt.Println(increment())
    fmt.Println(increment())

    // Goroutines and channels
    jobs := make(chan int, 100)
    results := make(chan int, 100)

    for w := 1; w <= 3; w++ {
        go worker(w, jobs, results)
    }

    for j := 1; j <= 5; j++ {
        jobs <- j
    }
    close(jobs)

    for a := 1; a <= 5; a++ {
        <-results
    }

    // Defer
    deferExample()

    // Interface implementation
    customWriter := CustomWriter{}
    fmt.Fprintf(customWriter, "Custom writer output\n")

    // Struct embedding
    employee := Employee{
        Person:   Person{Name: "Alice", Age: 28},
        Position: "Developer",
    }
    fmt.Printf("Employee: %s, Position: %s\n", employee.Name, employee.Position)

    // Type assertion
    printType(42)
    printType("Hello")
    printType(true)

    // Context usage
    ctx, cancel := context.WithTimeout(context.Background(), 2*time.Second)
    defer cancel()

    req, err := http.NewRequestWithContext(ctx, "GET", "http://example.com", nil)
    checkError(err)

    client := &http.Client{}
    resp, err := client.Do(req)
    if err != nil {
        fmt.Printf("Request failed: %v\n", err)
    } else {
        defer resp.Body.Close()
        body, err := io.ReadAll(resp.Body)
        checkError(err)
        fmt.Printf("Response: %s\n", body)
    }

    // WaitGroup for synchronization
    var wg sync.WaitGroup
    for i := 0; i < 3; i++ {
        wg.Add(1)
        go func(id int) {
            defer wg.Done()
            fmt.Printf("Goroutine %d finished\n", id)
        }(i)
    }
    wg.Wait()

    // Recover from panic
    defer func() {
        if r := recover(); r != nil {
            fmt.Printf("Recovered from panic: %v\n", r)
        }
    }()

    // File operations
    file, err := os.Create("example.txt")
    checkError(err)
    defer file.Close()

    _, err = file.WriteString("Hello, Go!")
    checkError(err)

    fmt.Println("File written successfully")

    // Panic (uncomment to test)
    // panic("This is a panic!")
}
