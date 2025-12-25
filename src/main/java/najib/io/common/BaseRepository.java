package najib.io.common;

import io.quarkus.hibernate.orm.panache.PanacheRepositoryBase;
import io.quarkus.panache.common.Page;
import io.quarkus.panache.common.Sort;

import java.time.ZonedDateTime;
import java.util.*;

public abstract class BaseRepository<E extends BaseEntity> implements PanacheRepositoryBase<E, UUID> {

    protected abstract Set<String> allowedSearchQueryFields();

    /**
     *
     * TODO:
     * at query params for search purpose, they still need to put "camelCase" while at the response has been used "snake_case".
     * if i do necessary to create converter method, it will interfere performance due to loop check.
     * let's see if FrontEnd don't mind if use "camelCase".
     *
     */
    public List<E> findPaginated(
            int page,
            int size,
            String sortField,
            String sortOrder,
            Map<String, String> filters
    ) {
        Page panachePage = Page.of(Math.max(page - 1, 0), size);

        FilterQuery filterQuery = queries(filters);

        Sort sort = sortOrder.equals("DESC")
                ? Sort.by(sortField).descending()
                : Sort.by(sortField).ascending();

        return find(filterQuery.query, sort, filterQuery.params.toArray())
                .page(panachePage)
                .list();
    }

    public long countFiltered(Map<String, String> filters) {
        FilterQuery filterQuery = queries(filters);
        return count(filterQuery.query, filterQuery.params.toArray());
    }

    private FilterQuery queries(Map<String, String> filters) {
        StringBuilder query = new StringBuilder("where deletedAt is null");
        List<Object> params = new ArrayList<>();
        int index = 1;

        for (Map.Entry<String, String> entry : filters.entrySet()) {
            String field = entry.getKey();
            String value = entry.getValue();

            if (allowedSearchQueryFields().contains(field) && value != null && !value.isBlank()) {
                query.append(" and lower(").append(field).append(") like ?").append(index);
                params.add("%" + value.toLowerCase() + "%");
                index++;
            }
        }

        return new FilterQuery(query.toString(), params);
    }

    private static class FilterQuery {
        final String query;
        final List<Object> params;

        public FilterQuery(String query, List<Object> params) {
            this.query = query;
            this.params = params;
        }
    }

    public boolean softDelete(UUID id) {
        return update("deletedAt=?1 WHERE id=?2 AND deletedAt IS NULL", ZonedDateTime.now(), id) > 0;
    }
}
