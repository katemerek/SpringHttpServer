package org.example.springserverlocal;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BirdsRepository extends JpaRepository<Bird, Integer> {
}
