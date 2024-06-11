package org.example.springserverlocal;
import com.querydsl.core.annotations.QueryType;
import com.querydsl.core.types.Predicate;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.core.types.dsl.NumberExpression;
import lombok.RequiredArgsConstructor;
import org.example.springserverlocal.BirdsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.data.querydsl.binding.QuerydslPredicate;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.awt.print.Pageable;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static javax.management.Query.and;


@RestController
public class DB_controller {
    private final BirdService birdService;
    private final BirdsRepository birdsRepository;
    @Autowired
    public DB_controller(BirdService birdService, BirdsRepository birdsRepository) {
        this.birdService = birdService;
        this.birdsRepository = birdsRepository;
    }

    @PostMapping(value = "/birds")
    public ResponseEntity<?> create(@RequestBody Bird bird) {
        birdService.create(bird);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }
    @GetMapping(value = "/birds")
    public ResponseEntity getFiltered(
            @QuerydslPredicate(root = Bird.class, bindings = BirdsRepository.class) Predicate predicate) {
        // time.sleep(10)
        return ResponseEntity.ok(birdsRepository.findAll (predicate));
    }

//    @GetMapping(value = "/birds")
//    public ResponseEntity<List<Bird>> read() {
//        final List<Bird> birds = birdService.readAll();
//
//        return birds != null &&  !birds.isEmpty()
//                ? new ResponseEntity<>(birds, HttpStatus.OK)
//                : new ResponseEntity<>(HttpStatus.NOT_FOUND);
//    }
    @GetMapping(value = "/birds/{id}")
    public ResponseEntity<Bird> read(@PathVariable(name = "id") int id) {
        final Bird bird = birdService.read(id);

        return bird != null
                ? new ResponseEntity<>(bird, HttpStatus.OK)
                : new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
    @PutMapping(value = "/birds/{id}")
    public ResponseEntity<?> update(@PathVariable(name = "id") int id, @RequestBody Bird bird) {
        final boolean updated = birdService.update(bird, id);

        return updated
                ? new ResponseEntity<>(HttpStatus.OK)
                : new ResponseEntity<>(HttpStatus.NOT_MODIFIED);
    }

    @DeleteMapping(value = "/birds/{id}")
    public ResponseEntity<?> delete(@PathVariable(name = "id") int id) {
        final boolean deleted = birdService.delete(id);

        return deleted
                ? new ResponseEntity<>(HttpStatus.OK)
                : new ResponseEntity<>(HttpStatus.NOT_MODIFIED);
    }
}

