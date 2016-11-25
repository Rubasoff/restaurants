package ru.karachurin.restaurants.repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import ru.karachurin.restaurants.model.Vote;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

/**
 * Created by Денис on 15.11.2016.
 */
public interface VoteRepository extends CrudRepository<Vote, Integer>{
    List<Vote> getAllByRestaurantIdAndDate(int restaurantId, LocalDate date);

    Vote getByUserIdAndDate(int userId, LocalDate date);

    @Query("DELETE FROM Vote v WHERE v.user.id=:id AND v.date=:voteDate")
    void deleteFromUserOnDate(@Param("id")Integer id, @Param("voteDate") LocalDateTime voteDate);
}
