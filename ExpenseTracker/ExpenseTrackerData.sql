Use ExpenseTracker;

INSERT INTO Customer (firstName, lastName, email) VALUES
    ('John', 'Doe', 'john.doe@example.com'),
    ('Jane', 'Smith', 'jane.smith@example.com'),
    ('Michael', 'Johnson', 'michael.johnson@example.com');

INSERT INTO Category (name) VALUES
    ('Food'),
    ('Transportation'),
    ('Entertainment'),
    ('Utilities');

INSERT INTO PaymentMethod (name) VALUES
    ('Cash'),
    ('Credit Card'),
    ('Debit Card'),
    ('PayPal');

INSERT INTO Expense (description, amount, date, categoryId, customerId) VALUES
    ('Groceries', 50.00, '2023-07-15', 1, 1),
    ('Gasoline', 35.50, '2023-07-16', 2, 2),
    ('Movie Ticket', 12.00, '2023-07-17', 3, 3),
    ('Electricity Bill', 80.25, '2023-07-18', 4, 1),
    ('Restaurant Dinner', 60.75, '2023-07-20', 1, 2),
    ('Bus Fare', 3.25, '2023-07-21', 2, 3);

INSERT INTO Budget (amount, categoryId, customerId) VALUES
    (200.00, 1, 1),
    (100.00, 2, 1),
    (50.00, 3, 1),
    (150.00, 1, 2),
    (80.00, 2, 2),
    (30.00, 3, 2);
