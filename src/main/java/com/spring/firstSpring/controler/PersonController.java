package com.spring.firstSpring.controler;

import com.spring.firstSpring.dto.PersonDTO;
import com.spring.firstSpring.exceptions.MyErrorMessage;
import com.spring.firstSpring.service.PersonServiceImpl;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.List;


@RestController
@RequestMapping("/persons")
public class PersonController {

    private final PersonServiceImpl personService;

    public PersonController(PersonServiceImpl personService) {
        this.personService = personService;
    }

    @Operation(summary = "Gets person by ID", description = "Returns a person with the requested id")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Ok", content =
                    {@Content(mediaType = "application/json", schema =
                    @Schema(implementation = PersonDTO.class))}),
            @ApiResponse(responseCode = "404", description = "Person not found")})

    @GetMapping("/id/{id}")
    public ResponseEntity<Object> findById(@PathVariable Long id) {
        return personService.findById(id);
    }


    @Operation(summary = "Gets person by name", description = "Returns a person with the requested name")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Ok", content =
                    {@Content(mediaType = "application/json", schema =
                    @Schema(implementation = PersonDTO.class))}),
            @ApiResponse(responseCode = "404", description = "Person not found")})
    @GetMapping("/name/{name}")
    public ResponseEntity<List<PersonDTO>> findByName(@PathVariable String name) {
        return personService.findByName(name);
    }

    @Operation(summary = "Gets all persons", description = "Returns all existing person")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Ok", content =
                    {@Content(mediaType = "application/json", schema =
                    @Schema(implementation = PersonDTO.class))}),
            @ApiResponse(responseCode = "404", description = "Person not found", content = {
                    @Content(mediaType = "application/json", schema = @Schema(implementation = MyErrorMessage.class))
            }) })
    @GetMapping
    protected ResponseEntity<List<PersonDTO>> all() {
        return personService.all();
    }

    @Operation(summary = "Added new Person", description = "Added new person in database")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Created", content =
                    {@Content(mediaType = "application/json", schema =
                    @Schema(implementation = PersonDTO.class))}),
            @ApiResponse(responseCode = "400", description = "Inputted data is invalid", content = {
                    @Content(mediaType = "application/json", schema = @Schema(implementation = MyErrorMessage.class))
            }) })
    @PostMapping
    private ResponseEntity<Void> createPerson(@Valid @RequestBody PersonDTO newPersonRequest, UriComponentsBuilder ucb) {
        return personService.createPerson(newPersonRequest, ucb);
    }

    @Operation(summary = "Change person`s data", description = "Change person`s data, if person exists")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "Success", content =
                    {@Content(mediaType = "application/json", schema =
                    @Schema(implementation = PersonDTO.class))}),
            @ApiResponse(responseCode = "400", description = "Inputted data is invalid", content = {
                    @Content(mediaType = "application/json", schema = @Schema(implementation = MyErrorMessage.class))
            }),
            @ApiResponse(responseCode = "404", description = "Person not found", content = {
                    @Content(mediaType = "application/json", schema = @Schema(implementation = MyErrorMessage.class))
            })
    })
    @PutMapping("/{id}")
    private ResponseEntity<Void> putPerson(@PathVariable Long id, @Valid @RequestBody PersonDTO personDto) {
        return personService.putPerson(id, personDto);
    }

    @Operation(summary = "Deleted Person", description = "Delete new person from database")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "Success", content =
                    {@Content(mediaType = "application/json", schema =
                    @Schema(implementation = PersonDTO.class))}),
            @ApiResponse(responseCode = "400", description = "Inputted data is invalid", content = {
                    @Content(mediaType = "application/json", schema = @Schema(implementation = MyErrorMessage.class))
            }) })
    @DeleteMapping("/{id}")
    private ResponseEntity<Void> deletePerson(@PathVariable Long id) {
        return personService.deletePerson(id);
    }
}
