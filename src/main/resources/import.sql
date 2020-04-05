CREATE TABLE workers (
    id serial PRIMARY KEY,
    name VARCHAR(255) NOT NULL,
    type INTEGER NOT NULL,
    design INTEGER NOT NULL,
    programming INTEGER NOT NULL,
    marketing INTEGER NOT NULL,
    salary INTEGER NOT NULL,
    companyId INTEGER
);