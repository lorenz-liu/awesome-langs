using System;
using System.Collections.Generic;
using System.Linq;
using System.Threading.Tasks;
using System.Text.Json;
using System.Text.RegularExpressions;

// Namespace declaration
namespace CSharpShowcase
{
    // Interface declaration
    public interface IExample
    {
        void ExampleMethod();
    }

    // Enum
    public enum Days
    {
        Monday,
        Tuesday,
        Wednesday,
        Thursday,
        Friday,
        Saturday,
        Sunday
    }

    // Class declaration with inheritance and interface implementation
    public class Example : IExample
    {
        // Properties
        public int Id { get; set; }
        public string Name { get; set; } = string.Empty;

        // Auto-implemented property
        public DateTime CreatedDate { get; } = DateTime.Now;

        // Field
        private readonly int _privateField;

        // Constructor
        public Example(int id)
        {
            Id = id;
            _privateField = 42;
        }

        // Method implementation
        public void ExampleMethod()
        {
            Console.WriteLine("Example method called");
        }

        // Static method
        public static void StaticMethod()
        {
            Console.WriteLine("Static method called");
        }

        // Generic method
        public T GenericMethod<T>(T value) where T : struct
        {
            return value;
        }

        // Async method
        public async Task<int> AsyncMethod()
        {
            await Task.Delay(1000);
            return 42;
        }

        // Expression-bodied method
        public override string ToString() => $"Example: {Id}";

        // Indexer
        private string[] _data = new string[10];
        public string this[int index]
        {
            get => _data[index];
            set => _data[index] = value;
        }

        // Event
        public event EventHandler? SomeEvent;

        // Protected virtual method
        protected virtual void OnSomeEvent()
        {
            SomeEvent?.Invoke(this, EventArgs.Empty);
        }
    }

    // Record type (C# 9.0+)
    public record Person(string FirstName, string LastName);

    // Struct
    public struct Point
    {
        public int X;
        public int Y;

        public Point(int x, int y) => (X, Y) = (x, y);

        // Deconstruct method
        public void Deconstruct(out int x, out int y) => (x, y) = (X, Y);
    }

    // Extension method
    public static class StringExtensions
    {
        public static bool IsPalindrome(this string str)
        {
            return str.SequenceEqual(str.Reverse());
        }
    }

    // Main Program class
    public class Program
    {
        // Delegate
        public delegate void PrintDelegate(string message);

        public static async Task Main(string[] args)
        {
            // Object creation
            var example = new Example(1) { Name = "Test" };

            // Using properties
            Console.WriteLine($"Id: {example.Id}, Name: {example.Name}, Created: {example.CreatedDate}");

            // Calling methods
            example.ExampleMethod();
            Example.StaticMethod();

            // Using generic method
            int genericResult = example.GenericMethod(10);
            Console.WriteLine($"Generic method result: {genericResult}");

            // Using async method
            int asyncResult = await example.AsyncMethod();
            Console.WriteLine($"Async method result: {asyncResult}");

            // Using indexer
            example[0] = "Hello";
            Console.WriteLine($"Indexer: {example[0]}");

            // Using events
            example.SomeEvent += (sender, e) => Console.WriteLine("Event triggered");

            // Using records
            var person = new Person("John", "Doe");
            Console.WriteLine(person);

            // Using structs and deconstruction
            var point = new Point(10, 20);
            var (x, y) = point;
            Console.WriteLine($"Point: ({x}, {y})");

            // Using extension methods
            string palindrome = "radar";
            Console.WriteLine($"Is '{palindrome}' a palindrome? {palindrome.IsPalindrome()}");

            // Lambda expressions
            Func<int, int, int> add = (a, b) => a + b;
            Console.WriteLine($"Lambda result: {add(5, 3)}");

            // LINQ
            var numbers = new List<int> { 1, 2, 3, 4, 5 };
            var evenNumbers = numbers.Where(n => n % 2 == 0).ToList();
            Console.WriteLine($"Even numbers: {string.Join(", ", evenNumbers)}");

            // Pattern matching
            object obj = "Hello, World!";
            if (obj is string s)
            {
                Console.WriteLine($"String length: {s.Length}");
            }

            // Switch expression (C# 8.0+)
            var dayType = DateTime.Now.DayOfWeek switch
            {
                DayOfWeek.Saturday or DayOfWeek.Sunday => "Weekend",
                _ => "Weekday"
            };
            Console.WriteLine($"Today is a {dayType}");

            // Tuple
            (string, int) tuple = ("test", 123);
            Console.WriteLine($"Tuple: {tuple.Item1}, {tuple.Item2}");

            // Nullable reference types (C# 8.0+)
            string? nullableString = null;
            Console.WriteLine($"Nullable string: {nullableString ?? "null"}");

            // Using statement for IDisposable
            using var disposable = new System.IO.StringWriter();
            disposable.WriteLine("Disposed automatically");

            // Delegate and anonymous method
            PrintDelegate printDelegate = delegate (string message)
            {
                Console.WriteLine($"Delegate: {message}");
            };
            printDelegate("Hello from delegate");

            // Regex
            var regex = new Regex(@"\d+");
            var match = regex.Match("abc123def");
            if (match.Success)
            {
                Console.WriteLine($"Regex match: {match.Value}");
            }

            // JSON serialization/deserialization
            var jsonPerson = JsonSerializer.Serialize(person);
            var deserializedPerson = JsonSerializer.Deserialize<Person>(jsonPerson);
            Console.WriteLine($"Deserialized person: {deserializedPerson}");

            // Span<T> (C# 7.2+)
            Span<int> span = stackalloc int[3] { 1, 2, 3 };
            Console.WriteLine($"Span: {string.Join(", ", span.ToArray())}");

            // Range and Index (C# 8.0+)
            var array = new[] { 1, 2, 3, 4, 5 };
            var slice = array[1..^1];
            Console.WriteLine($"Slice: {string.Join(", ", slice)}");

            // init accessor (C# 9.0+)
            var initExample = new { Id = 1, Name = "Init Example" };
            Console.WriteLine($"Init example: {initExample.Id}, {initExample.Name}");

            // Caller information attributes
            LogMessage("This is a log message");
        }

        // Caller information attributes
        public static void LogMessage(
            string message,
            [System.Runtime.CompilerServices.CallerMemberName] string memberName = "",
            [System.Runtime.CompilerServices.CallerFilePath] string sourceFilePath = "",
            [System.Runtime.CompilerServices.CallerLineNumber] int sourceLineNumber = 0)
        {
            Console.WriteLine($"Message: {message}");
            Console.WriteLine($"Member: {memberName}");
            Console.WriteLine($"File: {sourceFilePath}");
            Console.WriteLine($"Line: {sourceLineNumber}");
        }
    }
}
