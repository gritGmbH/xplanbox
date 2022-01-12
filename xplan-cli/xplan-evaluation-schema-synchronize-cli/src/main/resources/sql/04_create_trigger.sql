CREATE TRIGGER insert_xplanmgr_plans AFTER INSERT OR UPDATE OR DELETE ON xplanmgr.plans
  FOR EACH ROW
    EXECUTE PROCEDURE lgv.insertPlanLog();