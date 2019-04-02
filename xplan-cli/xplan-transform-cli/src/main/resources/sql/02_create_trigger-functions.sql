CREATE OR REPLACE FUNCTION xplanmgr.updateTransformToolPlanPlanLog()
  RETURNS trigger AS
$$
DECLARE
  fids text[];
BEGIN

   IF TG_OP = 'INSERT' THEN
     EXECUTE 'INSERT INTO xplanmgr.transformToolPlanTableLog (xplanmgrid, xp_version, newplanstatus, operation, datum) VALUES($1, $2, $3, $4, $5)' USING NEW.id, NEW.xp_version, NEW.planstatus, TG_OP, now();
     RETURN OLD;
   END IF;

   IF TG_OP = 'UPDATE' THEN
     EXECUTE 'SELECT ARRAY(SELECT fid FROM xplanmgr.features WHERE plan= $1)' INTO fids USING NEW.id;
     EXECUTE 'INSERT INTO xplanmgr.transformToolPlanTableLog (xplanmgrid, xp_version, newplanstatus, oldplanstatus, operation, datum, fids) VALUES($1, $2, $3, $4, $5, $6, $7)' USING NEW.id, NEW.xp_version, NEW.planstatus, OLD.planstatus, TG_OP, now(), fids;
     RETURN OLD;
   END IF;

   IF TG_OP = 'DELETE' THEN
     EXECUTE 'SELECT ARRAY(SELECT fid FROM xplanmgr.features WHERE plan= $1)' INTO fids USING OLD.id;
     EXECUTE 'INSERT INTO xplanmgr.transformToolPlanTableLog (xplanmgrid, xp_version, oldplanstatus, operation, datum, fids) VALUES($1, $2, $3, $4, $5, $6)' USING OLD.id, OLD.xp_version, OLD.planstatus, TG_OP, now(), fids;
     RETURN OLD;
   END IF;

END;
$$ LANGUAGE plpgsql;