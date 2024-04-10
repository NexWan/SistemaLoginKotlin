CREATE DATABASE "Test" WITH OWNER = wan;

\c "Test"

CREATE TABLE "Users" (
    "username" TEXT PRIMARY KEY,
    "password" TEXT
);

INSERT INTO "Users" VALUES ('wan', '123');
INSERT INTO "Users" VALUES ('test', '123');

SELECT * FROM "Users";