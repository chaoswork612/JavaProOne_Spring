CREATE SCHEMA spring;
ALTER SCHEMA spring OWNER TO "postgres_user";
CREATE TABLE spring.users (
    id BIGSERIAL PRIMARY KEY,
    username VARCHAR(255) UNIQUE
);