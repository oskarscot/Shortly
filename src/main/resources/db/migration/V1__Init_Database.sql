CREATE SEQUENCE IF NOT EXISTS urls_seq START WITH 1 INCREMENT BY 50;

CREATE TABLE urls
(
    id           BIGINT NOT NULL,
    short_code   VARCHAR(255),
    redirect_url VARCHAR(255),
    CONSTRAINT pk_urls PRIMARY KEY (id)
);