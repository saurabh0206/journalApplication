package net.saurabh.journal.services;


import net.saurabh.journal.entity.JournalEntry;
import net.saurabh.journal.repository.JournalEntryRepo;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
public class JournalEntryService  {

    @Autowired
    public JournalEntryRepo journalEntryRepo;

    public void saveEntry(JournalEntry journalEntry){
        journalEntryRepo.save(journalEntry);
    }

    public List<JournalEntry> getAll() {
        return  journalEntryRepo.findAll();

    }

    public Optional<JournalEntry> getById(ObjectId id) {
        return   journalEntryRepo.findById(id);
    }

    public void deleteByID(ObjectId id){
        journalEntryRepo.deleteById(id);
    }


}
