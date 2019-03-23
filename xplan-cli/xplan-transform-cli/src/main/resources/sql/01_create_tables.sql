CREATE TABLE xplanmgr.transformToolPlanTableLog (
    id SERIAL,
    xplanmgrid int NOT NULL,
    xp_version text NOT NULL,
    newplanstatus text,
    oldplanstatus text,
    operation varchar(6) NOT NULL,
    datum timestamp,
    fids text []
);

COMMENT ON TABLE xplanmgr.transformToolPlanTableLog IS 'Logs inserted, updated and deleted plans for TransformTool';