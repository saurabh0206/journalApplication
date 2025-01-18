package net.saurabh.journal.controller;

import java.time.LocalDateTime;
import java.util.*;

import net.saurabh.journal.entity.JournalEntry;
import net.saurabh.journal.entity.User;
import net.saurabh.journal.services.JournalEntryService;
import net.saurabh.journal.services.UserService;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/journal")
public class JournalEntryController {

    @Autowired
    private JournalEntryService journalEntryService;

    @Autowired
    private UserService userService;


    @PostMapping ("{userName}")
    public ResponseEntity<JournalEntry> createEntry(@RequestBody JournalEntry myEntry, @PathVariable String userName) {

        try {


            journalEntryService.saveEntry(myEntry,userName);
            return  new ResponseEntity<>(myEntry, HttpStatus.CREATED);
        } catch (Exception e) {
            return  new ResponseEntity<>( HttpStatus.BAD_REQUEST);
        }
    }



    @GetMapping("{userName}")
    public ResponseEntity<?> getAllJournalEntryOfUser(@PathVariable String userName) {
        User user = userService.findByUserName(userName);

        List<JournalEntry> all = user.getJournalEntryList();
        if(all!=null && !all.isEmpty())
            return new ResponseEntity<>(all,HttpStatus.OK);
        return  new ResponseEntity<>(HttpStatus.NOT_FOUND);


    }

    @GetMapping("id/{myId}")
    public ResponseEntity<JournalEntry> getJournalEntryById(@PathVariable ObjectId myId) {

        Optional<JournalEntry> journalEntry = journalEntryService.getById(myId);
        return journalEntry.map(entry -> new ResponseEntity<>(entry, HttpStatus.OK)).orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }



    @DeleteMapping("id/{userName}/{myId}")
    public ResponseEntity<?> deleteJouranalEntryById(@PathVariable ObjectId myId,@PathVariable String userName) {
        journalEntryService.deleteByID(myId,userName);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);

    }



    @PutMapping("/id/{userName}/{id}")
    public ResponseEntity<?> updateJournalEntryById(
            @PathVariable ObjectId id,
            @RequestBody JournalEntry myEntry,
            @PathVariable String userName
    ) {
        JournalEntry old = journalEntryService.getById(id).orElse(null);
        if (old != null) {
            old.setContent(myEntry.getContent() != null && !myEntry.equals("") ? myEntry.getContent() : old.getContent());
            old.setTitle(myEntry.getTitle() != null && !myEntry.equals("") ? myEntry.getTitle() : old.getTitle());
            journalEntryService.saveEntry(old);
            return new ResponseEntity<>(HttpStatus.OK);
        }
        return  new ResponseEntity<>(HttpStatus.NOT_FOUND);

    }


}
