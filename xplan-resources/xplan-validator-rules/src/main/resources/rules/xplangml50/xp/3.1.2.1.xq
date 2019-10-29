declare default element namespace 'http://www.xplanung.de/xplangml/5/0';
declare namespace gml='http://www.opengis.net/gml/3.2';
declare namespace xlink='http://www.w3.org/1999/xlink';

for $id in //praesentationsobjekt/@xlink:href/string()
where not(
	(//XP_FPO[@gml:id eq substring($id,2)]) or
	(//XP_LPO[@gml:id eq substring($id,2)]) or
	(//XP_PPO[@gml:id eq substring($id,2)]) or
	(//XP_Praesentationsobjekt[@gml:id eq substring($id,2)]) or
	(//XP_LTO[@gml:id eq substring($id,2)]) or
	(//XP_PTO[@gml:id eq substring($id,2)]) or
	(//XP_Nutzungsschablone[@gml:id eq substring($id,2)])
)
return substring($id,2)