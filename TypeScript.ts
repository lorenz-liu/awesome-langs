// Basic Types
let isDone: boolean = false;
let decimal: number = 6;
let color: string = "blue";
let list: number[] = [1, 2, 3];
let x: [string, number] = ["hello", 10];

// Enum
enum Color {Red, Green, Blue}
let c: Color = Color.Green;

// Any
let notSure: any = 4;
notSure = "maybe a string instead";
notSure = false;

// Void
function warnUser(): void {
    console.log("This is my warning message");
}

// Null and Undefined
let u: undefined = undefined;
let n: null = null;

// Never
function error(message: string): never {
    throw new Error(message);
}

// Object
declare function create(o: object | null): void;
create({ prop: 0 });

// Type assertions
let someValue: any = "this is a string";
let strLength: number = (<string>someValue).length;
let strLength2: number = (someValue as string).length;

// Interface
interface LabelledValue {
    label: string;
    optional?: string;
    readonly size: number;
}

function printLabel(labelledObj: LabelledValue) {
    console.log(labelledObj.label);
}

// Class
class Animal {
    private name: string;
    constructor(theName: string) { this.name = theName; }
    move(distanceInMeters: number = 0) {
        console.log(`${this.name} moved ${distanceInMeters}m.`);
    }
}

class Dog extends Animal {
    bark() {
        console.log('Woof! Woof!');
    }
}

// Generic
function identity<T>(arg: T): T {
    return arg;
}

// Union Types
function padLeft(value: string, padding: string | number) {
    // ...
}

// Intersection Types
interface ErrorHandling {
    success: boolean;
    error?: { message: string };
}

interface ArtworksData {
    artworks: { title: string }[];
}

type ArtworksResponse = ArtworksData & ErrorHandling;

// Type Aliases
type Name = string;
type NameResolver = () => string;
type NameOrResolver = Name | NameResolver;

// Literal Types
type Easing = "ease-in" | "ease-out" | "ease-in-out";

// Discriminated Unions
interface Square {
    kind: "square";
    size: number;
}
interface Rectangle {
    kind: "rectangle";
    width: number;
    height: number;
}
type Shape = Square | Rectangle;

// Index Types
function pluck<T, K extends keyof T>(o: T, names: K[]): T[K][] {
  return names.map(n => o[n]);
}

// Mapped Types
type Readonly<T> = {
    readonly [P in keyof T]: T[P];
}

// Conditional Types
type TypeName<T> = 
    T extends string ? "string" :
    T extends number ? "number" :
    T extends boolean ? "boolean" :
    T extends undefined ? "undefined" :
    T extends Function ? "function" :
    "object";

// Decorators
function sealed(constructor: Function) {
    Object.seal(constructor);
    Object.seal(constructor.prototype);
}

@sealed
class Greeter {
    greeting: string;
    constructor(message: string) {
        this.greeting = message;
    }
    greet() {
        return "Hello, " + this.greeting;
    }
}

// Symbols
const sym = Symbol();
let obj = {
    [sym]: "value"
};

// Iterators and Generators
function* generateSequence() {
    yield 1;
    yield 2;
    yield 3;
}

// Modules
export interface StringValidator {
    isAcceptable(s: string): boolean;
}

// Namespaces
namespace Validation {
    export interface StringValidator {
        isAcceptable(s: string): boolean;
    }
}

// JSX
declare namespace JSX {
    interface Element {}
    interface IntrinsicElements {
        foo: any
    }
}

// Mixins
class Disposable {
    isDisposed: boolean = false;
    dispose() {
        this.isDisposed = true;
    }
}

class Activatable {
    isActive: boolean = false;
    activate() {
        this.isActive = true;
    }
    deactivate() {
        this.isActive = false;
    }
}

class SmartObject implements Disposable, Activatable {
    constructor() {
        setInterval(() => console.log(this.isActive + " : " + this.isDisposed), 500);
    }

    interact() {
        this.activate();
    }

    // Disposable
    isDisposed: boolean = false;
    dispose: () => void;
    // Activatable
    isActive: boolean = false;
    activate: () => void;
    deactivate: () => void;
}
applyMixins(SmartObject, [Disposable, Activatable]);

function applyMixins(derivedCtor: any, baseCtors: any[]) {
    baseCtors.forEach(baseCtor => {
        Object.getOwnPropertyNames(baseCtor.prototype).forEach(name => {
            Object.defineProperty(derivedCtor.prototype, name, Object.getOwnPropertyDescriptor(baseCtor.prototype, name)!);
        });
    });
}

// Async/Await
async function fetchData(url: string): Promise<string> {
    const response = await fetch(url);
    return response.text();
}

// Nullish coalescing
let x = foo ?? bar();

// Optional chaining
let x = foo?.bar.baz();

// Template Literal Types
type World = "world";
type Greeting = `hello ${World}`;

// Usage
let mySquare: Shape = { kind: "square", size: 100 };
let myStr: TypeName<string> = "string";
let myNum: TypeName<number> = "number";
let readonlyPerson: Readonly<Person> = { name: "John", age: 30 };

async function main() {
    const data = await fetchData("https://api.example.com");
    console.log(data);
}

main().catch(console.error);
