package org.example.springserverlocal;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.Predicate;
import com.querydsl.core.types.dsl.NumberExpression;
import com.querydsl.core.types.dsl.NumberPath;
import com.querydsl.core.types.dsl.StringExpression;
import com.querydsl.core.types.dsl.StringPath;
import com.querydsl.jpa.impl.JPAQuery;
import jakarta.persistence.EntityManager;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;
import org.springframework.data.querydsl.binding.*;

import javax.xml.namespace.QName;
import java.util.Iterator;
import java.util.List;
import java.util.Optional;

import static org.apache.tomcat.util.http.parser.HttpParser.isNumeric;
import static org.aspectj.weaver.tools.cache.SimpleCacheFactory.path;
import static org.example.springserverlocal.QBird.bird;

public interface BirdsRepository extends JpaRepository<Bird, Integer>, QuerydslPredicateExecutor<Bird>, QuerydslBinderCustomizer<QBird> {
    @Override
    default void customize(QuerydslBindings bindings, QBird root) {
        // Make case-insensitive 'like' filter for all string properties
        bindings.bind(String.class).first((SingleValueBinding<StringPath, String>) StringExpression::containsIgnoreCase);
        bindings.bind(bird.population).all((path, values) -> {//для колонки популяция свой блок
            Iterator<? extends Integer> it = values.iterator();//перебираем и сравниваем значения
            if(values.size() == 1) {
                Integer v = it.next();
                return Optional.of(path.eq(v));
            } else {
                Integer v = it.next();
                Integer sign = it.next();
                switch (sign) {
                    case 0:
                        return Optional.of(path.eq(v));
                    case -1:
                        return Optional.of(path.lt(v));//меньше чем
                    case -2:
                        return Optional.of(path.loe(v));//меньше или равно
                    case 1:
                        return Optional.of(path.gt(v));//больше чем
                    case 2:
                        return Optional.of(path.goe(v));//больше или раавно
                }
            }
            return Optional.empty();
        });
    }

}