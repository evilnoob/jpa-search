package ru.evilnoob.jpasearch.service;

import static ru.evilnoob.jpasearch.util.CriteriaPredicateHelper.equalPredicate;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Join;
import jakarta.persistence.criteria.Root;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.evilnoob.jpasearch.model.entity.Role;
import ru.evilnoob.jpasearch.model.entity.Role_;
import ru.evilnoob.jpasearch.model.entity.User;
import ru.evilnoob.jpasearch.model.entity.User_;
import ru.evilnoob.jpasearch.repository.UserRepository;
import ru.evilnoob.jpasearch.util.SpecificationHelper;

@Service
@RequiredArgsConstructor
public class CriteriaBuildingServiceImpl {

    @PersistenceContext
    private final EntityManager em;
    private final UserRepository userRepository;

    @Transactional
    public List<User> searchEm() {
        var cb = em.getCriteriaBuilder();
        CriteriaQuery<User> query = cb.createQuery(User.class);
        Root<User> root = query.from(User.class);
        cb.equal(root.get(User_.NAME), "Alex");
        //Join<User, Role> roleJoin = root.join(User_.ROLES);
        Join<User, Role> roleJoin = root.join("roles");
        //Join<User, Department> departmentJoin = root.join("roles");
        query.select(root);
        query.distinct(true);
        query.where(cb.equal(roleJoin.get("name"), "ADMIN"));
        query.orderBy(cb.asc(root.get("name")));
        var typedQuery = em.createQuery(query.select(root));
        //return em.createQuery(query.multiselect(root)).getResultList();
        return typedQuery.getResultList();
    }

    /*@Transactional
    public List<User> searchEm2() {
        var cb = em.getCriteriaBuilder();
        CriteriaQuery<User> query = cb.createQuery(User.class);
        Root<User> rootLeadActivity = query.from(User.class);
        Join<User, Role> joinLead = rootLeadActivity.join(User_.ROLES, JoinType.INNER);
        List<Predicate> predicatesList = new ArrayList<>();

        Subquery<Long> sqMaxId = query.subquery(Long.class);
        Root<User> sqRootActivity = sqMaxId.from(User.class);
        Join<User, Role> sqJoinLead = sqRootActivity.join(User_.ROLES, JoinType.INNER);
        sqMaxId.where(cb.equal(sqJoinLead.get(Role_.NAME), joinLead.get(Role_.NAME)));
        sqMaxId.select(cb.);

        predicatesList.add(cb.equal(rootLeadActivity.get(LeadActivity_.leadActivityId), sqMaxId.getSelection()));

        cq.where(predicatesList.toArray(new Predicate[predicatesList.size()]));
        cq.multiselect(rootLeadActivity);
    }
*/
    @Transactional
    public List<User> searchRep() {
        //PageRequest.of(0, 10, Sort.by("roles").ascending());
        return userRepository.findAll(userRoleFetchSpec());
    }

    private Specification<User> userRoleFetchSpec() {
        return SpecificationHelper.joinSpecification(true, true, User_.ROLES,
                (cb, roleJoin) -> equalPredicate(cb, roleJoin.get(Role_.NAME), "ADMIN"));
    }

}
