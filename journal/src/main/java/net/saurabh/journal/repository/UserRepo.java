package net.saurabh.journal.repository;

import net.saurabh.journal.entity.JournalEntry;
import net.saurabh.journal.entity.User;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface UserRepo extends MongoRepository<User, ObjectId> {

     User findByUserName(String userName);
}
