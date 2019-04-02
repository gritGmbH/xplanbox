/* --- BEGIN schema setup --- */

CREATE SCHEMA xplanmgr;
SET search_path TO xplanmgr,public;

/* --- END schema setup --- */

CREATE TABLE plans (
    id serial PRIMARY KEY,
    import_date timestamp NOT NULL,
    xp_version text NOT NULL,
    xp_type text NOT NULL,
    ade text,
    name text,
    nummer text,    
    gkz text,
    has_raster boolean NOT NULL,
    rechtsstand text,
    release_date date,
    sonst_plan_art text,
    planstatus text,
    district text,
    wmsSortDate date,
    gueltigkeitBeginn timestamp,
    gueltigkeitEnde timestamp,
    inspirepublished boolean DEFAULT FALSE,
    bbox geometry
);
COMMENT ON TABLE plans IS 'Imported plans';

CREATE TABLE features (
    plan integer references plans ON DELETE CASCADE,
    fid text NOT NULL,
    num integer NOT NULL
);
COMMENT ON TABLE features IS 'Feature ids for plans';

CREATE TABLE artefacts (
    plan integer references plans ON DELETE CASCADE,
    filename text NOT NULL,
    data bytea NOT NULL,
    num integer NOT NULL,
    mimetype text NOT NULL
);
COMMENT ON TABLE plans IS 'Plan artefacts';
