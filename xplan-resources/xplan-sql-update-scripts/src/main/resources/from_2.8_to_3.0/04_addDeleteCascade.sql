ALTER TABLE xplanmgr.features DROP CONSTRAINT features_plan_fkey;
ALTER TABLE xplanmgr.features
  ADD CONSTRAINT features_plan_fkey
  FOREIGN KEY (plan)
  REFERENCES xplanmgr.plans(id)
  ON DELETE CASCADE;

ALTER TABLE xplanmgr.artefacts DROP CONSTRAINT artefacts_plan_fkey;
ALTER TABLE xplanmgr.artefacts
  ADD CONSTRAINT features_plan_fkey
  FOREIGN KEY (plan)
  REFERENCES xplanmgr.plans(id)
  ON DELETE CASCADE;
