use std::collections::HashMap;
use std::sync::{Arc, Mutex};
use std::thread;
use std::time::Duration;
use std::fmt;
use std::fs::File;
use std::io::Write;
use std::error::Error;

// Trait definition
trait Printable {
    fn print(&self);
}

// Struct definition
#[derive(Debug, Clone)]
struct Person {
    name: String,
    age: u32,
}

// Impl block for struct
impl Person {
    // Associated function (static method)
    fn new(name: String, age: u32) -> Self {
        Person { name, age }
    }

    // Method
    fn describe(&self) -> String {
        format!("{} is {} years old", self.name, self.age)
    }
}

// Impl block for trait
impl Printable for Person {
    fn print(&self) {
        println!("{}", self.describe());
    }
}

// Enum definition
enum Message {
    Quit,
    Move { x: i32, y: i32 },
    Write(String),
    ChangeColor(i32, i32, i32),
}

// Custom error type
#[derive(Debug)]
struct CustomError(String);

impl fmt::Display for CustomError {
    fn fmt(&self, f: &mut fmt::Formatter) -> fmt::Result {
        write!(f, "CustomError: {}", self.0)
    }
}

impl Error for CustomError {}

// Generic function
fn print_type<T: fmt::Debug>(item: T) {
    println!("{:?}", item);
}

// Lifetime annotation
fn longest<'a>(x: &'a str, y: &'a str) -> &'a str {
    if x.len() > y.len() { x } else { y }
}

// Macro definition
macro_rules! say_hello {
    () => {
        println!("Hello!");
    };
    ($name:expr) => {
        println!("Hello, {}!", $name);
    };
}

// Async function
async fn fetch_data(url: &str) -> Result<String, Box<dyn Error>> {
    // Simulating an async operation
    tokio::time::sleep(Duration::from_secs(1)).await;
    Ok(format!("Data from {}", url))
}

#[tokio::main]
async fn main() -> Result<(), Box<dyn Error>> {
    // Variable binding
    let x = 5;
    let y = 10;

    // Mutable variable
    let mut mutable = 15;
    mutable += 1;

    // Constant
    const MAX_POINTS: u32 = 100_000;

    // Shadowing
    let spaces = "   ";
    let spaces = spaces.len();

    // Tuple
    let tup: (i32, f64, u8) = (500, 6.4, 1);
    let (a, b, c) = tup;

    // Array
    let array = [1, 2, 3, 4, 5];

    // Vector
    let mut vec = vec![1, 2, 3];
    vec.push(4);

    // String
    let mut s = String::from("hello");
    s.push_str(", world!");

    // Slice
    let slice = &s[0..5];

    // HashMap
    let mut scores = HashMap::new();
    scores.insert(String::from("Blue"), 10);

    // Control flow
    if x < y {
        println!("x is less than y");
    } else {
        println!("x is not less than y");
    }

    // Match expression
    let number = 13;
    match number {
        1 => println!("One!"),
        2 | 3 | 5 | 7 | 11 => println!("This is a prime"),
        13..=19 => println!("A teen"),
        _ => println!("Ain't special"),
    }

    // Loop
    let mut counter = 0;
    let result = loop {
        counter += 1;
        if counter == 10 {
            break counter * 2;
        }
    };

    // While loop
    while counter < 20 {
        counter += 1;
    }

    // For loop
    for element in array.iter() {
        println!("The value is: {}", element);
    }

    // Closure
    let add_one = |x: i32| x + 1;
    println!("Add one to 5: {}", add_one(5));

    // Struct instantiation
    let person = Person::new(String::from("Alice"), 30);

    // Trait usage
    person.print();

    // Enum usage
    let message = Message::Write(String::from("Hello"));

    // Pattern matching with enum
    match message {
        Message::Quit => println!("Quit"),
        Message::Move { x, y } => println!("Move to ({}, {})", x, y),
        Message::Write(text) => println!("Text message: {}", text),
        Message::ChangeColor(r, g, b) => println!("Change color to ({}, {}, {})", r, g, b),
    }

    // Result and error handling
    let result: Result<i32, &str> = Ok(200);
    match result {
        Ok(code) => println!("Success! Code: {}", code),
        Err(e) => println!("Error: {}", e),
    }

    // Unwrap and expect
    let unwrapped = result.unwrap();
    let expected = result.expect("Failed to get the value");

    // Option
    let some_number = Some(5);
    let absent_number: Option<i32> = None;

    // Generic function usage
    print_type(42);
    print_type("Hello, Rust!");

    // Lifetime usage
    let string1 = String::from("short");
    let string2 = String::from("longer");
    let result = longest(string1.as_str(), string2.as_str());
    println!("Longest string: {}", result);

    // Macro usage
    say_hello!();
    say_hello!("Rust");

    // Threading
    let counter = Arc::new(Mutex::new(0));
    let mut handles = vec![];

    for _ in 0..10 {
        let counter = Arc::clone(&counter);
        let handle = thread::spawn(move || {
            let mut num = counter.lock().unwrap();
            *num += 1;
        });
        handles.push(handle);
    }

    for handle in handles {
        handle.join().unwrap();
    }

    println!("Final count: {}", *counter.lock().unwrap());

    // File I/O
    let mut file = File::create("example.txt")?;
    file.write_all(b"Hello, Rust file I/O!")?;

    // Async/await
    let data = fetch_data("https://example.com").await?;
    println!("Fetched data: {}", data);

    // Custom error
    Err(CustomError("This is a custom error".into()))?;

    Ok(())
}
