SET search_path TO xplanmgr,public;

CREATE TABLE planwerkwmsmetadata (
    plan integer references plans ON DELETE CASCADE,
    title text,
    resourceidentifier text,
    datametadataurl text,
    servicemetadataurl text
);
COMMENT ON TABLE planwerkwmsmetadata IS 'Metadata of plans provided in the capabilities of the PlanwerkWMS';