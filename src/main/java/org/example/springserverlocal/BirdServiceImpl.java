package org.example.springserverlocal;

import com.querydsl.core.types.Predicate;
import org.springframework.stereotype.Service;

import java.awt.print.Pageable;
import java.util.List;

@Service
public class BirdServiceImpl implements BirdService {

    private final BirdsRepository birdsRepository;
    public BirdServiceImpl(BirdsRepository birdsRepository) {
        this.birdsRepository = birdsRepository;
    }

    @Override
    public void create(Bird bird) {
        birdsRepository.save(bird);
    }

//    @Override
//    public List<Bird> readAll() {
//        return birdsRepository.findAll();
//    }

    @Override
    public Bird read(int id) {
        return birdsRepository.getOne(id);
    }

    @Override
    public boolean update(Bird bird, int id) {
        if (birdsRepository.existsById(id)) {
            bird.setId(id);
            birdsRepository.save(bird);
            return true;
        }

        return false;
    }

    @Override
    public boolean delete(int id) {
        if (birdsRepository.existsById(id)) {
            birdsRepository.deleteById(id);
            return true;
        }
        return false;
    }
}