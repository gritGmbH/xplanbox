alter table xplansynarchive.xplan_bp_plan add column xplan_externereferenz text;
alter table xplansyn.xplan_bp_plan add column xplan_externereferenz text;
alter table xplansynpre.xplan_bp_plan add column xplan_externereferenz text;

alter table xplansynarchive.xplan_fp_plan add column xplan_externereferenz text;
alter table xplansyn.xplan_fp_plan add column xplan_externereferenz text;
alter table xplansynpre.xplan_p_plan add column xplan_externereferenz text;

alter table xplansynarchive.xplan_lp_plan add column xplan_externereferenz text;
alter table xplansyn.xplan_lp_plan add column xplan_externereferenz text;
alter table xplansynpre.xplan_lp_plan add column xplan_externereferenz text;

alter table xplansynarchive.xplan_so_plan add column xplan_externereferenz text;
alter table xplansyn.xplan_so_plan add column xplan_externereferenz text;
alter table xplansynpre.xplan_so_plan add column xplan_externereferenz text;