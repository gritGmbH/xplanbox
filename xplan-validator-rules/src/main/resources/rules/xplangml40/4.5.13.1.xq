declare default element namespace 'http://www.xplanung.de/xplangml/4/1';
(
  every $h in //BP_NebenanlagenFlaeche satisfies
  (: Wenn nur eine Zweckbestimmung spezifiziert werden soll, muss dafür immer das Attribut zweckbestimmung verwendet werden. :)
  (
    if (
      exists($h/weitereZweckbestimmung1) or
      exists($h/weitereZweckbestimmung2) or
      exists($h/weitereZweckbestimmung3) or
      exists($h/weitereZweckbestimmung4))
    then (
      exists($h/zweckbestimmung)
    )
    else true()
  )
    and
    (: Wenn mehr als eine Zweckbestimmung durch unterschiedliche Attribute spezifiziert werden sollen, sind die Attribute weitereZweckbestimmung i (i = 1, 2, 3, 4) in aufsteigender Reihenfolge zu belegen. :)
  (
    if (
      exists($h/weitereZweckbestimmung1) and
      exists($h/weitereZweckbestimmung2) and
      exists($h/weitereZweckbestimmung3) and
      exists($h/weitereZweckbestimmung4)
    )
    then (
      true()
    )
    else if (
      exists($h/weitereZweckbestimmung1) and
      exists($h/weitereZweckbestimmung2) and
      exists($h/weitereZweckbestimmung3)
    )
    then (
        true()
      )
    else if (
        exists($h/weitereZweckbestimmung1) and
        exists($h/weitereZweckbestimmung2)
      )
      then (
          true()
        )
      else if (
          exists($h/weitereZweckbestimmung1)
        )
        then (
            true()
          )
        else if (
            not(exists($h/weitereZweckbestimmung1)) and
            not(exists($h/weitereZweckbestimmung2)) and
            not(exists($h/weitereZweckbestimmung3)) and
            not(exists($h/weitereZweckbestimmung4))
          )
          then (
              true()
            )
          else false()
  )
)