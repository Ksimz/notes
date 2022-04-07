package za.co.simz.notes.service;

import com.google.api.core.ApiFuture;
import com.google.cloud.firestore.Firestore;
import com.google.cloud.firestore.QueryDocumentSnapshot;
import com.google.cloud.firestore.QuerySnapshot;
import com.google.cloud.firestore.WriteResult;
import com.google.firebase.cloud.FirestoreClient;
import org.springframework.stereotype.Service;
import za.co.simz.notes.entity.NewNote;
import za.co.simz.notes.entity.Note;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.stream.Collectors;


@Service
public class ProductService {


    public String addNotes(Note myNote) throws ExecutionException, InterruptedException {

        Firestore db = FirestoreClient.getFirestore();
        ApiFuture<WriteResult> futureResponse=db.collection("notes").document(myNote.getTitle()).set(myNote);
        return futureResponse.get().getUpdateTime().toString();

    }

    public List<NewNote> getNotes() throws ExecutionException, InterruptedException {
        //TODO: convert to stream
        Firestore db = FirestoreClient.getFirestore();
        ApiFuture<QuerySnapshot> futureResponse = db.collection("notes").get();
        List<QueryDocumentSnapshot> documents = futureResponse.get().getDocuments();
        return documents.stream().map(document->document.toObject(NewNote.class)).collect(Collectors.toList());
    }
}
