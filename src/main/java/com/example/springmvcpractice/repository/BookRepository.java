package com.example.springmvcpractice.repository;

import com.example.springmvcpractice.dto.BookDto;
import org.springframework.stereotype.Repository;

import java.util.LinkedHashMap;
import java.util.Map;

@Repository
public class BookRepository {
    Map<Long, BookDto> bookMap = new LinkedHashMap<>();
    long sequence = 0;

    public BookRepository() {
        save(new BookDto(sequence++, "Clean Code", "Robert C. Martin", "소프트웨어 장인 정신을 담은 책입니다.", "9780132350884"));
        save(new BookDto(sequence++, "객체지향의 사실과 오해", "조영호", "객체지향의 본질을 쉽게 설명합니다.", "9791186710770"));
        save(new BookDto(sequence++, "Effective Java", "Joshua Bloch", "자바 개발자를 위한 베스트 프랙티스 모음집입니다.", "9780134685991"));
    }

    BookDto save(BookDto book) {
        Long bookId = book.getId();
        bookMap.put(bookId, book);
        return bookMap.get(bookId);
    }
}
