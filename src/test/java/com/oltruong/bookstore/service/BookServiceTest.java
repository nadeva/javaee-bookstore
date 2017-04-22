package com.oltruong.bookstore.service;

import com.oltruong.bookstore.TestUtils;
import com.oltruong.bookstore.model.Book;

import org.apache.commons.lang3.RandomStringUtils;
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

    private Long id;
    private Book book;


    @Before
    public void prepare() {
        MockitoAnnotations.initMocks(this);
        bookService = new BookService();
        TestUtils.setPrivateAttribute(bookService, mockEntityManager, "entityManager");

        book = createBook();
        id = Long.valueOf(RandomStringUtils.randomNumeric(12));
        when(mockEntityManager.find(eq(Book.class), isA(Long.class))).thenReturn(book);
    }

    @Test
    public void find() throws Exception {

        Book bookFound = bookService.find(id);
        assertThat(book).as("Book").isEqualTo(bookFound);
        verify(mockEntityManager).find(eq(Book.class), eq(id));

    }


    @Test
    public void delete() throws Exception {
        Book bookToDelete = createBook();
        bookToDelete.setId(id);

        bookService.delete(bookToDelete);

        verify(mockEntityManager).find(eq(Book.class), eq(id));
        verify(mockEntityManager).remove(eq(book));

    }

    private Book createBook() {
        Book book = new Book();
        book.setAuthor("William Shakespear");
        book.setName(RandomStringUtils.randomAlphabetic(12));
        return book;
    }

}