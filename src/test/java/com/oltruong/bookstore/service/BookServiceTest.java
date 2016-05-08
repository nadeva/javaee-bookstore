package com.oltruong.bookstore.service;

import com.oltruong.bookstore.TestUtils;
import com.oltruong.bookstore.model.Book;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import javax.persistence.EntityManager;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Matchers.eq;
import static org.mockito.Matchers.isA;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

/**
 * @author oltruong
 */
public class BookServiceTest {


    private BookService bookService;

    @Mock
    private EntityManager mockEntityManager;


    @Before
    public void prepare() {
        MockitoAnnotations.initMocks(this);
        bookService = new BookService();
        TestUtils.setPrivateAttribute(bookService, mockEntityManager, "entityManager");
    }

    @Test
    public void find() throws Exception {

        Long id = 123l;
        Book book = createBook();

        when(mockEntityManager.find(eq(Book.class), isA(Long.class))).thenReturn(book);
        Book bookFound = bookService.find(id);

        assertThat(book).as("Book").isEqualTo(bookFound);

        verify(mockEntityManager).find(eq(Book.class), eq(id));

    }

    private Book createBook() {
        Book book = new Book();
        book.setAuthor("William Shakespear");
        book.setName("Hamlet");
        return book;
    }

}