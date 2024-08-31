#!/usr/bin/env ruby

# Frozen string literal comment
# frozen_string_literal: true

# Module definition
module Printable
  def print_info
    puts "This is #{self.class}"
  end
end

# Class definition with inheritance
class Animal
  include Printable

  attr_reader :name
  attr_accessor :age

  def initialize(name, age)
    @name = name
    @age = age
  end

  def speak
    raise NotImplementedError, "Subclass must implement abstract method"
  end
end

# Class inheritance
class Dog < Animal
  def speak
    "Woof!"
  end

  def ==(other)
    name == other.name && age == other.age
  end
end

# Singleton class
class << (Logger = Object.new)
  def log(msg)
    puts "[LOG] #{msg}"
  end
end

# Exception handling
begin
  # Raise an exception
  raise ArgumentError, "This is an error"
rescue ArgumentError => e
  puts "Caught exception: #{e.message}"
ensure
  puts "This always runs"
end

# Block, Proc, and Lambda
def method_with_block
  yield if block_given?
end

method_with_block { puts "This is a block" }

proc_example = Proc.new { |x| puts "This is a proc with argument: #{x}" }
proc_example.call(42)

lambda_example = ->(x) { puts "This is a lambda with argument: #{x}" }
lambda_example.call(42)

# Symbol
symbol_example = :my_symbol
puts "This is a symbol: #{symbol_example}"

# String interpolation and multi-line strings
multi_line = <<-HEREDOC
  This is a multi-line string.
  It can contain #{symbol_example}.
HEREDOC
puts multi_line

# Array and Hash
array_example = [1, 2, 3, 4, 5]
hash_example = { a: 1, b: 2, c: 3 }

# Array and Hash operations
puts array_example.map { |x| x * 2 }.join(', ')
puts hash_example.transform_values { |v| v * 2 }

# Ranges
(1..5).each { |i| puts i }

# Conditional statements
if array_example.include?(3)
  puts "Array contains 3"
elsif array_example.empty?
  puts "Array is empty"
else
  puts "Array doesn't contain 3"
end

# Case statement
case array_example.first
when 1
  puts "First element is 1"
when 2
  puts "First element is 2"
else
  puts "First element is neither 1 nor 2"
end

# Ternary operator
puts array_example.empty? ? "Array is empty" : "Array is not empty"

# Unless statement
puts "Array is not empty" unless array_example.empty?

# Loop constructs
array_example.each { |x| puts x }
3.times { puts "Hello" }
1.upto(3) { |i| puts i }

# File I/O
File.open('example.txt', 'w') { |f| f.write("Hello, Ruby!") }
content = File.read('example.txt')
puts "File content: #{content}"

# Regular expressions
if "hello ruby" =~ /ruby/
  puts "String contains 'ruby'"
end

# Method definition with default and keyword arguments
def greet(name, greeting: "Hello", punctuation: "!")
  "#{greeting}, #{name}#{punctuation}"
end

puts greet("Ruby")
puts greet("Ruby", greeting: "Hi", punctuation: "!!!")

# Splat operator
def sum(*numbers)
  numbers.inject(0, :+)
end

puts sum(1, 2, 3, 4, 5)

# Struct
Person = Struct.new(:name, :age) do
  def adult?
    age >= 18
  end
end

person = Person.new("Alice", 30)
puts "#{person.name} is #{person.adult? ? 'an adult' : 'not an adult'}"

# Enumerable module
[1, 2, 3, 4, 5].each_cons(2) { |a, b| puts "#{a}, #{b}" }

# Mixin
module Swimmable
  def swim
    "#{self.class} is swimming"
  end
end

class Fish < Animal
  include Swimmable
end

fish = Fish.new("Nemo", 1)
puts fish.swim

# Method missing and method respond to
class MethodMissingExample
  def method_missing(method_name, *args, &block)
    puts "Called undefined method: #{method_name} with arguments: #{args}"
  end

  def respond_to_missing?(method_name, include_private = false)
    true
  end
end

obj = MethodMissingExample.new
obj.undefined_method(1, 2, 3)

# Monkey patching
class String
  def shout
    upcase + "!"
  end
end

puts "hello".shout

# Singleton methods
dog = Dog.new("Buddy", 5)
def dog.roll_over
  "#{name} is rolling over"
end
puts dog.roll_over

# Freeze objects
frozen_array = [1, 2, 3].freeze
# frozen_array << 4 # This would raise a FrozenError

# Refinements
module StringRefinements
  refine String do
    def palindrome?
      self == self.reverse
    end
  end
end

using StringRefinements
puts "radar".palindrome?

# Metaprogramming
class MetaprogrammingExample
  define_method(:dynamic_method) do |arg|
    "This is a dynamic method with argument: #{arg}"
  end
end

meta_obj = MetaprogrammingExample.new
puts meta_obj.dynamic_method("Ruby")

# Fiber (cooperative multitasking)
fiber = Fiber.new do
  puts "In Fiber"
  Fiber.yield
  puts "Fiber resumed"
end

fiber.resume
puts "Back in main"
fiber.resume

# Thread (preemptive multitasking)
thread = Thread.new do
  3.times { puts "In thread"; sleep 1 }
end

3.times { puts "In main"; sleep 1 }
thread.join

# Ruby's special variables
puts "Script name: #{$0}"
puts "Command line arguments: #{$*}"
puts "Process ID: #{$$}"

# END block (runs at the end of the program)
END {
  puts "This is the end of the program"
}

# BEGIN block (runs at the beginning of the program)
BEGIN {
  puts "This is the beginning of the program"
}
