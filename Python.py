import asyncio
from typing import List, Dict, Any
from dataclasses import dataclass
from functools import reduce
from collections import defaultdict

# Decorators
def timer(func):
    async def wrapper(*args, **kwargs):
        start = asyncio.get_event_loop().time()
        result = await func(*args, **kwargs)
        end = asyncio.get_event_loop().time()
        print(f"{func.__name__} took {end - start:.2f} seconds")
        return result
    return wrapper

# Type hinting and dataclasses
@dataclass
class Person:
    name: str
    age: int
    skills: List[str]

    def __post_init__(self):
        print(f"Created {self.name}")

# Asynchronous programming
@timer
async def fetch_data(url: str) -> Dict[str, Any]:
    print(f"Fetching data from {url}")
    await asyncio.sleep(1)  # Simulating network delay
    return {"status": "success", "data": "Some data"}

# List comprehension and generator expression
def process_numbers(numbers: List[int]) -> List[int]:
    squares = [x**2 for x in numbers if x % 2 == 0]
    return list(x for x in squares if x < 50)

# Lambda functions and functional programming
multiply = lambda x, y: x * y
numbers = [1, 2, 3, 4, 5]
product = reduce(multiply, numbers)

# Context manager
class FileManager:
    def __init__(self, filename: str):
        self.filename = filename
    
    def __enter__(self):
        self.file = open(self.filename, 'w')
        return self.file
    
    def __exit__(self, exc_type, exc_value, traceback):
        self.file.close()

# Main function using async/await
async def main():
    # Creating objects
    alice = Person("Alice", 30, ["Python", "AI"])
    bob = Person("Bob", 25, ["JavaScript", "Web Dev"])
    
    # Dictionary comprehension
    skills_dict = {person.name: person.skills for person in [alice, bob]}
    
    # Async operations
    results = await asyncio.gather(
        fetch_data("https://api1.example.com"),
        fetch_data("https://api2.example.com")
    )
    
    # Set operations
    all_skills = set(alice.skills) | set(bob.skills)
    
    # Error handling
    try:
        result = 10 / 0
    except ZeroDivisionError as e:
        print(f"Caught an error: {e}")
    finally:
        print("This always executes")
    
    # Using the context manager
    with FileManager("example.txt") as file:
        file.write("Hello, World!")
    
    # Unpacking
    a, *rest, b = [1, 2, 3, 4, 5]
    
    # F-strings and walrus operator
    if (n := len(numbers)) > 3:
        print(f"List has {n} items")
    
    # Type hinting with Union
    from typing import Union
    def process_input(data: Union[str, int]) -> str:
        return str(data).upper()
    
    # Demonstrating defaultdict
    word_count = defaultdict(int)
    for word in "the quick brown fox jumps over the lazy dog".split():
        word_count[word] += 1
    
    print("Execution completed")

if __name__ == "__main__":
    asyncio.run(main())
