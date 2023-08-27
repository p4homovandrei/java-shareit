drop table IF EXISTS bookings;
drop table IF EXISTS status;
drop table IF EXISTS items;
drop table IF EXISTS users;


create TABLE IF NOT EXISTS users(
  id BIGINT GENERATED BY DEFAULT AS IDENTITY PRIMARY KEY NOT NULL,
  name VARCHAR(255) NOT NULL,
  email VARCHAR(512) NOT NULL,
  CONSTRAINT pk_user PRIMARY KEY (id),
  CONSTRAINT UQ_USER_EMAIL UNIQUE (email)
);

create TABLE IF NOT EXISTS items(
      id INTEGER GENERATED BY DEFAULT AS IDENTITY PRIMARY KEY,
      name VARCHAR(64) NOT NULL,
      available BOOLEAN NOT NULL,
      description VARCHAR(64),
      owner BIGINT NOT NULL REFERENCES users(id) ON DELETE CASCADE
);

create TABLE IF NOT EXISTS bookings(
    id INTEGER GENERATED BY DEFAULT AS IDENTITY PRIMARY KEY,
    start_time TIMESTAMP  NOT NULL,
    end_time TIMESTAMP  NOT NULL,
    status INTEGER,
    booker_id BIGINT NOT NULL REFERENCES users(id) ON DELETE CASCADE,
    item_Id BIGINT NOT NULL REFERENCES items(id) ON DELETE CASCADE
);

create TABLE IF NOT EXISTS status(
   id INTEGER GENERATED BY DEFAULT AS IDENTITY PRIMARY KEY,
   name VARCHAR(64) NOT NULL
);
