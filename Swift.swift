import Foundation

// MARK: - Protocols

protocol Printable {
    var description: String { get }
    func printDescription()
}

protocol Drivable {
    func drive()
}

// MARK: - Classes

class Vehicle {
    var wheels: Int
    
    init(wheels: Int) {
        self.wheels = wheels
    }
    
    func describe() -> String {
        return "A vehicle with \(wheels) wheels"
    }
}

class Car: Vehicle, Printable, Drivable {
    var brand: String
    
    init(brand: String) {
        self.brand = brand
        super.init(wheels: 4)
    }
    
    override func describe() -> String {
        return "A \(brand) car"
    }
    
    var description: String {
        return describe()
    }
    
    func printDescription() {
        print(description)
    }
    
    func drive() {
        print("Driving the \(brand) car")
    }
}

// MARK: - Structs

struct Point {
    var x: Double
    var y: Double
    
    mutating func moveBy(x deltaX: Double, y deltaY: Double) {
        x += deltaX
        y += deltaY
    }
}

// MARK: - Enums

enum Compass: String {
    case north, south, east, west
    
    var abbreviation: String {
        return String(rawValue.prefix(1))
    }
}

// MARK: - Extensions

extension Double {
    var squared: Double { return self * self }
}

// MARK: - Generics

func swapValues<T>(_ a: inout T, _ b: inout T) {
    let temp = a
    a = b
    b = temp
}

// MARK: - Closures

let numbers = [1, 2, 3, 4, 5]
let squared = numbers.map { $0 * $0 }

// MARK: - Error Handling

enum NetworkError: Error {
    case badURL
    case noData
}

func fetchData(from urlString: String) throws -> Data {
    guard let url = URL(string: urlString) else {
        throw NetworkError.badURL
    }
    
    // Simulating network request
    if urlString.contains("example") {
        return Data()
    } else {
        throw NetworkError.noData
    }
}

// MARK: - Optionals and Optional Chaining

var optionalString: String? = "Hello"
let unwrappedString = optionalString?.uppercased()

// MARK: - Property Observers

class StepCounter {
    var totalSteps: Int = 0 {
        willSet(newTotalSteps) {
            print("About to set totalSteps to \(newTotalSteps)")
        }
        didSet {
            if totalSteps > oldValue  {
                print("Added \(totalSteps - oldValue) steps")
            }
        }
    }
}

// MARK: - Lazy Properties

class DataManager {
    lazy var importer = DataImporter()
}

class DataImporter {
    var fileName = "data.txt"
}

// MARK: - Computed Properties

struct Circle {
    var radius: Double
    var area: Double {
        return Double.pi * radius.squared
    }
}

// MARK: - Subscripts

struct Matrix {
    let rows: Int, columns: Int
    var grid: [Double]
    
    init(rows: Int, columns: Int) {
        self.rows = rows
        self.columns = columns
        grid = Array(repeating: 0.0, count: rows * columns)
    }
    
    subscript(row: Int, column: Int) -> Double {
        get {
            return grid[(row * columns) + column]
        }
        set {
            grid[(row * columns) + column] = newValue
        }
    }
}

// MARK: - Type Casting and Inspection

class MediaItem {
    var name: String
    init(name: String) {
        self.name = name
    }
}

class Movie: MediaItem {
    var director: String
    init(name: String, director: String) {
        self.director = director
        super.init(name: name)
    }
}

class Song: MediaItem {
    var artist: String
    init(name: String, artist: String) {
        self.artist = artist
        super.init(name: name)
    }
}

// MARK: - Nested Types

struct BlackjackCard {
    enum Suit: Character {
        case spades = "♠", hearts = "♡", diamonds = "♢", clubs = "♣"
    }
    
    enum Rank: Int {
        case two = 2, three, four, five, six, seven, eight, nine, ten
        case jack, queen, king, ace
    }
    
    let rank: Rank, suit: Suit
}

// MARK: - Main Execution

func main() {
    // Using classes and protocols
    let myCar = Car(brand: "Tesla")
    myCar.printDescription()
    myCar.drive()
    
    // Using structs
    var point = Point(x: 1, y: 2)
    point.moveBy(x: 2, y: 3)
    print("Point moved to (\(point.x), \(point.y))")
    
    // Using enums
    let direction = Compass.north
    print("Heading \(direction) (\(direction.abbreviation))")
    
    // Using extensions
    let number = 4.0
    print("\(number) squared is \(number.squared)")
    
    // Using generics
    var a = 10, b = 20
    swapValues(&a, &b)
    print("After swap: a = \(a), b = \(b)")
    
    // Using closures
    print("Squared numbers: \(squared)")
    
    // Error handling
    do {
        let data = try fetchData(from: "https://example.com")
        print("Fetched \(data.count) bytes")
    } catch {
        print("An error occurred: \(error)")
    }
    
    // Optional chaining
    if let uppercased = unwrappedString {
        print("Uppercased optional: \(uppercased)")
    }
    
    // Property observers
    let stepCounter = StepCounter()
    stepCounter.totalSteps = 100
    stepCounter.totalSteps = 200
    
    // Lazy properties
    let manager = DataManager()
    print(manager.importer.fileName)
    
    // Computed properties
    let circle = Circle(radius: 5)
    print("Circle area: \(circle.area)")
    
    // Subscripts
    var matrix = Matrix(rows: 2, columns: 2)
    matrix[0, 1] = 1.5
    print("Matrix value at [0, 1]: \(matrix[0, 1])")
    
    // Type casting
    let movieItem = Movie(name: "Inception", director: "Christopher Nolan")
    let songItem = Song(name: "Imagine", artist: "John Lennon")
    let mediaItems: [MediaItem] = [movieItem, songItem]
    
    for item in mediaItems {
        if let movie = item as? Movie {
            print("Movie: \(movie.name), dir. \(movie.director)")
        } else if let song = item as? Song {
            print("Song: \(song.name), by \(song.artist)")
        }
    }
    
    // Nested types
    let aceOfSpades = BlackjackCard(rank: .ace, suit: .spades)
    print("Card: \(aceOfSpades.rank.rawValue) of \(aceOfSpades.suit.rawValue)")
}

main()
