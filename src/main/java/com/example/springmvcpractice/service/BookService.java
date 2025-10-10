package com.example.springmvcpractice.service;

import com.example.springmvcpractice.dto.BookDto;
import com.example.springmvcpractice.repository.BookRepository;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
public class BookService {
    private final BookRepository bookRepository;

    BookService(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    // 저장소의 전체 목록을 반환 : id 오름차순 정렬
    public List<BookDto> getAllBooks() {
        List<BookDto> bookList = bookRepository.findAll();
        bookList.sort((o1, o2) -> (int) (o1.getId() - o2.getId()));
        return bookList;
    }

    // 도서를 조회 : 존재하지 않을 경우 404로 응답
    public BookDto getBookById(Long id) {
        return bookRepository.findById(id).
                orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "도서를 찾을 수 없습니다. id : " + id));
    }
}
