-- Users table
CREATE TABLE users (
    userid SERIAL PRIMARY KEY,
    username VARCHAR(50) UNIQUE NOT NULL,
    password VARCHAR(255) NOT NULL,
    email VARCHAR(100),
    phonenumber VARCHAR(20),
    address VARCHAR(255),
    role VARCHAR(20) NOT NULL
);

-- Memberships table
CREATE TABLE memberships (
    membershipid SERIAL PRIMARY KEY,
    membershiptype VARCHAR(50),
    description TEXT,
    membershipcost NUMERIC(10,2),
    memberid INT REFERENCES users(userid)
);

-- Gym Merchandise table
CREATE TABLE gymmerch (
    merchid SERIAL PRIMARY KEY,
    merchname VARCHAR(100),
    merchtype VARCHAR(50),
    merchprice NUMERIC(10,2),
    quantityinstock INT
);

-- Workout Classes table
CREATE TABLE workoutclasses (
    workoutclassid SERIAL PRIMARY KEY,
    type VARCHAR(50),
    description TEXT,
    trainerid INT REFERENCES users(userid)
);
