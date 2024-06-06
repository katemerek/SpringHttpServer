package org.example.springserverlocal;
import org.example.springserverlocal.BirdsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
public class DB_controller {
    private final BirdService birdService;
    @Autowired
    public DB_controller(BirdService birdService) {
        this.birdService = birdService;
    }

    @PostMapping(value = "/birds")
    public ResponseEntity<?> create(@RequestBody Bird bird) {
        birdService.create(bird);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }
    @GetMapping(value = "/birds")
    public ResponseEntity<List<Bird>> read() {
        final List<Bird> birds = birdService.readAll();

        return birds != null &&  !birds.isEmpty()
                ? new ResponseEntity<>(birds, HttpStatus.OK)
                : new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
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
//@RequestMapping("/ptichki")//УРЛ должен быть таким чтоб исполнялась программа
//public class DB_controller {
//    private final String sharedKey = "SHARED_KEY";
//    private static final String SUCCESS_STATUS = "success";
//    private static final String ERROR_STATUS = "error";
//    private static final int CODE_SUCCESS = 100;
//    private static final int AUTH_FAILURE = 102;
//
//@GetMapping
//public BaseResponse showStatus() {
//    return new BaseResponse(SUCCESS_STATUS, 1);
//}
//    @PostMapping("/addbird")
//    public BaseResponse addbird(@RequestParam(value = "key") String key, @RequestBody Bird request) {
//
//        final BaseResponse response;
//
//        if (sharedKey.equalsIgnoreCase(key)) {
//            int id = request.getId();
//            String name = request.getName();
//            String tree = request.getTree();
//            int population = request.getPopulation();
//            // Process the request
//            // ....
//            // Return success response to the client.
//            response = new BaseResponse(SUCCESS_STATUS, CODE_SUCCESS);
//        } else {
//            response = new BaseResponse(ERROR_STATUS, AUTH_FAILURE);
//        }
//        return response;
//    }
