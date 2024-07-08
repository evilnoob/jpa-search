package ru.evilnoob.jpasearch.service;

import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Order;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.data.util.Pair;

@RequiredArgsConstructor
public class GenericSearchService<T> {

    private final Class<T> type;
    private final EntityManager em;

    public List<T> findWithoutCount() {
        var cb = em.getCriteriaBuilder();
        CriteriaQuery<T> query = cb.createQuery(type);
        Root<T> root = query.from(type);
        query.where(getWherePredicate());
        //fixme stuff
        var typedQuery = em.createQuery(query.select(root));
        return typedQuery.getResultList();
    }

    public Pair<Long, List<T>> findWithCount() {
        var cb = em.getCriteriaBuilder();
        CriteriaQuery<T> query = cb.createQuery(type);
        Root<T> root = query.from(type);
        var wherePredicate = getWherePredicate();
        query.where(wherePredicate);
        Long count = count(cb, root, wherePredicate);
        var typedQuery = em.createQuery(query.select(root));
        return Pair.of(count, typedQuery.getResultList());
    }

    private Predicate getWherePredicate() {
        //fixme
        return null;
    }

    private Long count(CriteriaBuilder cb, Root<T> root, Predicate wherePredicate) {
        CriteriaQuery<Long> countQuery = cb.createQuery(Long.class);
        countQuery.select(cb.count(root));
        countQuery.where(wherePredicate);
        return em.createQuery(countQuery).getSingleResult();
    }

    public void addSort(CriteriaQuery<T> query, CriteriaBuilder cb, List<Order> orders) {
        query.orderBy(orders.stream().map(o -> o.isAscending()
                        ? cb.asc(o.getExpression())
                        : cb.desc(o.getExpression()))
                .toList());
    }

    public void addPaging(TypedQuery<T> query, Pageable pageable) {
        int offset = (int) pageable.getOffset();
        query.setFirstResult(offset);
        query.setMaxResults(offset + pageable.getPageSize() * pageable.getPageNumber());
    }

}
