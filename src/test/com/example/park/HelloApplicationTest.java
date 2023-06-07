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
        assertTrue(dateValidator.isValidDate("2023-06-07"));
        assertTrue(dateValidator.isValidDate("2022-12-31"));
    }

    @Test
    void testInvalidDate() {
        assertFalse(dateValidator.isValidDate("2023-06-32"));
        assertFalse(dateValidator.isValidDate("2022-13-01"));
        assertFalse(dateValidator.isValidDate("abc"));
    }

    @Test
    void testBoundaryIn() {
        assertTrue(dateValidator.isValidDate("2022-01-01"));
        assertTrue(dateValidator.isValidDate("2023-06-07"));
        assertTrue(dateValidator.isValidDate("2023-12-31"));
    }

    @Test
    void testBoundaryOut() {

        assertFalse(dateValidator.isValidDate("2021-12-32"));
        assertFalse(dateValidator.isValidDate("2024-05-32"));
    }
}