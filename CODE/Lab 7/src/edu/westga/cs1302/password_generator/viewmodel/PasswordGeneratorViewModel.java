package edu.westga.cs1302.password_generator.viewmodel;

import edu.westga.cs1302.password_generator.model.PasswordGenerator;
import javafx.beans.property.BooleanProperty;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

/**
 * The class PassWordGeneratorViewModel
 * 
 * @author fmensah1
 * @version Fall 24
 */
public class PasswordGeneratorViewModel {
	
	private BooleanProperty mustIncludeDigits;
	private BooleanProperty mustIncludeLowerCaseLetters;
	private BooleanProperty mustIncludeUpperCaseLetters;
	private StringProperty minimumLength;
	private StringProperty output;
	private PasswordGenerator password;
	private StringProperty minimumIntegerErrorLabel;
	
	/**
	 * Instantiates a new password view model.
	 */
	public PasswordGeneratorViewModel() {
		this.mustIncludeDigits = new SimpleBooleanProperty(false);
		this.mustIncludeUpperCaseLetters = new SimpleBooleanProperty(false);
		this.mustIncludeLowerCaseLetters = new SimpleBooleanProperty(false);
		this.minimumLength = new SimpleStringProperty("1");
		this.output = new SimpleStringProperty("");
		this.password = new PasswordGenerator(0);
	}
	
	/**
	 * Gets the must include digits property.
	 *
	 * @return the must include digits Property.
	 */
	public BooleanProperty getMustIncludeDigits() {
		return this.mustIncludeDigits;
	}
	
	/**
	 * Gets the Must Include LowerCase Letter property.
	 *
	 * @return the must Include LowerCase Letter property.
	 */
	public BooleanProperty getMustIncludeLowerCaseLetters() {
		return this.mustIncludeLowerCaseLetters;
	}

	/**
	 * Gets the Must Include UpperCase Letter property.
	 *
	 * @return the must Include UpperCase Letter property.
	 */
	public BooleanProperty getMustIncludeUpperCaseLetters() {
		return this.mustIncludeUpperCaseLetters;
	}

	/**
	 * Gets the Minimum Length property.
	 *
	 * @return Minimum Length property.
	 */
	public StringProperty getMinimumLength() {
		return this.minimumLength;
	}

	/**
	 * Gets the output property.
	 *
	 * @return output property.
	 */
	public StringProperty getOutput() {
		return this.output;
	}
	
	/**
     * Creates a password based on the set properties.
     */
	public void generatePassword() {
	    // Clear any previous error messages
	    this.minimumIntegerErrorLabel.setValue("");
	    
	    if (!this.minimumLength.getValue().isEmpty()) {
	        int minLength;

	        try {
	            minLength = Integer.parseInt(this.minimumLength.getValue());
	            if (minLength <= 0) {
	                throw new IllegalArgumentException("Minimum length must be a positive integer.");
	            }
	            this.password.setMinimumLength(minLength); 
	        } catch (NumberFormatException numberError) {
	            this.minimumIntegerErrorLabel.setValue("Invalid Minimum Length: must be a positive integer.");
	            this.output.set("");
	            return;
	        } catch (IllegalArgumentException error) {
	            this.minimumIntegerErrorLabel.setValue("Invalid Minimum Length: " + error.getMessage());
	            this.output.set(""); 
	            return;
	        }
	    } else {
	        this.minimumIntegerErrorLabel.setValue("Minimum length is required.");
	        this.output.set(""); 
	        return;
	    }

	    this.password.setMustHaveAtLeastOneUpperCaseLetter(this.mustIncludeUpperCaseLetters.getValue());
	    this.password.setMustHaveAtLeastOneLowerCaseLetter(this.mustIncludeLowerCaseLetters.getValue());
	    this.password.setMustHaveAtLeastOneDigit(this.mustIncludeDigits.getValue());

	    this.output.set(this.password.generatePassword());
	}
}
