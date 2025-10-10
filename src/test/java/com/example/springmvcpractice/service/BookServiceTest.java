package com.example.springmvcpractice.service;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class BookServiceTest {

    @Test
    @DisplayName("@Service가 적용되었다")
    void serviceAnnotationPresent() {
        assertTrue(
                BookService.class.
                        isAnnotationPresent(org.springframework.stereotype.Service.class)
                , "@Service가 어노테이션을 붙여야 합니다."
        );
    }
}
