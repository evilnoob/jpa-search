package ru.evilnoob.jpasearch.service;

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
import ru.evilnoob.jpasearch.model.entity.User;
import ru.evilnoob.jpasearch.repository.UserRepository;

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
        Join<User, Role> roleJoin = root.join("roles");
        //Join<User, Department> departmentJoin = root.join("roles");
        query.select(root);
        query.distinct(true);
        query.where(cb.equal(roleJoin.get("name"), "ADMIN"));
        return em.createQuery(query).getResultList();
    }

    @Transactional
    public List<User> searchRep() {
        return userRepository.findAll(userRoleSpec());
    }

    private Specification<User> userRoleSpec() {
        return (user, cq, cb) -> {
            Join<User, Role> roleJoin = user.join("roles");
            cq.distinct(true);
            return cb.equal(roleJoin.get("name"), "ADMIN");
        };
    }

}
