static def trimUrl( stringToTrim ) {
  if ( stringToTrim ==~ /.*\s.*/ ) {
   // if ( stringToTrim.contains(" ") ) {
    return stringToTrim.trim()
  }
  return stringToTrim
}