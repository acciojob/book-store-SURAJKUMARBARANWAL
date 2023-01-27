package com.driver;
import java.util.*;

import org.springframework.stereotype.Repository;

@Repository
public class BookRepository {
    Map<Integer,Book> dbBook=new HashMap<>();

    private static int count=1;
    public Book save(Book book){
        book.setId(count);
        count++;
        dbBook.put(book.getId(),book);
        return book;
    }

    public Book findBookById(int id){
       return dbBook.get(id);
    }

    public List<Book> findAll(){

        return new ArrayList<>(dbBook.values());
    }

    public void deleteBookById(int id){
        if(dbBook.containsKey(id)){
            dbBook.remove(id);
        }
    }

    public void deleteAll(){

        dbBook.clear();
    }

    public List<Book> findBooksByAuthor(String author){
        List<Book> list=new ArrayList<>();
        for(int key:dbBook.keySet()){
            if(dbBook.get(key).getAuthor().equals(author)){
                list.add(dbBook.get(key));
            }
        }
        return list;
    }

    public List<Book> findBooksByGenre(String genre){
        List<Book> list=new ArrayList<>();
        for(int key:dbBook.keySet()){
            if(dbBook.get(key).getGenre().equals(genre)){
                list.add(dbBook.get(key));
            }
        }
        return list;
    }
}
