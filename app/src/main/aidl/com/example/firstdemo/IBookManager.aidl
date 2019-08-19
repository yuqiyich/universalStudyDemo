// IBookManager.aidl
package com.example.firstdemo;

// Declare any non-default types here with import statements
import com.example.firstdemo.Book;
interface IBookManager {
    /**
     * Demonstrates some basic types that you can use as parameters
     * and return values in AIDL.
     */
    void basicTypes(int anInt, long aLong, boolean aBoolean, float aFloat,
            double aDouble, String aString);

             void addBook(in Book book);

                List<Book> getBookList();

}
