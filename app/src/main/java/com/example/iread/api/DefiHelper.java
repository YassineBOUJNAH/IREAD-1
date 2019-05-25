package com.example.iread.api;

import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.FirebaseFirestore;

public class DefiHelper {

    private static final String COLLECTION_NAME = "Defi";

    // --- COLLECTION REFERENCE ---

    public static CollectionReference getDefiCollection(){
        return FirebaseFirestore.getInstance().collection(COLLECTION_NAME);
    }
    public static Task<Void> deleteDefiRequest(String uid) {
        return DefiHelper.getDefiCollection().document(uid).delete();
    }

}

