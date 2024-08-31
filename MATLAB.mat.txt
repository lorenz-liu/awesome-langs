% MATLAB Syntax and Features Showcase

% Basic variable assignment
a = 5;
b = 10.5;

% Arrays and matrices
vector = [1 2 3 4 5];
row_vector = [1, 2, 3, 4, 5];
column_vector = [1; 2; 3; 4; 5];
matrix = [1 2 3; 4 5 6; 7 8 9];

% Array creation functions
linspace_array = linspace(0, 1, 11);
logspace_array = logspace(0, 2, 5);
zeros_matrix = zeros(3, 4);
ones_matrix = ones(2, 3);
identity_matrix = eye(3);

% Cell arrays
cell_array = {1, 'text', [1 2; 3 4]};

% Structures
person = struct('name', 'John', 'age', 30, 'city', 'New York');

% Indexing and slicing
element = matrix(2, 3);
row = matrix(2, :);
column = matrix(:, 3);
submatrix = matrix(1:2, 2:3);

% Basic arithmetic operations
sum_result = a + b;
product_result = a * b;
element_wise_product = vector .* column_vector;

% Matrix operations
matrix_product = matrix * column_vector;
matrix_power = matrix^2;
matrix_transpose = matrix';

% Logical operations and comparison
logical_and = (a > 0) && (b < 20);
logical_or = (a < 0) || (b > 0);
equality_check = a == b;
greater_than = vector > 3;

% Control structures
if a > b
    disp('a is greater than b');
elseif a < b
    disp('a is less than b');
else
    disp('a is equal to b');
end

for i = 1:5
    disp(['Iteration: ' num2str(i)]);
end

while a < 10
    a = a + 1;
end

% Switch statement
day = 3;
switch day
    case 1
        disp('Monday');
    case {2, 3, 4, 5}
        disp('Weekday');
    otherwise
        disp('Weekend');
end

% Function definition
function result = add_numbers(x, y)
    result = x + y;
end

% Anonymous functions
square = @(x) x.^2;

% Plotting
x = 0:0.1:2*pi;
y = sin(x);
plot(x, y);
xlabel('x');
ylabel('y');
title('Sine Wave');

% 3D plotting
[X, Y] = meshgrid(-2:0.2:2, -2:0.2:2);
Z = X .* exp(-X.^2 - Y.^2);
surf(X, Y, Z);

% String manipulation
str1 = 'Hello';
str2 = 'World';
concatenated = [str1 ' ' str2];
substring = str1(2:4);

% Cell array of strings
string_array = {'one', 'two', 'three'};

% Regular expressions
pattern = '\d+';
text = 'There are 123 apples and 456 oranges.';
matches = regexp(text, pattern, 'match');

% File I/O
fid = fopen('example.txt', 'w');
fprintf(fid, 'This is a test.\n');
fclose(fid);

content = fileread('example.txt');

% Error handling
try
    result = 1 / 0;
catch ME
    disp(['Error: ' ME.message]);
end

% Object-Oriented Programming
classdef Rectangle
    properties
        Width
        Height
    end
    
    methods
        function obj = Rectangle(w, h)
            obj.Width = w;
            obj.Height = h;
        end
        
        function area = getArea(obj)
            area = obj.Width * obj.Height;
        end
    end
end

rect = Rectangle(5, 3);
rect_area = rect.getArea();

% Data import
% Assuming you have a CSV file named 'data.csv'
% data = readtable('data.csv');

% Statistical functions
mean_value = mean(vector);
median_value = median(vector);
std_dev = std(vector);

% Numerical integration
f = @(x) x.^2;
integral_result = integral(f, 0, 1);

% Ordinary Differential Equations
ode_fun = @(t, y) -2 * y;
[t, y] = ode45(ode_fun, [0 5], 1);

% Symbolic math
syms x y
expr = x^2 + 2*x*y + y^2;
expanded = expand((x + y)^2);
derivative = diff(expr, x);

% Optimization
fun = @(x) x(1)^2 + x(2)^2;
x0 = [1; 1];
options = optimset('Display', 'iter');
[x_min, fval] = fminunc(fun, x0, options);

% Image processing
% Assuming you have an image file named 'image.jpg'
% img = imread('image.jpg');
% gray_img = rgb2gray(img);
% edge_img = edge(gray_img, 'Canny');
% imshow(edge_img);

% Sparse matrices
sparse_matrix = sparse(1:3, 1:3, [1 2 3], 5, 5);

% GPU computing (if supported)
% gpuArray_example = gpuArray([1 2 3; 4 5 6]);
% gpu_result = sum(gpuArray_example, 'all');

% Parallel computing
% parfor i = 1:10
%     result(i) = heavy_computation(i);
% end

% MEX files (assuming you have a MEX file named 'mex_function')
% result = mex_function(arg1, arg2);

% Profiling
profile on
% Your code here
profile off
profile viewer

% Unit testing
% In a separate file named 'TestAddNumbers.m'
% classdef TestAddNumbers < matlab.unittest.TestCase
%     methods (Test)
%         function testAddition(testCase)
%             actual = add_numbers(2, 3);
%             expected = 5;
%             testCase.verifyEqual(actual, expected);
%         end
%     end
% end

% To run the test:
% results = run(TestAddNumbers);

disp('MATLAB Features Showcase completed.');
