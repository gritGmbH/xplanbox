SET search_path TO xplanmgr,public;

CREATE TABLE bereiche (
    plan integer references plans ON DELETE CASCADE,
    nummer text NOT NULL,
    name text,
    PRIMARY KEY (plan,nummer)
);
COMMENT ON TABLE bereiche IS 'Plan Bereiche';