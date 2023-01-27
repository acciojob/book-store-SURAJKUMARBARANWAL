package com.driver;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@RestController
public class BookController {
    @Autowired
    BookService bookService;

    // One example controller, make the rest by yourself
    @PostMapping("/books/create-book")
    public ResponseEntity createBook(@RequestBody Book book){
        Book newbook = bookService.createBook(book);
        return new ResponseEntity<>(newbook, HttpStatus.CREATED);
    }
    @GetMapping("/books/get-book-by-id/{id}")
    public ResponseEntity findBookById(@PathVariable("id") String id){
       Book response=bookService.findBookById(id);
       if(Objects.equals(null,response)){
           return new ResponseEntity("Book not found",HttpStatus.NOT_FOUND);
       }
       return new ResponseEntity(response,HttpStatus.FOUND);

    }
    @DeleteMapping("/books/delete-book-by-id/{id}")
    public ResponseEntity deleteBookById(@PathVariable("id") String id){
          bookService.deleteBookById(id);
          return new ResponseEntity("Book Deleted successfully",HttpStatus.ACCEPTED);
    }
    @GetMapping("/books/get-all-books")
    public ResponseEntity findAllBooks(){
        List<Book> response=new ArrayList<>();
        response=bookService.findAllBooks();
        return  new ResponseEntity(response,HttpStatus.FOUND);
    }
    @DeleteMapping("/books/delete-all-books")
    public ResponseEntity deleteAllBooks(){
        bookService.deleteAllBooks();
        return new ResponseEntity("All books deleted successfully",HttpStatus.ACCEPTED);
     }

    @GetMapping("/books/get-books-by-author")
    public ResponseEntity findBooksByAuthor(@RequestParam("author") String author){
       List<Book> response=new ArrayList<>();
       response=bookService.findBooksByAuthor(author);
       return new ResponseEntity(response,HttpStatus.FOUND);
    }
    @GetMapping("/books/get-books-by-genre")
    public ResponseEntity findBooksByGenre(@RequestParam("genre") String genre){
        List<Book> response=new ArrayList<>();
        response=bookService.findBooksByGenre(genre);
        return new ResponseEntity(response,HttpStatus.FOUND);
    }

}
