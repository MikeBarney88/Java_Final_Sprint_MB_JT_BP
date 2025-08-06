CREATE TABLE IF NOT EXISTS users (
                    user_id SERIAL PRIMARY KEY,
                    user_username TEXT NOT NULL,
                    user_address TEXT NOT NULL,
                    user_phoneNumber TEXT NOT NULL,
                    user_role TEXT NOT NULL,
                    user_password TEXT
);

CREATE TABLE IF NOT EXISTS memberships (
                    membership_id SERIAL PRIMARY KEY,
                    membership_type TEXT NOT NULL,
                    membership_description TEXT NOT NULL,
                    membership_cost INTEGER NOT NULL,
--                    *** member_id OR user_id TEXT NOT NULL ***
);

CREATE TABLE IF NOT EXISTS workout_classes (
                    workoutClass_id SERIAL PRIMARY KEY,
                    workoutClass_type TEXT NOT NULL,
                    workoutClass_Description TEXT NOT NULL,
                    trainer_id TEXT NOT NULL
);

CREATE TABLE IF NOT EXISTS gym_products (
                    product_id SERIAL PRIMARY KEY,
                    product_name TEXT NOT NULL,
                    category TEXT NOT NULL,
                    price DECIMAL(10,2) NOT NULL,
                    stock_quantity INTEGER NOT NULL,
                    description TEXT
);