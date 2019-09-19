package de.latlon.xplan.validator.semantic.report;

import java.util.List;

/**
 * contains the validator result of the rules of the semantic validation
 *
 * @author bingel
 */
public class RuleResult implements Comparable {

  private final String name;

  private final boolean isValid;

  private final String message;

  private final List<String> invalidFeatures;

  protected RuleResult( String name, boolean isValid, String message, List<String> invalidFeatures ) {
    this.name = name;
    this.isValid = isValid;
    this.message = message;
    this.invalidFeatures = invalidFeatures;
  }

  public String getName() {
    return name;
  }

  public boolean isValid() {
    return isValid;
  }

  public String getMessage() {
    return message;
  }

  public List<String> getInvalidFeatures() {
    return invalidFeatures;
  }

  @Override
  public String toString() {
    return "RuleResult{" +
           "name='" + name + '\'' +
           ", isValid=" + isValid +
           ", message='" + message + '\'' +
           '}';
  }

  @Override
  public int compareTo( Object o ) {
    return this.getName().compareTo( ( (RuleResult) o ).getName() );
  }
}