CREATE TABLE Tutorials
(
    id serial not null
        constraint tutorials_primary primary key,
    title varchar(40),
    description text,
    published boolean
)