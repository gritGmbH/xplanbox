DROP INDEX xplansyn.spatial_idx_493;
DROP INDEX xplansyn.spatial_idx_494;
DROP INDEX xplansyn.spatial_idx_495;
DROP INDEX xplansyn.spatial_idx_496;
DROP INDEX xplansyn.spatial_idx_497;
DROP INDEX xplansyn.spatial_idx_498;
DROP INDEX xplansyn.spatial_idx_499;
DROP INDEX xplansyn.spatial_idx_500;

DROP INDEX xplansynarchive.spatial_idx_493;
DROP INDEX xplansynarchive.spatial_idx_494;
DROP INDEX xplansynarchive.spatial_idx_495;
DROP INDEX xplansynarchive.spatial_idx_496;
DROP INDEX xplansynarchive.spatial_idx_497;
DROP INDEX xplansynarchive.spatial_idx_498;
DROP INDEX xplansynarchive.spatial_idx_499;
DROP INDEX xplansynarchive.spatial_idx_500;

DROP INDEX xplansynpre.spatial_idx_493;
DROP INDEX xplansynpre.spatial_idx_494;
DROP INDEX xplansynpre.spatial_idx_495;
DROP INDEX xplansynpre.spatial_idx_496;
DROP INDEX xplansynpre.spatial_idx_497;
DROP INDEX xplansynpre.spatial_idx_498;
DROP INDEX xplansynpre.spatial_idx_499;
DROP INDEX xplansynpre.spatial_idx_500;

CREATE INDEX spatial_idx_493 ON xplansyn.xplan_bp_abweichungvonbaugrenze  USING GIST (xplan_position);
CREATE INDEX spatial_idx_494 ON xplansyn.xplan_bp_abweichungvonueberbauberergrundstuecksflaeche  USING GIST (xplan_position);
CREATE INDEX spatial_idx_495 ON xplansyn.xplan_bp_richtungssektorgrenze  USING GIST (xplan_position);
CREATE INDEX spatial_idx_496 ON xplansyn.xplan_bp_zusatzkontingentlaermflaeche  USING GIST (xplan_position);
CREATE INDEX spatial_idx_497 ON xplansyn.xplan_bp_zusatzkontingentlaerm  USING GIST (xplan_position);
CREATE INDEX spatial_idx_498 ON xplansyn.xplan_bp_sichtflaeche  USING GIST (xplan_position);
CREATE INDEX spatial_idx_499 ON xplansyn.xplan_so_bauverbotszone  USING GIST (xplan_position);
CREATE INDEX spatial_idx_500 ON xplansyn.xplan_so_gewaesser  USING GIST (xplan_position);

CREATE INDEX spatial_idx_493 ON xplansynarchive.xplan_bp_abweichungvonbaugrenze  USING GIST (xplan_position);
CREATE INDEX spatial_idx_494 ON xplansynarchive.xplan_bp_abweichungvonueberbauberergrundstuecksflaeche  USING GIST (xplan_position);
CREATE INDEX spatial_idx_495 ON xplansynarchive.xplan_bp_richtungssektorgrenze  USING GIST (xplan_position);
CREATE INDEX spatial_idx_496 ON xplansynarchive.xplan_bp_zusatzkontingentlaermflaeche  USING GIST (xplan_position);
CREATE INDEX spatial_idx_497 ON xplansynarchive.xplan_bp_zusatzkontingentlaerm  USING GIST (xplan_position);
CREATE INDEX spatial_idx_498 ON xplansynarchive.xplan_bp_sichtflaeche  USING GIST (xplan_position);
CREATE INDEX spatial_idx_499 ON xplansynarchive.xplan_so_bauverbotszone  USING GIST (xplan_position);
CREATE INDEX spatial_idx_500 ON xplansynarchive.xplan_so_gewaesser  USING GIST (xplan_position);

CREATE INDEX spatial_idx_493 ON xplansynpre.xplan_bp_abweichungvonbaugrenze  USING GIST (xplan_position);
CREATE INDEX spatial_idx_494 ON xplansynpre.xplan_bp_abweichungvonueberbauberergrundstuecksflaeche  USING GIST (xplan_position);
CREATE INDEX spatial_idx_495 ON xplansynpre.xplan_bp_richtungssektorgrenze  USING GIST (xplan_position);
CREATE INDEX spatial_idx_496 ON xplansynpre.xplan_bp_zusatzkontingentlaermflaeche  USING GIST (xplan_position);
CREATE INDEX spatial_idx_497 ON xplansynpre.xplan_bp_zusatzkontingentlaerm  USING GIST (xplan_position);
CREATE INDEX spatial_idx_498 ON xplansynpre.xplan_bp_sichtflaeche  USING GIST (xplan_position);
CREATE INDEX spatial_idx_499 ON xplansynpre.xplan_so_bauverbotszone  USING GIST (xplan_position);
CREATE INDEX spatial_idx_500 ON xplansynpre.xplan_so_gewaesser  USING GIST (xplan_position);