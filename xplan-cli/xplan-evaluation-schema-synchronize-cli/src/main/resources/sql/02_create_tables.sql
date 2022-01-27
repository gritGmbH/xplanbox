SELECT xplanevaluation.clone_schema('xplansynpre','xplanevaluationxplansynpre');
SELECT xplanevaluation.clone_schema('xplansyn','xplanevaluationxplansyn');
SELECT xplanevaluation.clone_schema('xplansynarchive','xplanevaluationxplansynarchive');

CREATE TABLE xplanevaluation.planTableLog (
    id SERIAL,
    xplanmgrid int NOT NULL,
    xp_version text NOT NULL,
    newplanstatus text,
    oldplanstatus text,
    operation varchar(6) NOT NULL,
    datum timestamp
);

COMMENT ON TABLE xplanevaluation.planTableLog IS 'Logs inserted, updated and deleted plans';