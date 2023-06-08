package ua.ostrometskiy.springcourse.converter;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;
import ua.ostrometskiy.springcourse.dao.BooksDAO;
import ua.ostrometskiy.springcourse.models.Books;

import java.util.ArrayList;
import java.util.List;

/*@Component
public class BooksConverter implements Converter<String, List<Books>> {

*//*    @Override
    public List<Books> convert(String source) {
        List<Books> booksList = new ArrayList<>();
        if (source != null && !source.isEmpty()) {
            String[] bookIds = source.split(",");
            for (String bookId : bookIds) {
                Books books = new Books();
                books.setId(Integer.parseInt(bookId));
                booksList.add(books);
            }
        }
        return booksList;
    }*//*

*//*    @Override
    public List<Books> convert(String source) {
        List<Books> booksList = new ArrayList<>();
        String[] bookIds = source.split(",");
        if (bookIds.length == 1) {
            Books book = new Books();
            book.setId(Integer.parseInt(bookIds[0]));
            booksList.add(book);
        } else {
            for (String bookId : bookIds) {
                Books books = new Books();
                books.setId(Integer.parseInt(bookId));
                booksList.add(books);
            }
        }
        return booksList;
    }*//*

*//*    @Override
    public List<Books> convert(String[] source) {
        List<Books> booksList = new ArrayList<>();
        if (source != null && source.length > 0) {
            for (String bookId : source) {
                Books book = new Books();
                book.setId(Integer.parseInt(bookId));
                booksList.add(book);
            }
        }
        return booksList;
    }

    public List<Books> convert(String source) {
        String[] sourceArray = source.split(",");
        return convert(sourceArray);
    }*//*

    @Override
    public List<Books> convert(String source) {
        List<Books> booksList = new ArrayList<>();
        if (source != null && !source.isEmpty()) {
            if (source.contains(",")) {
                String[] bookIds = source.split(",");
                for (String bookId : bookIds) {
                    Books book = new Books();
                    book.setId(Integer.parseInt(bookId));
                    booksList.add(book);
                }
            } else {
                Books book = new Books();
                book.setId(Integer.parseInt(source));
                booksList.add(book);
            }
        }
        return booksList;
    }

}*/

/*@Component
public class BooksConverter implements Converter<String[], List<Books>> {

    @Override
    public List<Books> convert(String[] source) {
        List<Books> booksList = new ArrayList<>();
        if (source != null && source.length > 0) {
            for (String bookId : source) {
                Books book = new Books();
                book.setId(Integer.parseInt(bookId));
                booksList.add(book);
            }
        }
        return booksList;
    }
}*/
@Component
public class BooksConverter implements Converter<String, List<Books>> {

    @Override
    public List<Books> convert(String source) {
        List<Books> booksList = new ArrayList<>();
        if (source != null && !source.isEmpty()) {
            String[] bookIds = source.split(",");
            for (String bookId : bookIds) {
                int id = Integer.parseInt(bookId.trim()); // Преобразование в int
                Books book = new Books();
                book.setId(id);
                booksList.add(book);
            }
        }
        return booksList;
    }
}



