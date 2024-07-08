package ru.evilnoob.jpasearch.util;

import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.Join;
import jakarta.persistence.criteria.Predicate;
import java.util.List;
import java.util.function.BiFunction;
import org.springframework.data.jpa.domain.Specification;

public class SpecificationHelper {

    public static <R, B> Specification<R> joinSpecification(
            boolean isDistinct, boolean isFetchJoin, String joinAttribute,
            BiFunction<CriteriaBuilder, Join<R, B>, Predicate> predicateBiFunction) {
        return (root, cq, cb) -> {
            cq.distinct(isDistinct);
            Join<R, B> roleJoin = isFetchJoin
                    ? (Join<R, B>) root.fetch(joinAttribute)
                    : root.join(joinAttribute);
            return predicateBiFunction.apply(cb, roleJoin);
        };
    }

    private static <R> Specification<R> andSpecification(List<Specification<R>> specs) {
        return Specification.allOf(specs);
    }

    private static <R> Specification<R> orSpecification(List<Specification<R>> specs) {
        return Specification.anyOf(specs);
    }

}
