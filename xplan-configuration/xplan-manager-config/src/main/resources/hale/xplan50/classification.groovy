static def featureTypeName(sourceTypes) {
    if (sourceTypes != null && !sourceTypes.isEmpty()) {
        def sourceType = sourceTypes.get(0);
        if (sourceType != null
                && sourceType.getDefinition() != null
                && sourceType.getDefinition().getName() != null) {

            def localPart = sourceType.getDefinition().getName().getLocalPart();
            if (localPart != null && localPart.endsWith('Type'))
                return localPart.substring(0, localPart.length() - 4);
        }
    }
    return null;
}