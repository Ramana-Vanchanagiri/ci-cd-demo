package com.example;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

class AppTest {
    @Test
    void greetReturnsExpectedMessage() {
        assertEquals(
            "Hello from our CI/CD pipeline!",
            App.greet()
        );
    }

    @Test
    void getBuildStatusReturnsReadyMessage() {
        assertEquals("Application is ready!", App.getBuildStatus());
    }
}
