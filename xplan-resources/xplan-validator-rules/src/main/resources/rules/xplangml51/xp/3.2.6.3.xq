declare default element namespace 'http://www.xplanung.de/xplangml/5/1';

every $h in //XP_VerbundenerPlan satisfies
(exists($h/verbundenerPlan) or exists($h/planName)) and
not(exists($h/verbundenerPlan) and exists($h/planName))