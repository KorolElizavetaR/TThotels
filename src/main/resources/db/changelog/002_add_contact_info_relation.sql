-- 002_add_contact_info_relation.sql
-- Make contact_info.hotel_id NOT NULL and UNIQUE (required 1-to-1 relation)

ALTER TABLE contact_info ALTER COLUMN hotel_id BIGINT NOT NULL;
ALTER TABLE contact_info ADD CONSTRAINT uk_contact_info_hotel UNIQUE (hotel_id);
