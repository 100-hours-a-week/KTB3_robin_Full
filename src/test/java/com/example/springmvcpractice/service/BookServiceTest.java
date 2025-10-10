package com.example.springmvcpractice.service;

import com.example.springmvcpractice.repository.BookRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

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

    @Test
    @DisplayName("생성자에 주입한 mock BookRepository가 실제로 사용된다")
    void constructorDiIsUsed() {
        // given : BookRepository를 mock으로 만들고, 생성자에 주입
        BookRepository mockRepo = mock(BookRepository.class);
        BookService bookService = new BookService(mockRepo);

        // when : 서비스 메소드 호출
        bookService.getAllBooks();

        // then : 생성자로 주입한 mock이 실제로 호출되었는지 검증
        verify(mockRepo).findAll();
        verifyNoMoreInteractions(mockRepo);
    }

    @Test
    @DisplayName("존재하지 않는 id로 페이지 요청 시 404 응답 동작 테스트")
    void notFoundIsReturnedForInvalidId() {
        // given : id 1~3 까지만 저장된 상태
        BookRepository mockRepo = mock(BookRepository.class);
        when(mockRepo.findById(4L)).thenReturn(Optional.empty());
        BookService bookService = new BookService(mockRepo);

        // when
        ResponseStatusException exception = assertThrows(ResponseStatusException.class, () -> bookService.getBookById(4L));

        // then : 404 상태 코드 검증 + 상호작용 검증
        assertEquals(HttpStatus.NOT_FOUND.value(), exception.getStatusCode().value(), "404 상태여야 합니다.");
        verify(mockRepo).findById(4L);
        verifyNoMoreInteractions(mockRepo);
    }
}
