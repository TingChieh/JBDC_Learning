CREATE TABLE Users(
    id INT PRIMARY KEY AUTO_INCREMENT,
    email VARCHAR(255) NOT NULL UNIQUE,
    bio TEXT,
    country VARCHAR(2)
);

INSERT INTO Rooms (owner_id, street)
VALUES
    (1, 'san diego sailboat'),
    (1, 'nantucket cottage'),
    (1, 'vail cabin'),
    (1, 'sf cardboard box')