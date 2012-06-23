# Feeds schema
 
# --- !Ups

CREATE SEQUENCE feed_id_seq;
CREATE TABLE feed (
    id integer NOT NULL DEFAULT nextval('feed_id_seq'),
    date TIMESTAMP NOT NULL DEFAULT CURRENT TIMESTAMP,
    label varchar(140) NOT NULL
);
 
# --- !Downs
 
DROP TABLE feed;
DROP SEQUENCE feed_id_seq;