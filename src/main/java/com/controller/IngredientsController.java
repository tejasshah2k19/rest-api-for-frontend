package com.controller;

import java.util.List;

import javax.websocket.server.PathParam;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.entity.IngredientEntity;
import com.model.ResponseEntity;
import com.repository.IngredientRepository;

@RestController
@CrossOrigin
@RequestMapping("/api")
public class IngredientsController {

	@Autowired
	IngredientRepository ingredientRepository;

	@PostMapping("/ingredient")
	public ResponseEntity<IngredientEntity> addIngredient(@RequestBody IngredientEntity ig) {
		ResponseEntity<IngredientEntity> responseEntity = new ResponseEntity<>();

		if (ingredientRepository.findByName(ig.getName()) == null) {

			ingredientRepository.save(ig);
			responseEntity.setData(ig);
			responseEntity.setStatus(200);
			responseEntity.setMsg("item added!!");
		} else {
			responseEntity.setData(ig);
			responseEntity.setStatus(-1);
			responseEntity.setMsg("Item with this name is already present");
		}

		return responseEntity;
	}


	@PutMapping("/ingredient")
	public ResponseEntity<IngredientEntity> updateIngredient(@RequestBody IngredientEntity ig) {
		ResponseEntity<IngredientEntity> responseEntity = new ResponseEntity<>();

		if (ingredientRepository.findByName(ig.getName()) == null) {

			ingredientRepository.save(ig);
			responseEntity.setData(ig);
			responseEntity.setStatus(200);
			responseEntity.setMsg("item updated!!");
		} else {
			responseEntity.setData(ig);
			responseEntity.setStatus(-1);
			responseEntity.setMsg("Item with this name is already present");
		}

		return responseEntity;
	}

	
	@GetMapping("/ingredients")
	public ResponseEntity<List<IngredientEntity>> getAllIngredients() {
		ResponseEntity<List<IngredientEntity>> resp = new ResponseEntity<>();
		List<IngredientEntity> allIng = ingredientRepository.findAll();
		resp.setData(allIng);
		resp.setMsg("List retrvied");
		resp.setStatus(200);
		return resp;
	}

	@GetMapping("/ingredient/{id}")
	public ResponseEntity<IngredientEntity> getIngredientById(@PathVariable("id") Long id) {
		ResponseEntity<IngredientEntity> resp = new ResponseEntity<>();
		try {
			IngredientEntity ing = ingredientRepository.findById(id).get();

			resp.setData(ing);
			resp.setMsg("Item retrvied");
			resp.setStatus(200);
		} catch (Exception e) {
			resp.setData(null);
			resp.setMsg("Invalid Id");
			resp.setStatus(-1);
		}
		return resp;
	}

	
	@DeleteMapping("/ingredient/{id}")
	public ResponseEntity<List<IngredientEntity>> removeIngredientById(@PathVariable("id") Long id) {
		ResponseEntity<List<IngredientEntity>> resp = new ResponseEntity<>();
		try {
			ingredientRepository.deleteById(id);
			
			resp.setData(ingredientRepository.findAll());
			resp.setMsg("Item removed");
			resp.setStatus(200);
		} catch (Exception e) {
			resp.setData(null);
			resp.setMsg("Invalid Id");
			resp.setStatus(-1);
		}
		return resp;
	}
	
}
