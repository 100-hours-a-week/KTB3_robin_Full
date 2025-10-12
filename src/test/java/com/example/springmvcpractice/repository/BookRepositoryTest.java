package com.example.springmvcpractice.repository;

import com.example.springmvcpractice.dto.BookDto;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.HashSet;
import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

public class BookRepositoryTest {

    final BookDto testDto = new BookDto(
            null,
            "테스트제목1",
            "테스트저자1",
            "테스트 설명입니다.",
            "T123456789"
    );

    BookRepository bookRepository;

    @Test
    @DisplayName("@Repository 적용 여부 테스트")
    void repositoryAnnotationPresent() {
        assertTrue(
                BookRepository.class.
                        isAnnotationPresent(org.springframework.stereotype.Repository.class)
                , "@Repository 어노테이션을 붙여야 합니다."
        );
    }

    @Test
    @DisplayName("초기 데이터 1번만 추가되는지 테스트")
    void initDataOnceTest() {
        // 준비
        bookRepository = new BookRepository();
        Map<Long, BookDto> bookMap = bookRepository.bookMap;

        // 초기화 단계에서 3개의 데이터만 들어갔는지 검증
        assertEquals(3, bookMap.size(), "초기화 단계에서는 3개의 데이터만 들어가야 합니다.");

        // 같은 데이터 중복 입력 검증
        HashSet<String> isbnSet = new HashSet<>();
        for (BookDto dto : bookMap.values()) {
            isbnSet.add(dto.getIsbn());
        }
        assertEquals(bookMap.size(), isbnSet.size(), "같은 데이터가 여러번 들어가서는 안됩니다.");
    }

    @Test
    @DisplayName("save 메소드 정상 동작 테스트")
    void saveTest() {
        // 준비
        bookRepository = new BookRepository();
        Map<Long, BookDto> bookMap = bookRepository.bookMap;
        int mapSize = bookMap.size();

        // 추가
        BookDto saved = bookRepository.save(testDto);

        // 요소 하나가 정상적으로 들어갔는지 검증
        assertEquals(mapSize + 1, bookMap.size(), "새로운 요소를 저장하면 Map 이 저장하는 엔트리 개수가 하나 추가되어야 합니다.");
        // 생성된 도서에 id 가 부여됐는지 검증
        assertNotNull(saved.getId());
        // 생성된 도서가 save 메소드에 넘긴 dto 와 동일한 dto 인지 검증
        assertSame(testDto, saved);
    }

    @Test
    @DisplayName("findById 메소드 정상 동작 테스트")
    void findByIdTest() {
        // 준비
        bookRepository = new BookRepository();
        Map<Long, BookDto> bookMap = bookRepository.bookMap;
        Long expectedId = (long) (bookMap.size() + 1);

        // 추가
        bookRepository.save(testDto);

        // 비교
        assertEquals(testDto, bookRepository.findById(expectedId).get(), "새로 저장한 요소의 key 는 지금까지 저장한 요소의 개수와 동일해야 합니다.");
    }

    @Test
    @DisplayName("findAll 메소드 정상 동작 테스트 (순서유지)")
    void findAllTest() {
        // 준비
        bookRepository = new BookRepository();
        Map<Long, BookDto> bookMap = bookRepository.bookMap;

        // 초기 3개 + 1개 추가
        BookDto saved = bookRepository.save(testDto);
        List<BookDto> all = bookRepository.findAll();

        // 개수 검증
        assertEquals(4, all.size(), "총 4개가 반환되어야 합니다.");

        // 방금 들어간 원소가 마지막에 들어갔는지 검증
        assertSame(saved, all.getLast(), "새로 저장한 요소가 마지막에 위치해야 합니다.");

        // 저장된 순서 검증 (LinkedHashMap.values() 활용)
        List<BookDto> expected = List.copyOf(bookMap.values());
        assertEquals(expected, all, "findAll 의 반환 순서는 삽입 순서와 같아야 합니다.");
    }
}
