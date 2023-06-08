package com.example.park;

import Service.Datevalidator;
import View.Bookings;
import javafx.scene.layout.AnchorPane;
import org.junit.jupiter.api.*;

import static org.junit.jupiter.api.Assertions.*;

class HelloApplicationTest
{
    Datevalidator dateValidator = new Datevalidator();

    @BeforeEach
    void setUp()
    {
    }

    @AfterEach
    void tearDown()
    {
    }
    @Test
    void testValidDate() {
        System.out.println("Testing valid date ");
        assertTrue(dateValidator.isValidDate("2023-06-07"));
        System.out.println("Testing valid  month");
        assertTrue(dateValidator.isValidDate("2022-10-09"));
    }

    @Test
    void testInvalidDate() {
        System.out.println("Testing invalid date ");
        assertFalse(dateValidator.isValidDate("2023-06-32"));
        System.out.println("Testing invalid Month ");
        assertFalse(dateValidator.isValidDate("2022-13-01"));
        System.out.println("Testing invalid input ");
        assertFalse(dateValidator.isValidDate("abc"));
    }

    @Test
    void testBoundaryIn() {
        System.out.println("Testing bounder in month");
        assertTrue(dateValidator.isValidDate("2022-01-05"));
        System.out.println("Testing boundery in Day");
        assertTrue(dateValidator.isValidDate("2023-06-01"));
        System.out.println("Testing bounder in month & day");
        assertTrue(dateValidator.isValidDate("2023-12-31"));
    }

    @Test
    void testBoundaryOut() {
        System.out.println("Testing boundery out invalid day");
        assertFalse(dateValidator.isValidDate("2021-12-32"));
        System.out.println("Testing boundery out invalid month");
        assertFalse(dateValidator.isValidDate("2024-13-22"));
    }
}