-- 001_initial_schema.sql

-- Brand table
CREATE TABLE brand (
    id UUID DEFAULT RANDOM_UUID() PRIMARY KEY,
    brand_name VARCHAR(255)
);

-- Country table
CREATE TABLE country (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(255)
);

-- City table
CREATE TABLE city (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(255),
    country_id BIGINT REFERENCES country(id)
);

-- Hotel table
CREATE TABLE hotel (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(255) NOT NULL,
    description TEXT NOT NULL,
    brand_id UUID NOT NULL REFERENCES brand(id),
    address OTHER,
    contact OTHER,
    city_id BIGINT REFERENCES city(id),
    check_in TIME,
    check_out TIME
);

-- Contact info table
CREATE TABLE contact_info (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    phone VARCHAR,
    email VARCHAR,
    hotel_id BIGINT REFERENCES hotel(id)
);

-- Amenities table
CREATE TABLE amenities (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(255) NOT NULL
);

-- Amenities hotel junction table
CREATE TABLE amenities_hotel (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    hotel_id BIGINT REFERENCES hotel(id),
    amenities_id BIGINT REFERENCES amenities(id),
    UNIQUE(hotel_id, amenities_id)
);
