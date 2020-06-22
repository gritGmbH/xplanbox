UPDATE xplanmgr.plans SET internalid = xplan_internalid FROM xplansyn.xplan_bp_plan syn WHERE id = syn.xplan_mgr_planid;
UPDATE xplanmgr.plans SET internalid = xplan_internalid FROM xplansyn.xplan_lp_plan syn WHERE id = syn.xplan_mgr_planid;
UPDATE xplanmgr.plans SET internalid = xplan_internalid FROM xplansyn.xplan_fp_plan syn WHERE id = syn.xplan_mgr_planid;
UPDATE xplanmgr.plans SET internalid = xplan_internalid FROM xplansyn.xplan_rp_plan syn WHERE id = syn.xplan_mgr_planid;
UPDATE xplanmgr.plans SET internalid = xplan_internalid FROM xplansyn.xplan_so_plan syn WHERE id = syn.xplan_mgr_planid;
