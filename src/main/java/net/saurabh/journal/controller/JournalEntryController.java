package net.saurabh.journal.controller;

import java.time.LocalDateTime;
import java.util.*;

import net.saurabh.journal.entity.JournalEntry;
import net.saurabh.journal.services.JournalEntryService;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/journal")
public class JournalEntryController {

    @Autowired
    private JournalEntryService journalEntryService;

    @PostMapping
     public boolean createEntry( @RequestBody JournalEntry myEntry){
        myEntry.setDate(LocalDateTime.now());
         journalEntryService.saveEntry(myEntry);
        return true;
     }

     @GetMapping
     public List<JournalEntry> getAllJournalEntry(){
           return journalEntryService.getAll();
     }

     @GetMapping("id/{myId}")
     public JournalEntry getJournalEntryById(@PathVariable ObjectId myId){
        return journalEntryService.getById(myId).orElse(null);
     }


     @DeleteMapping
     public boolean deleteJouranalEntryById(@PathVariable ObjectId myId){
        journalEntryService.getById(myId);
        return  true ;

     }

     @PutMapping("/id/{id}")
     public void updateJournalEntryById(@PathVariable ObjectId id, @RequestBody JournalEntry myEntry){
        JournalEntry old= journalEntryService.getById(id).orElse(null);
        if(old!=null){
            old.setContent(myEntry.getContent()!=null && !myEntry.equals("")?myEntry.getContent():old.getContent());
            old.setTitle(myEntry.getTitle()!=null && !myEntry.equals("")?myEntry.getTitle():old.getTitle());
        }
        journalEntryService.saveEntry(old);
     }


}
