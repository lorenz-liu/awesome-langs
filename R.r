# Basic variable assignment
x <- 5
y = 10  # Alternative assignment operator

# Data types
numeric_var <- 3.14
integer_var <- 42L
character_var <- "Hello, R!"
logical_var <- TRUE
complex_var <- 3 + 4i

# Vectors
numeric_vector <- c(1, 2, 3, 4, 5)
character_vector <- c("a", "b", "c")
logical_vector <- c(TRUE, FALSE, TRUE)

# Sequences
seq_vector <- 1:10
seq_vector2 <- seq(from = 0, to = 1, by = 0.1)

# Matrices
matrix_example <- matrix(1:9, nrow = 3, ncol = 3)

# Arrays
array_example <- array(1:24, dim = c(2, 3, 4))

# Lists
list_example <- list(a = 1, b = "hello", c = TRUE)

# Data frames
df <- data.frame(
  name = c("Alice", "Bob", "Charlie"),
  age = c(25, 30, 35),
  height = c(165, 180, 175)
)

# Factors
factor_example <- factor(c("low", "medium", "high", "medium", "low"))

# Indexing and subsetting
vector_subset <- numeric_vector[2:4]
matrix_subset <- matrix_example[1:2, 2:3]
df_subset <- df[df$age > 25, ]

# Control structures
if (x > 0) {
  print("x is positive")
} else if (x < 0) {
  print("x is negative")
} else {
  print("x is zero")
}

for (i in 1:5) {
  print(i^2)
}

while (x > 0) {
  print(x)
  x <- x - 1
}

# Functions
square <- function(x) {
  return(x^2)
}

# Anonymous functions (lambda)
cube <- \(x) x^3

# Apply family of functions
lapply(1:5, square)
sapply(1:5, cube)
apply(matrix_example, 1, sum)  # Sum of each row

# Vectorized operations
vectorized_sum <- numeric_vector + 10
vectorized_product <- numeric_vector * 2

# Basic statistics
mean_value <- mean(numeric_vector)
median_value <- median(numeric_vector)
sd_value <- sd(numeric_vector)

# Random number generation
random_normal <- rnorm(100, mean = 0, sd = 1)
random_uniform <- runif(100, min = 0, max = 1)

# Plotting
plot(1:10, main = "Simple Plot", xlab = "X-axis", ylab = "Y-axis")

# String manipulation
paste_example <- paste("Hello", "R", "World", sep = " ")
substr_example <- substr("Hello, R!", start = 1, stop = 5)

# Regular expressions
grep_example <- grep("^a", c("apple", "banana", "cherry"), value = TRUE)
gsub_example <- gsub("a", "A", "banana")

# Date and time
current_date <- Sys.Date()
current_time <- Sys.time()

# Error handling
tryCatch(
  expr = {
    1 / 0
  },
  error = function(e) {
    print("Error caught:")
    print(e)
  },
  warning = function(w) {
    print("Warning caught:")
    print(w)
  },
  finally = {
    print("This is always executed")
  }
)

# Classes and methods (S3 system)
create_person <- function(name, age) {
  person <- list(name = name, age = age)
  class(person) <- "person"
  return(person)
}

print.person <- function(x) {
  cat("Person: ", x$name, ", Age: ", x$age, "\n")
}

john <- create_person("John", 30)
print(john)

# Environments
new_env <- new.env()
new_env$var1 <- 100
new_env$var2 <- 200

# Closures
counter <- function() {
  count <- 0
  function() {
    count <<- count + 1
    return(count)
  }
}

my_counter <- counter()
my_counter()  # Returns 1
my_counter()  # Returns 2

# Formulas
formula_example <- y ~ x1 + x2 + x3

# Data manipulation with dplyr
library(dplyr)

df_manipulated <- df %>%
  filter(age > 25) %>%
  mutate(age_group = ifelse(age < 30, "young", "old")) %>%
  group_by(age_group) %>%
  summarise(avg_height = mean(height))

# Data visualization with ggplot2
library(ggplot2)

ggplot(df, aes(x = age, y = height)) +
  geom_point() +
  geom_smooth(method = "lm") +
  labs(title = "Age vs Height", x = "Age", y = "Height")

# Working with files
write.csv(df, "example.csv")
read_df <- read.csv("example.csv")

# Named arguments and default values
greet <- function(name = "World", greeting = "Hello") {
  paste(greeting, name, sep = ", ")
}

greet(greeting = "Hi", name = "R")

# Lazy evaluation
lazy_function <- function(x) {
  10
}

lazy_function(stop("This error is not triggered"))

# Operators as functions
add <- `+`
add(3, 4)

# Scoping and environments
x <- 10
f <- function() {
  y <- 20
  g <- function() {
    z <- 30
    c(x, y, z)
  }
  g()
}
f()

# Attributes
numbers <- 1:10
attr(numbers, "my_attribute") <- "This is a custom attribute"
attributes(numbers)

# S4 classes
setClass("Employee",
  slots = list(
    name = "character",
    age = "numeric",
    department = "character"
  )
)

alice <- new("Employee", name = "Alice", age = 30, department = "HR")

# Metaprogramming
call_example <- call("mean", x = 1:10)
eval(call_example)

# Non-standard evaluation
library(rlang)
my_summary <- function(data, var) {
  var <- enquo(var)
  summarise(data, mean = mean(!!var), sd = sd(!!var))
}

my_summary(df, height)

# Print results
print("R Features Showcase completed.")
