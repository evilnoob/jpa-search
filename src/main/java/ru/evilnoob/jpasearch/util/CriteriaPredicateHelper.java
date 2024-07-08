package ru.evilnoob.jpasearch.util;

import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaBuilder.In;
import jakarta.persistence.criteria.Expression;
import jakarta.persistence.criteria.Predicate;
import java.util.Collection;

public class CriteriaPredicateHelper {

    public static Predicate equalPredicate(CriteriaBuilder cb, Expression<?> expression, Object value) {
        return cb.equal(expression, value);
    }

    public static Predicate notEqualPredicate(CriteriaBuilder cb, Expression<?> expression, Object value) {
        return cb.notEqual(expression, value);
    }

    public static <T> Predicate inPredicate(CriteriaBuilder cb, Expression<T> expression, Collection<T> values) {
        In<T> in = cb.in(expression);
        values.forEach(in::value);
        return in;
    }

    public static Predicate likePredicate(CriteriaBuilder cb, Expression<String> expression, String pattern) {
        return cb.like(expression, pattern);
    }

    public static Predicate notLikePredicate(CriteriaBuilder cb, Expression<String> expression, String pattern) {
        return cb.like(expression, pattern);
    }

    public static Predicate isTruePredicate(CriteriaBuilder cb, Expression<Boolean> expression) {
        return cb.isTrue(expression);
    }

    public static Predicate isFalsePredicate(CriteriaBuilder cb, Expression<Boolean> expression) {
        return cb.isFalse(expression);
    }

    public static Predicate isNullPredicate(CriteriaBuilder cb, Expression<?> expression) {
        return cb.isNull(expression);
    }

    public static Predicate isNotNullPredicate(CriteriaBuilder cb, Expression<?> expression) {
        return cb.isNotNull(expression);
    }

    public static <Y extends Comparable<? super Y>> Predicate isGreaterThanOrEqualPredicate(
            CriteriaBuilder cb,
            Expression<? extends Y> expression,
            Y value) {
        return cb.greaterThanOrEqualTo(expression, value);
    }

    public static <Y extends Comparable<? super Y>> Predicate isLessThanOrEqualPredicate(
            CriteriaBuilder cb,
            Expression<? extends Y> expression,
            Y value) {
        return cb.lessThanOrEqualTo(expression, value);
    }

    public static <Y extends Comparable<? super Y>> Predicate isGreaterThanPredicate(CriteriaBuilder cb,
                                                                                     Expression<? extends Y> expression,
                                                                                     Y value) {
        return cb.greaterThan(expression, value);
    }

    public static <Y extends Comparable<? super Y>> Predicate isLessThanPredicate(CriteriaBuilder cb,
                                                                                  Expression<? extends Y> expression,
                                                                                  Y value) {
        return cb.lessThan(expression, value);
    }

    public static <Y extends Comparable<? super Y>> Predicate isBetweenPredicate(CriteriaBuilder cb,
                                                                                 Expression<? extends Y> expression,
                                                                                 Y leftValue, Y rightValue) {
        return cb.between(expression, leftValue, rightValue);
    }

    public static <C extends Collection<?>> Predicate isEmptyPredicate(CriteriaBuilder cb,
                                                                       Expression<C> collectionExpression) {
        return cb.isEmpty(collectionExpression);
    }

    public static <C extends Collection<?>> Predicate isNotEmptyPredicate(CriteriaBuilder cb,
                                                                          Expression<C> collectionExpression) {
        return cb.isNotEmpty(collectionExpression);
    }

}
