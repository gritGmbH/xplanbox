declare default element namespace 'http://www.xplanung.de/xplangml/5/1';
declare namespace gml='http://www.opengis.net/gml/3.2';
declare namespace xlink='http://www.w3.org/1999/xlink';

(
  every $h in //BP_Bereich/praesentationsobjekt/@xlink:href satisfies
  let $id :=substring($h,2) return
  exists(//XP_FPO[@gml:id = $id]) or
  exists(//XP_LPO[@gml:id = $id]) or
  exists(//XP_PPO[@gml:id = $id]) or
  exists(//XP_Praesentationsobjekt[@gml:id = $id]) or
  exists(//XP_TPO[@gml:id = $id]) or
  exists(//XP_LTO[@gml:id = $id]) or
  exists(//XP_PTO[@gml:id = $id]) or
  exists(//XP_Nutzungsschablone[@gml:id = $id])
)
and
(
  every $h in //FP_Bereich/praesentationsobjekt/@xlink:href satisfies
  let $id :=substring($h,2) return
  exists(//XP_FPO[@gml:id = $id]) or
  exists(//XP_LPO[@gml:id = $id]) or
  exists(//XP_PPO[@gml:id = $id]) or
  exists(//XP_Praesentationsobjekt[@gml:id = $id]) or
  exists(//XP_TPO[@gml:id = $id]) or
  exists(//XP_LTO[@gml:id = $id]) or
  exists(//XP_PTO[@gml:id = $id]) or
  exists(//XP_Nutzungsschablone[@gml:id = $id])
)
and
(
  every $h in //LP_Bereich/praesentationsobjekt/@xlink:href satisfies
  let $id :=substring($h,2) return
  exists(//XP_FPO[@gml:id = $id]) or
  exists(//XP_LPO[@gml:id = $id]) or
  exists(//XP_PPO[@gml:id = $id]) or
  exists(//XP_Praesentationsobjekt[@gml:id = $id]) or
  exists(//XP_TPO[@gml:id = $id]) or
  exists(//XP_LTO[@gml:id = $id]) or
  exists(//XP_PTO[@gml:id = $id]) or
  exists(//XP_Nutzungsschablone[@gml:id = $id])
)
and
(
  every $h in //RP_Bereich/praesentationsobjekt/@xlink:href satisfies
  let $id :=substring($h,2) return
  exists(//XP_FPO[@gml:id = $id]) or
  exists(//XP_LPO[@gml:id = $id]) or
  exists(//XP_PPO[@gml:id = $id]) or
  exists(//XP_Praesentationsobjekt[@gml:id = $id]) or
  exists(//XP_TPO[@gml:id = $id]) or
  exists(//XP_LTO[@gml:id = $id]) or
  exists(//XP_PTO[@gml:id = $id]) or
  exists(//XP_Nutzungsschablone[@gml:id = $id])
)
and
(
  every $h in //SO_Bereich/praesentationsobjekt/@xlink:href satisfies
  let $id :=substring($h,2) return
  exists(//XP_FPO[@gml:id = $id]) or
  exists(//XP_LPO[@gml:id = $id]) or
  exists(//XP_PPO[@gml:id = $id]) or
  exists(//XP_Praesentationsobjekt[@gml:id = $id]) or
  exists(//XP_TPO[@gml:id = $id]) or
  exists(//XP_LTO[@gml:id = $id]) or
  exists(//XP_PTO[@gml:id = $id]) or
  exists(//XP_Nutzungsschablone[@gml:id = $id])
)