package org.example.book;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.support.DefaultMessageSourceResolvable;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.List;
import java.util.Objects;
import java.util.Set;
import java.util.stream.Collectors;

@Slf4j
@Controller
@RequiredArgsConstructor
public class BookController {
    private final BookRepository repository;
    private int totalPages;
    private int currentPage;

    @GetMapping
    public String initPage(Model model) {
        Page<BookEntity> bookData = bookData(0);
        this.totalPages = bookData.getTotalPages();
        this.currentPage = bookData.getPageable().getPageNumber();

        model.addAttribute("bookList", bookData.getContent());
        model.addAttribute("currentPage", this.currentPage);
        return "index";
    }

    @GetMapping("/filter")
    public String filter(Model model, @RequestParam String param) {
        if (Objects.isNull(param) || param.isBlank()) {
            return initPage(model);
        }

        Set<BookEntity> filteredBooks = repository
                .findByNameLike("%".concat(param).concat("%"), Sort.by("addedAt").descending());
        model.addAttribute("bookList", filteredBooks);
        model.addAttribute("currentPage", 0);
        return "index";
    }

    @PostMapping("/previous")
    public String previousPage(Model model) {
        this.currentPage--;

        if (this.currentPage < 0) {
            this.currentPage = 0;
        }

        Page<BookEntity> bookData = bookData(this.currentPage);
        model.addAttribute("bookList", bookData.getContent());
        model.addAttribute("currentPage", this.currentPage);
        return "index";
    }

    @PostMapping("/next")
    public String nextPage(Model model) {
        this.currentPage++;

        if (this.currentPage > this.totalPages - 1) {
            this.currentPage = this.totalPages - 1;
        }

        Page<BookEntity> bookData = bookData(this.currentPage);
        model.addAttribute("bookList", bookData.getContent());
        model.addAttribute("currentPage", this.currentPage);
        return "index";
    }

    @PostMapping("/add")
    public String addBook(@Valid BookEntity book, BindingResult bindingResult, Model model) {
        if (bindingResult.hasErrors()) {
            String error = bindingResult.getAllErrors().stream()
                    .map(DefaultMessageSourceResolvable::getDefaultMessage)
                    .collect(Collectors.joining());

            model.addAttribute("error", error);
        } else {
            try {
                repository.save(book);
            } catch (RuntimeException e) {
                model.addAttribute("error", "Book already persisted in database!");
                return initPage(model);
            }
        }

        return initPage(model);
    }

    private Page<BookEntity> bookData(int pageNumber) {
        return repository.findAll(PageRequest.of(pageNumber, 50, Sort.by("addedAt").descending()));
    }

    @ResponseBody
    @GetMapping("/books")
    public List<BookEntity> listBookRestResponse() {
        return repository.findAll();
    }
}
