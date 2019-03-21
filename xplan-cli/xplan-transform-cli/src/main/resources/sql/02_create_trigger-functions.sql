CREATE OR REPLACE FUNCTION updateTransformToolPlanPlanLog()
  RETURNS trigger AS
$$
BEGIN

   IF TG_OP = 'INSERT' THEN
     EXECUTE 'INSERT INTO xplanmgr.transformToolPlanTableLog VALUES($1, $2, $3, null, $4, $5)' USING NEW.id, NEW.xp_version, NEW.planstatus, TG_OP, now();
     RETURN OLD;
   END IF;

   IF TG_OP = 'UPDATE' THEN
     EXECUTE 'INSERT INTO xplanmgr.transformToolPlanTableLog VALUES($1, $2, $3, $4, $5, $6)' USING NEW.id, NEW.xp_version, NEW.planstatus, OLD.planstatus, TG_OP, now();
     RETURN OLD;
   END IF;

   IF TG_OP = 'DELETE' THEN
     EXECUTE 'INSERT INTO xplanmgr.transformToolPlanTableLog VALUES($1, $2, null, $3, $4, $5)' USING OLD.id, OLD.xp_version, OLD.planstatus, TG_OP, now();
     RETURN OLD;
   END IF;

END;
$$ LANGUAGE plpgsql;