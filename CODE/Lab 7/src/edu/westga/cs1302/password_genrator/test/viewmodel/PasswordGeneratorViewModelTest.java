package edu.westga.cs1302.password_genrator.test.viewmodel;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;

import edu.westga.cs1302.password_generator.viewmodel.PasswordGeneratorViewModel;

class PasswordGeneratorViewModelTest {

    private PasswordGeneratorViewModel viewModel;


    @Test
    void testInitialState() {
    	this.viewModel = new PasswordGeneratorViewModel();
        assertFalse(this.viewModel.getMustIncludeDigits().get());
        assertFalse(this.viewModel.getMustIncludeUpperCaseLetters().get());
        assertFalse(this.viewModel.getMustIncludeLowerCaseLetters().get());
        assertEquals("1", this.viewModel.getMinimumLength().get());
        assertEquals("", this.viewModel.getOutput().get());
 
    }

    @Test
    void testGeneratePasswordWithValidMinimumLength() {
    	this.viewModel = new PasswordGeneratorViewModel();
        this.viewModel.getMinimumLength().set("8");
        this.viewModel.generatePassword();
        assertTrue(this.viewModel.getOutput().get().length() >= 8);
        assertEquals("", this.viewModel.getMinimumIntegerErrorLabel().get());
    }

    @Test
    void testGeneratePasswordWithEmptyMinimumLength() {
    	this.viewModel = new PasswordGeneratorViewModel();
    	this.viewModel = new PasswordGeneratorViewModel();
        this.viewModel.getMinimumLength().set("");
        this.viewModel.generatePassword();
        assertEquals("Minimum length is required.", this.viewModel.getMinimumIntegerErrorLabel().get());
        assertEquals("", this.viewModel.getOutput().get());
    }

    @Test
    void testGeneratePasswordWithNonIntegerMinimumLength() {
    	this.viewModel = new PasswordGeneratorViewModel();
        this.viewModel.getMinimumLength().set("abc");
        this.viewModel.generatePassword();
        assertEquals("Invalid Minimum Length: must be a positive integer.", this.viewModel.getMinimumIntegerErrorLabel().get());
        assertEquals("", this.viewModel.getOutput().get());
    }

    @Test
    void testGeneratePasswordWithNegativeMinimumLength() {
    	this.viewModel = new PasswordGeneratorViewModel();
        this.viewModel.getMinimumLength().set("-5");
        this.viewModel.generatePassword();
        assertEquals("Invalid Minimum Length: Minimum length must be a positive integer.", this.viewModel.getMinimumIntegerErrorLabel().get());
        assertEquals("", this.viewModel.getOutput().get());
    }

    @Test
    void testGeneratePasswordWithDigitRequirement() {
    	this.viewModel = new PasswordGeneratorViewModel();
        this.viewModel.getMinimumLength().set("8");
        this.viewModel.getMustIncludeDigits().set(true);
        this.viewModel.generatePassword();
        assertTrue(this.viewModel.getOutput().get().matches(".*\\d.*"));  // Ensures at least one digit is present
    }
 
}
