package com.example.springmvcpractice.service;

import com.example.springmvcpractice.dto.BookDto;
import com.example.springmvcpractice.repository.BookRepository;
import org.springframework.stereotype.Service;

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
}
