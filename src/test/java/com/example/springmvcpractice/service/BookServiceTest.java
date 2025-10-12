package com.example.springmvcpractice.service;

import com.example.springmvcpractice.dto.BookDto;
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

    @Test
    @DisplayName("BookService.createBook 메소드가 존재한다")
    void createBookMethodPresent() {
        // given
        BookRepository mockRepo = mock(BookRepository.class);
        BookService bookService = new BookService(mockRepo);

        BookDto inputDto = new BookDto(null, "테스트도서 123", "테스트작가1", "테스트설명입니다.", "T934134451");
        when(mockRepo.save(inputDto)).thenReturn(inputDto);

        // when : 서비스 메소드 실제 호출
        bookService.createBook(inputDto);

        // then : createBook 메소드가 존재한다면, mockRepo.save를 한 번 호출함
        verify(mockRepo).save(inputDto);
        verifyNoMoreInteractions(mockRepo);
    }

    @Test
    @DisplayName("서비스를 통해 도서를 생성하면 리포지토리가 ID 를 부여한다")
    void createBookReturnsBookWithValidId() {
        BookRepository mockRepo = mock(BookRepository.class);
        BookService service = new BookService(mockRepo);

        BookDto input = new BookDto(null, "제목", "저자", "설명", "isbn");
        BookDto saved = new BookDto(4L, "제목", "저자", "설명", "isbn"); // 리포지토리가 반환해줄 결과

        when(mockRepo.save(input)).thenReturn(saved);

        BookDto result = service.createBook(input);

        assertNotNull(result.getId(), "서비스는 ID가 채워진 결과를 반환해야 합니다.");
        assertEquals(4L, result.getId());
        verify(mockRepo).save(input);
        verifyNoMoreInteractions(mockRepo);
    }
}
