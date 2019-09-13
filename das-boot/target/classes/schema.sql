DROP TABLE IF EXISTS "subscription";
DROP TABLE IF EXISTS "subscriber";
DROP TABLE IF EXISTS "person";
DROP TABLE IF EXISTS "offer_good";
DROP TABLE IF EXISTS "offer";
DROP TABLE IF EXISTS "good";
DROP TABLE IF EXISTS "goodtype";


CREATE TABLE "person" (
    "person_id" VARCHAR(11) PRIMARY KEY NOT NULL,
    "name" VARCHAR(30) NOT NULL,
    "surname" VARCHAR(30) NOT NULL,
    "birthdate" DATE,
    "gender" VARCHAR(6)
);

CREATE TABLE "subscriber" (
    "subscriber_id" SERIAL PRIMARY KEY NOT NULL,
    "person_id" VARCHAR(11) NOT NULL,
    "msisdn_no" VARCHAR(10) NOT NULL,
    "imsi" VARCHAR(15) NOT NULL,
    "balance" FLOAT,
    CONSTRAINT FK_subscriber_person
    FOREIGN KEY ("person_id") 
    REFERENCES "person" ("person_id"),
    CONSTRAINT "msisdn_no_imsi" UNIQUE ("msisdn_no", "imsi")
);

CREATE TABLE "offer" (
    "offer_id" SERIAL NOT NULL PRIMARY KEY,
    "name" VARCHAR(20),
    "start_date" TIMESTAMP(4) WITHOUT TIME ZONE NOT NULL,
    "end_date" TIMESTAMP(4) WITHOUT TIME ZONE NOT NULL,
    "price" FLOAT NOT NULL,
    "subscription_period" INTEGER NOT NULL,
    "charging_period" INTEGER NOT NULL,
    "recurring" BOOLEAN NOT NULL,
    "second_subscription" BOOLEAN NOT NULL,
    "description" TEXT
);

CREATE TABLE "goodtype" (
    "goodtype_id" SERIAL NOT NULL PRIMARY KEY,
    "name" VARCHAR(20) NOT NULL
);

CREATE TABLE "good" (
    "good_id" SERIAL NOT NULL PRIMARY KEY,
    "goodtype_id" INTEGER NOT NULL,
    "name" VARCHAR(20) NOT NULL,
    "amount" INTEGER NOT NULL,
    CONSTRAINT FK_good_goodtype
    FOREIGN KEY ("goodtype_id") 
    REFERENCES "goodtype" ("goodtype_id")
);

CREATE TABLE "offer_good" (
    "offer_id" INTEGER NOT NULL,
    "good_id" INTEGER NOT NULL,
    CONSTRAINT FK_offer_good_offer
    FOREIGN KEY ("offer_id") 
    REFERENCES "offer" ("offer_id"),
    CONSTRAINT FK_offer_good_good
    FOREIGN KEY ("good_id") 
    REFERENCES "good" ("good_id")
);

CREATE TABLE "subscription" (
    "subscription_id" SERIAL NOT NULL PRIMARY KEY,
    "subscriber_id" INTEGER NOT NULL,
    "offer_id" INTEGER NOT NULL,
    "start_date" TIMESTAMP(4) WITHOUT TIME ZONE NOT NULL,
    "end_date" TIMESTAMP(4) WITHOUT TIME ZONE NOT NULL,
    "status" VARCHAR(20) NOT NULL,
    "cost" FLOAT NOT NULL,
    "discount_amount" INTEGER,
    CONSTRAINT FK_subscription_subscriber
    FOREIGN KEY ("subscriber_id") 
    REFERENCES "subscriber" ("subscriber_id"),
    CONSTRAINT FK_subscription_offer
    FOREIGN KEY ("offer_id") 
    REFERENCES "offer" ("offer_id")
);