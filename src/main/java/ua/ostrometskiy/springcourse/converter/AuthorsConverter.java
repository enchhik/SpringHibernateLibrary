package ua.ostrometskiy.springcourse.converter;

import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;
import ua.ostrometskiy.springcourse.models.Authors;

import java.util.ArrayList;
import java.util.List;

@Component
public class AuthorsConverter implements Converter<String, List<Authors>> {

    @Override
    public List<Authors> convert(String source) {
        List<Authors> authorsList = new ArrayList<>();
        if (source != null && !source.isEmpty()) {
            String[] authorsIds = source.split(",");
            for (String authorsId : authorsIds) {
                Authors authors = new Authors();
                authors.setId(Integer.parseInt(authorsId));
                authorsList.add(authors);
            }
        }
        return authorsList;
    }
}
