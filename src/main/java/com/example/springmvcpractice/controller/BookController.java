package com.example.springmvcpractice.controller;

import com.example.springmvcpractice.dto.BookDto;
import com.example.springmvcpractice.service.BookService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/books")
public class BookController {

    private final BookService bookService;

    public BookController(BookService bookService) {
        this.bookService = bookService;
    }

    @GetMapping
    public String getBooks(Model model) {
        List<BookDto> bookList = bookService.getAllBooks();
        model.addAttribute("books", bookList);
        return "books/list";
    }

    @GetMapping("/{id}")
    public String getSingleBook(Model model, @PathVariable Long id) {
        BookDto bookDto = bookService.getBookById(id);
        model.addAttribute("book", bookDto);
        return "books/detail";
    }

    @GetMapping("/new")
    public String createBookPorm(Model model) {
        model.addAttribute("bookDto", new BookDto());
        return "books/form";
    }

    @PostMapping
    public String createBook(@ModelAttribute BookDto dto) {
        bookService.createBook(dto);
        return "redirect:/books";
    }
}
