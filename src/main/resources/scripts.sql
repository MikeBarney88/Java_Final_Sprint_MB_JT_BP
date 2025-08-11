CREATE TABLE IF NOT EXISTS users (
                    user_id SERIAL PRIMARY KEY,
                    user_username TEXT NOT NULL UNIQUE,
                    user_password TEXT NOT NULL,
                    user_email TEXT NOT NULL UNIQUE,
                    user_address TEXT NOT NULL,
                    user_phoneNumber TEXT NOT NULL UNIQUE,
                    user_role TEXT NOT NULL
);

CREATE TABLE IF NOT EXISTS memberships (
                    membership_id SERIAL PRIMARY KEY,
                    membership_type TEXT NOT NULL,
                    membership_description TEXT NOT NULL,
                    membership_cost DECIMAL(10, 2) NOT NULL,
                    user_id INTEGER NOT NULL REFERENCES users(user_id)
);

CREATE TABLE IF NOT EXISTS workout_classes (
                    workoutClass_id SERIAL PRIMARY KEY,
                    workoutClass_type TEXT NOT NULL,
                    workoutClass_description TEXT NOT NULL,
                    trainer_id INTEGER NOT NULL REFERENCES users(user_id)
);

CREATE TABLE IF NOT EXISTS gym_products (
                    product_id SERIAL PRIMARY KEY,
                    product_name TEXT NOT NULL,
                    category TEXT NOT NULL,
                    price DECIMAL(10,2) NOT NULL,
                    stock_quantity INTEGER NOT NULL,
                    description TEXT NOT NULL
);