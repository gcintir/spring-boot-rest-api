package springbootrestapi.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import springbootrestapi.service.NoteService;

@RestController
@RequestMapping("/api")
public class NoteRestController {
	
	@Autowired
	private NoteService noteService;
	

}
