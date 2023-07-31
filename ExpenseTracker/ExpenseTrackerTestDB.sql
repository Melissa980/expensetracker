DROP DATABASE IF EXISTS ExpenseTrackerTest;
CREATE DATABASE ExpenseTrackerTest;
USE ExpenseTrackerTest;

CREATE TABLE Customer (
    customerId INT PRIMARY KEY AUTO_INCREMENT,
    firstName VARCHAR(50) NOT NULL,
    lastName VARCHAR(50) NOT NULL,
    email VARCHAR(100) NOT NULL
);

CREATE TABLE Category (
    categoryId INT PRIMARY KEY AUTO_INCREMENT,
    name VARCHAR(50) NOT NULL
);

CREATE TABLE PaymentMethod (
    paymentMethodId INT PRIMARY KEY AUTO_INCREMENT,
    name VARCHAR(50) NOT NULL
);

CREATE TABLE Expense (
    expenseId INT PRIMARY KEY AUTO_INCREMENT,
    description VARCHAR(100) NOT NULL,
    amount DECIMAL(10, 2) NOT NULL,
    date DATE NOT NULL,
    categoryId INT NOT NULL,
    customerId INT NOT NULL,
    FOREIGN KEY (categoryId) REFERENCES Category(categoryId),
    FOREIGN KEY (customerId) REFERENCES Customer(customerId)
);

CREATE TABLE Budget (
    budgetId INT PRIMARY KEY AUTO_INCREMENT,
    amount DECIMAL(10, 2) NOT NULL,
    categoryId INT NOT NULL,
    customerId INT NOT NULL,
    FOREIGN KEY (categoryId) REFERENCES Category(categoryId),
    FOREIGN KEY (customerId) REFERENCES Customer(customerId)
);
