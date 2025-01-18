package net.saurabh.journal.services;


import net.saurabh.journal.entity.JournalEntry;
import net.saurabh.journal.entity.User;
import net.saurabh.journal.repository.JournalEntryRepo;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Component
public class JournalEntryService  {

    @Autowired
    private JournalEntryRepo journalEntryRepo;

    @Autowired
    private UserService userService;

    @Transactional
    public void saveEntry(JournalEntry journalEntry, String userName){
        User user = userService.findByUserName(userName);
        journalEntry.setDate(LocalDateTime.now());
        JournalEntry saved=journalEntryRepo.save(journalEntry);
        user.getJournalEntryList().add(saved);
        userService.saveUser(user);

    }

    public void saveEntry(JournalEntry journalEntry){
       journalEntryRepo.save(journalEntry);

    }

    public List<JournalEntry> getAll() {
        return  journalEntryRepo.findAll();

    }

    public Optional<JournalEntry> getById(ObjectId id) {
        return   journalEntryRepo.findById(id);
    }

    public void deleteByID(ObjectId id, String userName){
        User user = userService.findByUserName(userName);
        user.getJournalEntryList().removeIf(x->x.getId().equals(id));
        userService.saveUser(user);
        journalEntryRepo.deleteById(id);

    }


}
