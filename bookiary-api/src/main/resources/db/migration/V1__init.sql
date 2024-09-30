-- Users
CREATE TABLE users
(
    id         BIGINT PRIMARY KEY AUTO_INCREMENT,
    nickname   VARCHAR(255),
    created_at TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
    updated_at TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP ON update CURRENT_TIMESTAMP
);

-- Auth Provider
CREATE TABLE auth_provider
(
    id          BIGINT PRIMARY KEY AUTO_INCREMENT,
    user_id     BIGINT       NOT NULL,
    type        VARCHAR(20)  NOT NULL,
    provider_id VARCHAR(255) NOT NULL,
    created_at  TIMESTAMP    NOT NULL DEFAULT CURRENT_TIMESTAMP,
    updated_at  TIMESTAMP    NOT NULL DEFAULT CURRENT_TIMESTAMP ON update CURRENT_TIMESTAMP
);

-- Refresh Token
CREATE TABLE refresh_token
(
    id         BIGINT PRIMARY KEY AUTO_INCREMENT,
    user_id    BIGINT       NOT NULL,
    token      VARCHAR(255) NOT NULL,
    expire_at  TIMESTAMP    NOT NULL,
    created_at TIMESTAMP    NOT NULL DEFAULT CURRENT_TIMESTAMP,
    updated_at TIMESTAMP    NOT NULL DEFAULT CURRENT_TIMESTAMP ON update CURRENT_TIMESTAMP
);

-- Book
CREATE TABLE book
(
    id             BIGINT PRIMARY KEY AUTO_INCREMENT,
    isbn           VARCHAR(20)  NOT NULL UNIQUE,
    title          VARCHAR(255) NOT NULL,
    author         VARCHAR(255) NOT NULL,
    publisher      VARCHAR(255) NOT NULL,
    image_url      TEXT,
    naver_book_url TEXT,
    description    TEXT,
    published_at   DATE,
    created_at     TIMESTAMP    NOT NULL DEFAULT CURRENT_TIMESTAMP,
    updated_at     TIMESTAMP    NOT NULL DEFAULT CURRENT_TIMESTAMP ON update CURRENT_TIMESTAMP
);

-- UserBook
CREATE TABLE user_book
(
    id         BIGINT PRIMARY KEY AUTO_INCREMENT,
    user_id    BIGINT    NOT NULL,
    book_id    BIGINT    NOT NULL,
    created_at TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
    updated_at TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP ON update CURRENT_TIMESTAMP
);

-- Note
CREATE TABLE note
(
    id           BIGINT PRIMARY KEY AUTO_INCREMENT,
    user_book_id BIGINT     NOT NULL,
    content      TEXT       NOT NULL,
    note_order   INT        NOT NULL,
    deleted      TINYINT(1) NOT NULL DEFAULT 0,
    deleted_at   TIMESTAMP,
    created_at   TIMESTAMP  NOT NULL DEFAULT CURRENT_TIMESTAMP,
    updated_at   TIMESTAMP  NOT NULL DEFAULT CURRENT_TIMESTAMP ON update CURRENT_TIMESTAMP
);
