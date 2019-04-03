CREATE TRIGGER insert_xplanmgr_plans_transform_tool AFTER INSERT OR UPDATE ON xplanmgr.plans
  FOR EACH ROW
    EXECUTE PROCEDURE xplanmgr.updateTransformToolPlanPlanLog();

CREATE TRIGGER delete_xplanmgr_plans_transform_tool BEFORE DELETE ON xplanmgr.plans
  FOR EACH ROW
    EXECUTE PROCEDURE xplanmgr.updateTransformToolPlanPlanLog();
