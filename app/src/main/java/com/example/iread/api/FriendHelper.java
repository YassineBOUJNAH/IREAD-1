package com.example.iread.api;

import com.example.iread.model.FriendRequest;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;

public class FriendHelper {

    private static final String COLLECTION_NAME = "friendRequests";

    // --- COLLECTION REFERENCE ---

    public static CollectionReference getFriendsCollection(){
        return FirebaseFirestore.getInstance().collection(COLLECTION_NAME);
    }

    // --- CREATE ---

    public static Task<Void> createFriendRequest(String uidSender, String uidRecever) {
        FriendRequest friendRequest = new FriendRequest(uidSender, uidRecever);
        return FriendHelper.getFriendsCollection().document().set(friendRequest);
    }

    // --- GET ---

    public static Task<DocumentSnapshot> getFriendRequest(String uid){
        return FriendHelper.getFriendsCollection().document(uid).get();
    }

    // --- DELETE ---

    public static Task<Void> deleteFriendRequest(String uid) {
        return FriendHelper.getFriendsCollection().document(uid).delete();
    }


}
