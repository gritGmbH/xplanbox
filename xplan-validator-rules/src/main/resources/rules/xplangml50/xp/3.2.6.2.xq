declare default element namespace 'http://www.xplanung.de/xplangml/5/0';
declare namespace gml='http://www.opengis.net/gml/3.2';
declare namespace xlink='http://www.w3.org/1999/xlink';

every $id in //XP_VerbundenerPlan/verbundenerPlan/@xlink:href satisfies
exists(//BP_Plan[@gml:id= substring($id,2)]) or
exists(//FP_Plan[@gml:id= substring($id,2)]) or
exists(//LP_Plan[@gml:id= substring($id,2)]) or
exists(//RP_Plan[@gml:id= substring($id,2)]) or
exists(//SO_Plan[@gml:id= substring($id,2)])