package ru.practicum.shareit.user.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;
import ru.practicum.shareit.services.Exceptions.NoFoundException;
import ru.practicum.shareit.user.dto.UserDto;
import ru.practicum.shareit.user.dto.UserDtoCreate;
import ru.practicum.shareit.user.dto.UserDtoPatch;
import ru.practicum.shareit.user.dto.UserMapper;
import ru.practicum.shareit.user.model.User;
import ru.practicum.shareit.user.storage.UserRepository;

import java.util.*;

@Service
@Primary
public class UserServiceDBImpl implements UserService {
    private final UserRepository repository;

    private final String eMessage = "Пользователь не найден! ";

    @Autowired
    public UserServiceDBImpl(UserRepository repository) {
        this.repository = repository;
    }

    public void checkUsers() {

    }

    @Override
    public UserDto create(UserDtoCreate user) {
        UserMapper.fromUserDtoCreate(user);
        User createdUser = repository.save(UserMapper.fromUserDtoCreate(user));
        return UserMapper.toUserDto(createdUser);
    }

    @Override
    public UserDto get(Long id) {
            Optional<User> user = repository.findById(id);
            if(user.isPresent()){
                return UserMapper.toUserDto(user.get());
            }
            else throw new NoFoundException(eMessage);
    }

    @Override
    public UserDto patch(Long id, UserDtoPatch user) {
        User userFromDB =repository.findById(id).get();
        User updateUser = UserMapper.fromUserDtoPatch(user);
        if (updateUser.getName() != null) {
            userFromDB.setName(updateUser.getName());
        }
        if (updateUser.getEmail() != null) {
            userFromDB.setEmail(updateUser.getEmail());
        }
       return UserMapper.toUserDto(repository.save(userFromDB));
    }

    @Override
    public UserDto delete(Long id) {
       repository.deleteById(id);
       return null;
    }

    @Override
    public Collection<UserDto> getAll() {
      List<User> list = repository.findAll();
      List<UserDto> newList = new LinkedList<>();
      list.forEach(user -> newList.add(UserMapper.toUserDto(user)));
       return newList;
    }
}
