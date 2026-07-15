package vn.spring.personal_finance.dto.response;

import lombok.Getter;
import lombok.Setter;
import org.springframework.data.domain.Page;

import java.util.List;

@Getter
@Setter
public class PaginationResponse<T> {
    private Pagination pagination;
    private T meta;

    @Getter
    @Setter
    public static class Pagination {
        private int page;
        private int size;
        private long total;
        private int total_pages;
    }
    public static <T> PaginationResponse<List<T>> setPaginate(Page<T> page) {

        PaginationResponse<List<T>> response = new PaginationResponse<>();

        Pagination p = new Pagination();
        p.setPage(page.getNumber() +1);
        p.setSize(page.getSize());
        p.setTotal(page.getTotalElements());
        p.setTotal_pages(page.getTotalPages());

        response.setPagination(p);
        response.setMeta(page.getContent());

        return response;
    }
}
