package com.group.libraryapp.controller.book;

import com.group.libraryapp.dto.Book.request.BookCreateRequest;
import com.group.libraryapp.dto.Book.request.BookLoanRequest;
import com.group.libraryapp.dto.Book.request.BookReturnRequest;
import com.group.libraryapp.service.book.BookService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/book")
public class BookController {
    private final BookService bookService;

    public BookController(BookService bookService) {
        this.bookService = bookService;
    }

    @PostMapping
    public void saveBook(@RequestBody BookCreateRequest request) {
        bookService.saveBook(request);
    }

    @PostMapping("/loan")
    public void loanBook(@RequestBody BookLoanRequest request) {
        bookService.loanBook(request);
    }

    @PutMapping ("/return")
    private void returnBook(@RequestBody BookReturnRequest request) {
        bookService.returnBook(request);
    }
}
