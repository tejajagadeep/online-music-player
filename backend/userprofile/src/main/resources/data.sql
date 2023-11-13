-- Create the user_profile table
CREATE TABLE user_profile (
    id INT PRIMARY KEY,
    username VARCHAR(255),
    first_name VARCHAR(255),
    last_name VARCHAR(255),
    number BIGINT,
    date_of_birth DATE,
    email VARCHAR(255),
    password VARCHAR(255)
);

-- Insert data into the user_profile table
INSERT INTO user_profile (id, username, first_name, last_name, number, date_of_birth, email, password)
VALUES
    (1, 'johndoe', 'John', 'Doe', 123456789, '1990-01-01', 'john.doe@example.com', '{bcrypt}$2a$10$CrYQ4MZGyFKcsRVdHzF.iu1lcFWHBcQx3i9faJj2I/KEwZ3ZNsflm'),
    (2, 'jagdeep', 'Jaga', 'Deep', 123456780, '1999-01-01', 'john.deep@gmail.com', '{bcrypt}$2a$10$CrYQ4MZGyFKcsRVdHzF.iu1lcFWHBcQx3i9faJj2I/KEwZ3ZNsflm'),
    (3, 'meghana', 'Meghana', 'Priya', 123456783, '1997-01-01', 'meghana.priya@example.com', '{bcrypt}$2a$10$CrYQ4MZGyFKcsRVdHzF.iu1lcFWHBcQx3i9faJj2I/KEwZ3ZNsflm');
