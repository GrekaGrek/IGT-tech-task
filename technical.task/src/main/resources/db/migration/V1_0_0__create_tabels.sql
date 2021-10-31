CREATE TABLE customer
(
    id              BIGINT          NOT NULL IDENTITY,
	username        VARCHAR (50)    UNIQUE NOT NULL,
	surname         VARCHAR (50)    UNIQUE NOT NULL,
	password        VARCHAR (50)    NOT NULL,
	email           VARCHAR (255)   UNIQUE NOT NULL,
	country         VARCHAR (50)    NOT NULL,
	created_date    DATETIME        NOT NULL DEFAULT CURRENT_TIMESTAMP,
    last_modified   DATETIME        NOT NULL DEFAULT CURRENT_TIMESTAMP,

    CONSTRAINT customer_pk PRIMARY KEY (id)
);

CREATE TABLE customer_debt_cases
(
    id          BIGINT      NOT NULL IDENTITY,
    customer_id BIGINT      NOT NULL,
    amount      BIGINT      NUMERIC(18, 8)
    currency    VARCHAR(3)  NOT NULL,
    due_date    DATE        NOT NULL,

    CONSTRAINT customer_debt_cases_pk PRIMARY KEY (id),
    CONSTRAINT customer_debt_cases__customer_id_fk FOREIGN KEY (customer_id)
        REFERENCES customer_id (id) ON DELETE CASCADE
);

CREATE INDEX customer_debt_cases_customer_id_index ON customer_debt_cases (customer_id);