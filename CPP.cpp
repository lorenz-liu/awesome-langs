#include <iostream>
#include <vector>
#include <map>
#include <memory>
#include <algorithm>
#include <functional>
#include <thread>
#include <mutex>
#include <future>
#include <type_traits>
#include <variant>
#include <optional>
#include <any>

// Template class
template<typename T>
class Container {
private:
    T data;
public:
    Container(T d) : data(d) {}
    T getValue() const { return data; }
};

// Variadic template
template<typename... Args>
void printAll(Args... args) {
    (std::cout << ... << args) << std::endl;
}

// Concept (C++20)
template<typename T>
concept Printable = requires(T t) {
    { std::cout << t } -> std::same_as<std::ostream&>;
};

// Constexpr function
constexpr int factorial(int n) {
    return (n <= 1) ? 1 : n * factorial(n - 1);
}

// Lambda expression
auto lambda = [](int x, int y) { return x + y; };

// Smart pointers
std::unique_ptr<int> uniquePtr = std::make_unique<int>(42);
std::shared_ptr<int> sharedPtr = std::make_shared<int>(10);

// RAII class
class ResourceManager {
    int* resource;
public:
    ResourceManager() : resource(new int(0)) {}
    ~ResourceManager() { delete resource; }
    ResourceManager(const ResourceManager&) = delete;
    ResourceManager& operator=(const ResourceManager&) = delete;
};

// Move semantics
class Movable {
public:
    Movable() = default;
    Movable(Movable&&) noexcept = default;
    Movable& operator=(Movable&&) noexcept = default;
};

// Virtual inheritance
class Base { public: virtual ~Base() = default; };
class Derived1 : virtual public Base {};
class Derived2 : virtual public Base {};
class FinalDerived : public Derived1, public Derived2 {};

// Enum class
enum class Color { Red, Green, Blue };

// Structured binding
std::pair<int, std::string> getPair() { return {1, "one"}; }

// Namespace
namespace MyNamespace {
    void someFunction() { std::cout << "In namespace" << std::endl; }
}

// Attributes
[[nodiscard]] int mustUse() { return 42; }

// Compile-time if
template<typename T>
auto getValue(T t) {
    if constexpr (std::is_pointer_v<T>)
        return *t;
    else
        return t;
}

// Fold expressions (C++17)
template<typename... Args>
auto sum(Args... args) {
    return (... + args);
}

// std::variant and std::visit
std::variant<int, float, std::string> v = "hello";
auto visitor = [](auto&& arg) { std::cout << arg << std::endl; };

// Coroutines (C++20)
#include <coroutine>
#include <iostream>

struct Generator {
    struct promise_type {
        int current_value;
        auto get_return_object() { return Generator{handle_type::from_promise(*this)}; }
        auto initial_suspend() { return std::suspend_always{}; }
        auto final_suspend() noexcept { return std::suspend_always{}; }
        void unhandled_exception() { std::terminate(); }
        auto yield_value(int value) {
            current_value = value;
            return std::suspend_always{};
        }
    };
    using handle_type = std::coroutine_handle<promise_type>;
    handle_type coro;
    Generator(handle_type h) : coro(h) {}
    ~Generator() { if (coro) coro.destroy(); }
    int next() {
        coro.resume();
        return coro.promise().current_value;
    }
};

Generator range(int start, int end) {
    for (int i = start; i < end; ++i)
        co_yield i;
}

int main() {
    // Using Container
    Container<int> intContainer(5);
    std::cout << intContainer.getValue() << std::endl;

    // Using variadic template
    printAll(1, 2.5, "Hello");

    // Using concept
    auto printConcept = [](Printable auto const& thing) {
        std::cout << thing << std::endl;
    };
    printConcept("Concept demo");

    // Using constexpr
    constexpr int fact5 = factorial(5);
    std::cout << "5! = " << fact5 << std::endl;

    // Using lambda
    std::cout << "Lambda result: " << lambda(3, 4) << std::endl;

    // Using smart pointers
    std::cout << "Unique ptr value: " << *uniquePtr << std::endl;
    std::cout << "Shared ptr value: " << *sharedPtr << std::endl;

    // Using move semantics
    Movable m1;
    Movable m2 = std::move(m1);

    // Using virtual inheritance
    FinalDerived fd;

    // Using enum class
    Color c = Color::Red;

    // Using structured binding
    auto [num, str] = getPair();
    std::cout << num << " " << str << std::endl;

    // Using namespace
    MyNamespace::someFunction();

    // Using attributes
    [[maybe_unused]] int result = mustUse();

    // Using compile-time if
    int x = 5;
    std::cout << getValue(&x) << " " << getValue(x) << std::endl;

    // Using fold expressions
    std::cout << "Sum: " << sum(1, 2, 3, 4, 5) << std::endl;

    // Using std::variant and std::visit
    std::visit(visitor, v);

    // Using std::optional
    std::optional<int> opt = 42;
    if (opt.has_value()) {
        std::cout << "Optional value: " << *opt << std::endl;
    }

    // Using std::any
    std::any a = 1;
    std::cout << std::any_cast<int>(a) << std::endl;

    // Using threads
    std::mutex mtx;
    std::thread t([&mtx]() {
        std::lock_guard<std::mutex> lock(mtx);
        std::cout << "Hello from thread!" << std::endl;
    });
    t.join();

    // Using futures
    std::future<int> fut = std::async(std::launch::async, []() { return 8; });
    std::cout << "Future result: " << fut.get() << std::endl;

    // Using coroutines
    auto gen = range(0, 5);
    for (int i = 0; i < 5; ++i) {
        std::cout << gen.next() << " ";
    }
    std::cout << std::endl;

    return 0;
}
