CREATE TABLE books (
    id SERIAL PRIMARY KEY,
    name TEXT,
    author TEXT,
    year TEXT,
    genre TEXT,
    userid INTEGER
);


CREATE TABLE "user" (
    "id" SERIAL PRIMARY KEY,
    "login" TEXT NOT NULL,
    "password" TEXT NOT NULL
);

INSERT INTO "public"."books" ("name", "author", "year", "genre") VALUES ('test', 'test', '1998.01.01', 'test',1);

INSERT INTO "public"."user" ("login", "password") VALUES ('test', 'test');