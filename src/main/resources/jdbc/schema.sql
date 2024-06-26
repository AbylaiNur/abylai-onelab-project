-- Create USERS table
CREATE TABLE IF NOT EXISTS USERS (
                                     id BIGINT AUTO_INCREMENT PRIMARY KEY,
                                     username VARCHAR(255) NOT NULL
);

-- Create MOVIES table
CREATE TABLE IF NOT EXISTS MOVIES (
                                      id BIGINT AUTO_INCREMENT PRIMARY KEY,
                                      title VARCHAR(255) NOT NULL,
                                      description TEXT NOT NULL
);

-- Create COMMENTS table
CREATE TABLE IF NOT EXISTS COMMENTS (
                                        id BIGINT AUTO_INCREMENT PRIMARY KEY,
                                        text TEXT NOT NULL,
                                        user_id BIGINT,
                                        movie_id BIGINT,
                                        FOREIGN KEY (user_id) REFERENCES USERS(id) ON DELETE CASCADE,
                                        FOREIGN KEY (movie_id) REFERENCES MOVIES(id) ON DELETE CASCADE
);

CREATE TABLE IF NOT EXISTS RATES (
                                        id BIGINT AUTO_INCREMENT PRIMARY KEY,
                                        rate INT NOT NULL,
                                        user_id BIGINT,
                                        movie_id BIGINT,
                                        created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
                                        FOREIGN KEY (user_id) REFERENCES USERS(id) ON DELETE CASCADE,
                                        FOREIGN KEY (movie_id) REFERENCES MOVIES(id) ON DELETE CASCADE
);

CREATE TABLE IF NOT EXISTS GENRES (
                                        id BIGINT AUTO_INCREMENT PRIMARY KEY,
                                        name VARCHAR(255) NOT NULL
);

CREATE TABLE IF NOT EXISTS ACTORS (
                                        id BIGINT AUTO_INCREMENT PRIMARY KEY,
                                        name VARCHAR(255) NOT NULL,
                                        surname VARCHAR(255) NOT NULL

);

CREATE TABLE IF NOT EXISTS MOVIES_GENRES (
                                        id BIGINT AUTO_INCREMENT PRIMARY KEY,
                                        movie_id BIGINT,
                                        genre_id BIGINT,
                                        FOREIGN KEY (movie_id) REFERENCES MOVIES(id) ON DELETE CASCADE,
                                        FOREIGN KEY (genre_id) REFERENCES GENRES(id) ON DELETE CASCADE
);

CREATE TABLE IF NOT EXISTS MOVIES_ACTORS (
                                        id BIGINT AUTO_INCREMENT PRIMARY KEY,
                                        movie_id BIGINT,
                                        actor_id BIGINT,
                                        FOREIGN KEY (movie_id) REFERENCES MOVIES(id) ON DELETE CASCADE,
                                        FOREIGN KEY (actor_id) REFERENCES ACTORS(id) ON DELETE CASCADE
);

