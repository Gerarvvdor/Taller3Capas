package com.nalas.pnccontrollers.controllers;


import com.nalas.pnccontrollers.domain.dtos.GeneralResponse;
import com.nalas.pnccontrollers.domain.dtos.ChangeRolesDTO;
import com.nalas.pnccontrollers.domain.entities.User;
import com.nalas.pnccontrollers.services.UserService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/users")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/change")
    public ResponseEntity<GeneralResponse> changeRoles(@RequestBody @Valid ChangeRolesDTO info){

        User user = userService.findByIdentifier(info.getIdentifier());


        if(user == null) {
            return GeneralResponse.builder()
                    .message("User not found")
                    .status(HttpStatus.NOT_FOUND)
                    .build();
        }

        //userService.changeRoles(user, info.getRoles());

        return GeneralResponse.builder()
                .message("Roles changed")
                .status(HttpStatus.OK)
                .build();
    }

    @DeleteMapping("/delete/{identifier}")
    public ResponseEntity<GeneralResponse> deleteUser(@PathVariable String identifier) {

        User user = userService.findByUsernameOrEmail(identifier, identifier);

        if(user == null){
            return GeneralResponse.builder()
                    .message("User not found")
                    .status(HttpStatus.NOT_FOUND)
                    .build();
        }else if(!user.getActive()){
            return GeneralResponse.builder()
                    .message("User is already deleted")
                    .status(HttpStatus.NOT_FOUND)
                    .build();
        }else{

            boolean userDelete = userService.deleteFindOneByIdentifier(identifier);

            if(userDelete){
                return GeneralResponse.builder()
                        .message("User deleted")
                        .status(HttpStatus.OK)
                        .build();
            }else{
                return GeneralResponse.builder()
                        .message("User not deleted")
                        .status(HttpStatus.INTERNAL_SERVER_ERROR)
                        .build();
            }
        }
    }
}
