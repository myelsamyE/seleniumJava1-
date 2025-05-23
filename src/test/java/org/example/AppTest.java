package org.example;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertTrue;

class AppTest {

    @Test
    void sanity() {
        assertTrue(2 + 2 == 4, "Basic arithmetic still works");
    }
}
