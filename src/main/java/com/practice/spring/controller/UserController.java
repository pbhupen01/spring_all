package com.practice.spring.controller;

import com.practice.spring.dto.*;
import com.practice.spring.dto.diary.DiaryRequest;
import com.practice.spring.dto.diary.DiaryResponse;
import com.practice.spring.dto.user.UserRequest;
import com.practice.spring.dto.user.UserResponse;
import com.practice.spring.exception.DiaryAlreadyExistsException;
import com.practice.spring.exception.DiaryNotFoundException;
import com.practice.spring.model.Diary;
import com.practice.spring.model.DiaryID;
import com.practice.spring.model.User;
import com.practice.spring.exception.UserAlreadyExistsException;
import com.practice.spring.exception.UserNotFoundException;
import com.practice.spring.service.DiaryService;
import com.practice.spring.service.UserService;
import com.practice.spring.util.SpringAllUtils;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.Date;
import java.util.stream.Collectors;

@RestController
@RequestMapping(value = SpringAllUtils.USERS)
@Transactional(rollbackFor = Exception.class)
@Slf4j
public class UserController {

    UserService userService;
    DiaryService diaryService;

    @Autowired
    public UserController(UserService userService, DiaryService diaryService)
    {
        this.userService = userService;
        this.diaryService = diaryService;
    }

    @ApiOperation(
            value = "Get user",
            notes = "Returns Json Data with the User details"
    )
    @GetMapping( value = "/{userId}")
    public ResponseEntity<UserResponse> getUser(@PathVariable String userId) throws UserNotFoundException
    {
        log.debug("Got search request for user : " + userId);
        User userDAO = userService.searchUserByUserId(userId);
        log.debug("Returning found user : " + userId);
        UserResponse response = convertUserModelToUserResponse(userDAO);
        return new ResponseEntity<UserResponse>(response, HttpStatus.OK);
    }


    @ApiOperation(
            value = "Create user",
            notes = "Returns Json Data with the created User details"
    )
    @PostMapping
    public ResponseEntity<UserResponse> createUser(@RequestBody UserRequest userRequest) throws UserAlreadyExistsException
    {
        log.debug("Got create request for user : " + userRequest);
        User userDAO = convertUserRequestToUserModel(userRequest);
        User createdUserDAO = userService.createUser(userDAO);
        log.info("Created user : " + userRequest);
        UserResponse response = convertUserModelToUserResponse(createdUserDAO);
        return new ResponseEntity<UserResponse>(response, HttpStatus.CREATED);
    }

    @ApiOperation(
            value = "Update user",
            notes = "Returns Json Data with the updated User details"
    )
    @PutMapping
    public ResponseEntity<UserResponse> updateUser(@RequestBody UserRequest userRequest) throws UserNotFoundException {
        log.debug("Got update request for user : " + userRequest);
        User userDAO = convertUserRequestToUserModel(userRequest);
        User updatedUserDAO = userService.updateUser(userDAO);
        log.info("Updated user : " + userRequest);
        UserResponse response = convertUserModelToUserResponse(updatedUserDAO);
        return new ResponseEntity<UserResponse>(response, HttpStatus.OK);
    }


    @ApiOperation(
            value = "Delete user",
            notes = "Deletes user by userId"
    )
    @DeleteMapping( value = "/{userId}")
    @ResponseStatus(HttpStatus.OK)
    public void deleteUser(@PathVariable String userId) throws UserNotFoundException
    {
        log.debug("Got delete request for user : " + userId);
        userService.deleteUserByUserId(userId);
        log.info("Deleted user : " + userId);
    }

    @ApiOperation(
            value = "Get all users",
            notes = "Returns Json Data with all the Users details"
    )
    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<PageableCollection<UserResponse>> getUsers(@RequestParam(required = false, defaultValue = "0") Integer page,
                                                                    @RequestParam(required = false, defaultValue = "10") Integer pageSize)
    {
        log.debug(String.format("Got getUsers request. Page %d. Pagesize %d", page, pageSize));
        Page<User> userDAOs = userService.getAllUsers(page, pageSize);
        log.debug(String.format("Found users. Total: %d Pages: %d", userDAOs.getTotalElements(), userDAOs.getTotalPages()));
        PageableCollection<UserResponse> response = new PageableCollection(
                userDAOs.getTotalElements(),
                userDAOs.getTotalPages(),
                userDAOs.getContent().stream().map(this::convertUserModelToUserResponse).collect(Collectors.toList()));
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @GetMapping(value = "/{userId}" + SpringAllUtils.DIARIES)
    public ResponseEntity<DiaryResponse> getDiaryForUserForDate(@PathVariable String userId,
                                                                @RequestParam(value="date", required = true) @DateTimeFormat(pattern="dd-MM-yyyy") Date date) throws UserNotFoundException, DiaryNotFoundException {
        log.debug(String.format("Got getDiaryForUserForDate request. UserId %s. Date %s", userId, date));
        Diary diary = diaryService.searchDiaryForUser(userId, date);
        DiaryResponse response = convertDiaryModelToDiaryResponse(diary);
        log.debug(String.format("Returning Diary for UserId %s. Date %s", userId, date));
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @PostMapping(value = "/{userId}" + SpringAllUtils.DIARIES)
    public ResponseEntity<DiaryResponse> createDiaryForUser(@PathVariable String userId,
                                                            @RequestBody DiaryRequest diaryRequest) throws UserNotFoundException, DiaryAlreadyExistsException {
        log.debug(String.format("Got createDiaryForUser request. UserId %s. Date %s", userId, diaryRequest.getDate()));
        Diary diary = convertDiaryRequestToDiaryModel(diaryRequest, userId);
        Diary createdDiary = diaryService.createDiaryForUser(userId, diary);
        DiaryResponse response = convertDiaryModelToDiaryResponse(createdDiary);
        log.info(String.format("Created diary for UserId %s. Date %s", userId, response.getDate()));
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @PutMapping(value = "/{userId}" + SpringAllUtils.DIARIES)
    public ResponseEntity<DiaryResponse> updateDiaryForUser(@PathVariable String userId,
                                                            @RequestBody DiaryRequest diaryRequest) throws UserNotFoundException, DiaryNotFoundException {
        log.debug(String.format("Got updateDiaryForUser request. UserId %s. Date %s", userId, diaryRequest.getDate()));
        Diary diary = convertDiaryRequestToDiaryModel(diaryRequest, userId);
        Diary createdDiary = diaryService.updateDiaryForUser(userId, diary);
        DiaryResponse response = convertDiaryModelToDiaryResponse(createdDiary);
        log.info(String.format("Updated diary for UserId %s. Date %s", userId, response.getDate()));
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @DeleteMapping(value = "/{userId}" + SpringAllUtils.DIARIES)
    public void deleteDiaryForUser(@PathVariable String userId,
                                                        @RequestParam(value="date", required = true) @DateTimeFormat(pattern="dd-MM-yyyy") Date date) throws UserNotFoundException, DiaryNotFoundException {
        log.debug(String.format("Got deleteDiaryForUser request. UserId %s. Date %s", userId, date));
        diaryService.deleteDiaryForUser(userId, date);
        log.info(String.format("Deleted Diary for UserId %s. Date %s", userId, date));
    }

    @DeleteMapping(value = "/{userId}" + SpringAllUtils.DIARIES + SpringAllUtils.UPLOADFILE)
    public ResponseEntity<String> handleFileUpload(@RequestParam("file") MultipartFile diaryFile)
    {
        String response = "File upload successful";
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    private Diary convertDiaryRequestToDiaryModel(DiaryRequest request, String userId)
    {
        Diary diary = new Diary();
        diary.setKey(new DiaryID(userId, request.getDate()));
        diary.setMessage(request.getMessage());
        return diary;
    }

    private DiaryResponse convertDiaryModelToDiaryResponse(Diary diary)
    {
        DiaryResponse response = new DiaryResponse();
        response.setUserId(diary.getKey().getUserId());
        response.setDate(diary.getKey().getDate());
        response.setMessage(diary.getMessage());
        return response;
    }

    private UserResponse convertUserModelToUserResponse(User userDAO)
    {
        UserResponse userResponse = new UserResponse();
        userResponse.setName(userDAO.getDisplayName());
        userResponse.setEmailId(userDAO.getEmailId());
        userResponse.setUserId(userDAO.getUserId());
        return userResponse;
    }

    private User convertUserRequestToUserModel(UserRequest userRequest)
    {
        User userDAO = new User();
        userDAO.setDisplayName(userRequest.getName());
        userDAO.setEmailId(userRequest.getEmailId());
        userDAO.setPassword(userRequest.getPassword());
        userDAO.setUserId(userRequest.getUserId());
        return userDAO;
    }
}
