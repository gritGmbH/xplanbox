CREATE TRIGGER insert_xplanmgr_plans_transform_tool AFTER INSERT OR UPDATE OR DELETE ON xplanmgr.plans
  FOR EACH ROW
    EXECUTE PROCEDURE updateTransformToolPlanPlanLog();