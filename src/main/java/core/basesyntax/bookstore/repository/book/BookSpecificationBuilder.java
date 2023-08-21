package core.basesyntax.bookstore.repository.book;

import core.basesyntax.bookstore.dto.BookSearchParametersDto;
import core.basesyntax.bookstore.model.Book;
import core.basesyntax.bookstore.repository.SpecificationBuilder;
import core.basesyntax.bookstore.repository.SpecificationProviderManager;
import lombok.RequiredArgsConstructor;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Component;

@RequiredArgsConstructor
@Component
public class BookSpecificationBuilder implements
        SpecificationBuilder<Book, BookSearchParametersDto> {
    private static final String KEY_FOR_TITLE = "title";
    private static final String KEY_FOR_AUTHOR = "author";
    private static final String KEY_FOR_PRICE_FROM = "fromPrice";
    private static final String KEY_FOR_PRICE_TO = "toPrice";
    private final SpecificationProviderManager<Book> bookSpecificationProviderManager;

    @Override
    public Specification<Book> build(BookSearchParametersDto searchParametersDto) {
        Specification<Book> specification = Specification.where(null);
        if (searchParametersDto.title() != null && searchParametersDto.title().length > 0) {
            specification = specification.and(bookSpecificationProviderManager
                    .getSpecificationProvider(KEY_FOR_TITLE)
                    .getSpecification(searchParametersDto.title()));
        }
        if (searchParametersDto.author() != null && searchParametersDto.author().length > 0) {
            specification = specification.and(bookSpecificationProviderManager
                    .getSpecificationProvider(KEY_FOR_AUTHOR)
                    .getSpecification(searchParametersDto.author()));
        }
        if (searchParametersDto.fromPrice() != null) {
            specification = specification.and(bookSpecificationProviderManager
                    .getSpecificationProvider(KEY_FOR_PRICE_FROM)
                    .getSpecification(searchParametersDto.fromPrice()));
        }
        if (searchParametersDto.toPrice() != null) {
            specification = specification.and(bookSpecificationProviderManager
                    .getSpecificationProvider(KEY_FOR_PRICE_TO)
                    .getSpecification(searchParametersDto.toPrice()));
        }
        return specification;
    }
}