package com.saraya.surveyProject.controller;

import java.net.URI;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
//import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
//import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;


import com.saraya.surveyProject.model.Question;

import com.saraya.surveyProject.service.SurveyService;

@RestController
public class SurveyController {
	@Autowired
	private SurveyService ss;
	
	@GetMapping("/surveys/{surveyId}/questions")
	public List<Question> questionBySurvey(@PathVariable 
			String surveyId){
		return ss.retrieveQuestions(surveyId);
	}
	
	@PostMapping("/surveys/{surveyId}/questions")
  public  ResponseEntity<Void> addQuestion(@PathVariable String surveyId,
            @RequestBody Question newQuestion) {
	  			Question question = ss.addQuestion(surveyId, newQuestion);
        if (question == null) {
            return ResponseEntity.noContent().build();
        }

        URI location = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}").buildAndExpand(question.getId()).toUri();

        return ResponseEntity.created(location).build();

    }
		
	

}

