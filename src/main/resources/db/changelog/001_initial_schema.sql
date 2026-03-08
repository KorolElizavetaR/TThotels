CREATE TABLE brand (
    id UUID DEFAULT RANDOM_UUID() PRIMARY KEY,
    brand_name VARCHAR(255)
);

CREATE TABLE country (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(255)
);

CREATE TABLE city (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(255),
    country_id BIGINT REFERENCES country(id)
);

CREATE TABLE hotel (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(255) NOT NULL,
    description TEXT NULL,
    brand_id UUID NOT NULL REFERENCES brand(id),
    address JSON,
    city_id BIGINT REFERENCES city(id),
    check_in TIME,
    check_out TIME
);

CREATE TABLE contact_info (
    hotel_id BIGINT PRIMARY KEY REFERENCES hotel(id),
    phone VARCHAR,
    email VARCHAR
);

CREATE TABLE amenities (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(255) NOT NULL UNIQUE
);

CREATE TABLE amenities_hotel (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    hotel_id BIGINT REFERENCES hotel(id),
    amenities_id BIGINT REFERENCES amenities(id),
    UNIQUE(hotel_id, amenities_id)
);
