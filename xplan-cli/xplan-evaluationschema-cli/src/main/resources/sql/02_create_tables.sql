SELECT lgv.clone_schema('xplansynpre','lgvxplansynpre');
SELECT lgv.clone_schema('xplansyn','lgvxplansyn');
SELECT lgv.clone_schema('xplansynarchive','lgvxplansynarchive');

CREATE TABLE lgv.lgvPlanTableLog (
    id SERIAL,
    xplanmgrid int NOT NULL,
    xp_version text NOT NULL,
    newplanstatus text,
    oldplanstatus text,
    operation varchar(6) NOT NULL,
    datum timestamp
);

COMMENT ON TABLE lgv.lgvPlanTableLog IS 'Logs inserted, updated and deleted plans';