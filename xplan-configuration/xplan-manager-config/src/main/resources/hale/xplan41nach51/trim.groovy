static def trimUrl(stringToTrim) {
    try {
        return stringToTrim.trim()
    } catch (Exception e1) {
        // ignore
    }
    return stringToTrim
}