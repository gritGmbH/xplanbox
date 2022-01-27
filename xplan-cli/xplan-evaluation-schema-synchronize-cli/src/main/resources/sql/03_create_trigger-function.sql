CREATE OR REPLACE FUNCTION xplanevaluation.insertPlanLog()
  RETURNS trigger AS
$$
BEGIN

   IF TG_OP = 'INSERT' THEN
     EXECUTE 'INSERT INTO xplanevaluation.planTableLog (xplanmgrid, xp_version, newplanstatus, operation, datum) VALUES($1, $2, $3, $4, $5)' USING NEW.id, NEW.xp_version, NEW.planstatus, TG_OP, now();
     RETURN OLD;
   END IF;

   IF TG_OP = 'UPDATE' THEN
     EXECUTE 'INSERT INTO xplanevaluation.planTableLog (xplanmgrid, xp_version, newplanstatus, oldplanstatus, operation, datum) VALUES($1, $2, $3, $4, $5, $6)' USING NEW.id, NEW.xp_version, NEW.planstatus, OLD.planstatus, TG_OP, now();
     RETURN OLD;
   END IF;

   IF TG_OP = 'DELETE' THEN
     EXECUTE 'INSERT INTO xplanevaluation.planTableLog (xplanmgrid, xp_version, oldplanstatus, operation, datum) VALUES($1, $2, $3, $4, $5)' USING OLD.id, OLD.xp_version, OLD.planstatus, TG_OP, now();
     RETURN OLD;
   END IF;

END;
$$ LANGUAGE plpgsql;