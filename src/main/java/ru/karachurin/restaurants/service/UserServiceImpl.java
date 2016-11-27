package ru.karachurin.restaurants.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;
import ru.karachurin.restaurants.model.Restaurant;
import ru.karachurin.restaurants.model.User;
import ru.karachurin.restaurants.model.Vote;
import ru.karachurin.restaurants.repository.RestaurantRepository;
import ru.karachurin.restaurants.repository.UserRepository;
import ru.karachurin.restaurants.repository.VoteRepository;
import ru.karachurin.restaurants.util.exceptions.ExceptionUtil;
import ru.karachurin.restaurants.util.exceptions.NotFoundException;
import ru.karachurin.restaurants.util.exceptions.SameDayVoteException;

import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;

/**
 * Created by Денис on 24.11.2016.
 */
@Service
@Transactional
public class UserServiceImpl implements UserService {
    @Autowired
    UserRepository userRepository;

    @Autowired
    VoteRepository voteRepository;

    @Autowired
    RestaurantRepository restaurantRepository;

    @Override
    public User save(User user) {
        Assert.notNull(user, "user must not be null");
        return userRepository.save(user);
    }

    @Override
    public void delete(int id) throws NotFoundException {
        ExceptionUtil.checkNotFoundWithId(userRepository.delete(id)!=0, id);
    }

    @Override
    public User get(int id) throws NotFoundException {
        return ExceptionUtil.checkNotFoundWithId(userRepository.findOne(id), id);
    }

    @Override
    public User getByUsername(String username) throws NotFoundException {
        Assert.notNull(username, "Username must not be null");
        return ExceptionUtil.checkNotFound(userRepository.findByName(username), "username=" + username);
    }

    @Override
    public void update(User user) {
        Assert.notNull(user, "user must not be null");
        User updatedUser = get(user.getId());
        userRepository.save(updatedUser);
    }

    @Override
    public List<User> getAll() {
        return (List<User>) userRepository.findAll();
    }

    @Override
    public void enable(int id, boolean enabled) {
        User user = get(id);
        user.setEnabled(enabled);
        userRepository.save(user);
    }

    @Override
    public void doVote(int userId, int restaurantId, LocalDateTime date) {
        User user = get(userId);
        Restaurant restaurant = restaurantRepository.findOne(restaurantId);

        if (canVote(user, date)){
            voteRepository.save(new Vote(null, date.toLocalDate(), user, restaurant));
        }
        else {
            throw new SameDayVoteException("User with id: "+user.getId()+" already voted today");
        }
    }

    private boolean canVote(User user, LocalDateTime voteDate) {

        if (voteRepository.getByUserIdAndDate(user.getId(), voteDate.toLocalDate())==null){
            return true;
        }
        else if (voteDate.toLocalTime().isBefore(LocalTime.of(11,0))){
            voteRepository.deleteFromUserOnDate(user.getId(), voteDate.toLocalDate());
            return true;
        }
        else return false;
    }

}
