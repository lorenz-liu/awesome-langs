// ES6+ features
import { someFunction } from './module.js';

// Class definition with static method, getter, and setter
class Person {
  #privateField = 'Private'; // Private field

  constructor(name, age) {
    this.name = name;
    this.age = age;
  }

  static staticMethod() {
    console.log('This is a static method');
  }

  get info() {
    return `${this.name} is ${this.age} years old`;
  }

  set info(value) {
    [this.name, this.age] = value.split(' ');
  }

  // Method
  greet() {
    console.log(`Hello, I'm ${this.name}`);
  }
}

// Inheritance
class Employee extends Person {
  constructor(name, age, job) {
    super(name, age);
    this.job = job;
  }
}

// Arrow functions
const multiply = (a, b) => a * b;

// Template literals
const name = 'Alice';
console.log(`Hello, ${name}!`);

// Destructuring
const { x, y, ...rest } = { x: 1, y: 2, a: 3, b: 4 };

// Spread operator
const arr1 = [1, 2, 3];
const arr2 = [...arr1, 4, 5];

// Object property shorthand
const objShorthand = { x, y };

// Async/Await with Promises
async function fetchData(url) {
  try {
    const response = await fetch(url);
    const data = await response.json();
    return data;
  } catch (error) {
    console.error('Error:', error);
  }
}

// Map and Set
const myMap = new Map();
myMap.set('key', 'value');

const mySet = new Set([1, 2, 3, 3]); // Duplicates are automatically removed

// Symbol
const uniqueKey = Symbol('description');

// Iterators and generators
function* numberGenerator() {
  yield 1;
  yield 2;
  yield 3;
}

// Array methods
const numbers = [1, 2, 3, 4, 5];
const doubled = numbers.map(x => x * 2);
const sum = numbers.reduce((acc, curr) => acc + curr, 0);

// Optional chaining and nullish coalescing
const obj = { nested: { value: null } };
console.log(obj?.nested?.value ?? 'default');

// BigInt
const bigNumber = 1234567890123456789012345678901234567890n;

// Proxy
const handler = {
  get: (target, prop) => `Property ${prop} accessed`
};
const proxy = new Proxy({}, handler);

// Reflect
Reflect.has({ x: 0 }, 'x');

// Typed Arrays
const int32 = new Int32Array(4);

// Modules (named and default exports)
export const namedExport = 'I am named';
export default function defaultExport() { }

// Decorators (proposal)
function readonly(target, name, descriptor) {
  descriptor.writable = false;
  return descriptor;
}

class Example {
  @readonly
  cannotBeChanged() { }
}

// async generator
async function* asyncGenerator() {
  yield await Promise.resolve(1);
  yield await Promise.resolve(2);
}

// Top-level await (in modules)
// const data = await fetchData('https://api.example.com/data');

// Logical assignment operators
let a = null;
a ??= 5; // a is now 5

// Numeric separators
const largeNumber = 1_000_000_000;

// Object.fromEntries
const entries = [['foo', 'bar'], ['baz', 42]];
const obj = Object.fromEntries(entries);

// String methods
'hello'.padStart(10, '.');

// Atomics and SharedArrayBuffer (for concurrency)
const buffer = new SharedArrayBuffer(16);
const int32 = new Int32Array(buffer);
Atomics.add(int32, 0, 10);

// Using the class and other features
const person = new Person('Bob', 30);
person.greet();
console.log(person.info);
Person.staticMethod();

// Running an async function
(async () => {
  for await (const num of asyncGenerator()) {
    console.log(num);
  }
})();
