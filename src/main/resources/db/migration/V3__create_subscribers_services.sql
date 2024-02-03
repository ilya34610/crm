CREATE TABLE subscribers_services
(
    id_subscriber BIGINT NOT NULL REFERENCES subscribers(id),
    id_service INT NOT NULL REFERENCES services(id),
    PRIMARY KEY (id_subscriber, id_service)
);