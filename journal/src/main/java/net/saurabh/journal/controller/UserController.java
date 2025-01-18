package net.saurabh.journal.controller;

import net.saurabh.journal.entity.JournalEntry;
import net.saurabh.journal.entity.User;
import net.saurabh.journal.services.JournalEntryService;
import net.saurabh.journal.services.UserService;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping
    public ResponseEntity<User> createEntry(@RequestBody User myUser) {
        try {
            userService.saveUser(myUser);
            return  new ResponseEntity<>(myUser, HttpStatus.CREATED);
        } catch (Exception e) {
            return  new ResponseEntity<>( HttpStatus.BAD_REQUEST);
        }
    }



    @GetMapping
    public ResponseEntity<?> getAllUser() {
        List<User> all = userService.getAll();
        if(all!=null && !all.isEmpty())
            return new ResponseEntity<>(all,HttpStatus.OK);
        return  new ResponseEntity<>(HttpStatus.NOT_FOUND);


    }





    @PutMapping
    public ResponseEntity<?> updateUser( @RequestBody User user) {

        User userInDB=  userService.findByUserName(user.getUserName());
        if (userInDB!= null) {
            userInDB.setUserName(user.getUserName() );
            userInDB.setPassword(user.getPassword());
            userService.saveUser(userInDB);
            return new ResponseEntity<>(HttpStatus.OK);
        }
        return  new ResponseEntity<>(HttpStatus.NOT_FOUND);

    }

//
//    @GetMapping("id/{myId}")
//    public ResponseEntity<JournalEntry> getJournalEntryById(@PathVariable ObjectId myId) {
//
//        Optional<JournalEntry> journalEntry = journalEntryService.getById(myId);
//        return journalEntry.map(entry -> new ResponseEntity<>(entry, HttpStatus.OK)).orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
//    }
//
//
//
//    @DeleteMapping
//    public ResponseEntity<?> deleteJouranalEntryById(@PathVariable ObjectId myId) {
//        journalEntryService.getById(myId);
//            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
//
//    }
//
//


}
