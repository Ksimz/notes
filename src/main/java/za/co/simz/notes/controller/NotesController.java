package za.co.simz.notes.controller;

import com.google.cloud.firestore.QueryDocumentSnapshot;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import za.co.simz.notes.entity.NewNote;
import za.co.simz.notes.entity.Note;
import za.co.simz.notes.service.ProductService;

import java.util.List;
import java.util.concurrent.ExecutionException;

@RestController
@RequestMapping("/note")
public class NotesController {

    @Autowired
    private ProductService productService;

    @GetMapping
    public List<NewNote> getNotes() throws ExecutionException, InterruptedException {
        return productService.getNotes();
    }

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping
    public String addNotes(@RequestBody Note notes) throws ExecutionException, InterruptedException {
        return productService.addNotes(notes);
    }
}
