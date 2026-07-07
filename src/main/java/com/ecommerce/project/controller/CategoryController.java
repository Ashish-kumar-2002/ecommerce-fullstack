package com.ecommerce.project.controller;

import com.ecommerce.project.config.AppConstants;
import com.ecommerce.project.payload.CategoryDTO;
import com.ecommerce.project.payload.CategoryResponse;
import com.ecommerce.project.service.CategoryService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
public class CategoryController {

    @Autowired
    private CategoryService categoryService;


    @GetMapping("/echo")
    public ResponseEntity<String> echoMessage(@RequestParam(name = "message", required = false)String message){
        //public ResponseEntity<String> echoMessage(@RequestParam(name = "message" ,defaultValue = "Hello World ")String message)
        return new ResponseEntity<>("Echo Message: " + message,HttpStatus.OK);
    }

    @Tag(name = "Category API",description = "APIs for managing Products")
    @GetMapping("/public/categories")
    public ResponseEntity<CategoryResponse> getAllCategories(@RequestParam(name = "pageNumber" ,defaultValue = AppConstants.PAGE_NUMBER,required = false) Integer pageNumber,
                                                           @RequestParam(name = "pageSize" ,defaultValue = AppConstants.PAGE_SIZE,required = false) Integer pageSize,
                                                           @RequestParam(name = "sortBy",defaultValue = AppConstants.SORT_CATEGORIES_BY,required = false) String sortBy,
                                                             @RequestParam(name = "sortOrder",defaultValue = AppConstants.SORT_DIR,required = false) String sortOrder){
        CategoryResponse categoryResponse = categoryService.getAllCategories(pageNumber,pageSize,sortBy,sortOrder);
        return new ResponseEntity<>(categoryResponse,HttpStatus.OK);
    }

    @Tag(name = "Category API",description = "APIs for managing Products")
    @Operation(summary = "Create Category",description = "Api to create a new category")
    @ApiResponses({
            @ApiResponse(responseCode = "201",description = "Category is created successfully"),
            @ApiResponse(responseCode = "401",description = "Invalid Input",content = @Content),
            @ApiResponse(responseCode = "500",description = "Internal server error",content = @Content)
    })
    @PostMapping("/public/categories")
    public ResponseEntity<CategoryDTO> createCategory(@Parameter(description = "Category that you wish to create ")
            @Valid @RequestBody CategoryDTO categoryDTO){
        CategoryDTO savedCategoryDTO = categoryService.createCategory(categoryDTO);
        return new ResponseEntity<>( savedCategoryDTO,HttpStatus.CREATED);
    }

    @DeleteMapping("/admin/categories/{categoryId}")
    public ResponseEntity<CategoryDTO> deleteCategory(@Parameter(description = "ID of the Category that you wish to delete ")
            @PathVariable Long categoryId) {
            CategoryDTO deleteCategory = categoryService.deleteCategory(categoryId);
            return  ResponseEntity.status(HttpStatus.OK).body(deleteCategory);
    }

    @PutMapping("/public/categories/{categoryId}")
    public ResponseEntity<CategoryDTO>updateCategory(@Valid @RequestBody CategoryDTO categoryDTO,
                                                @PathVariable Long categoryId){
        CategoryDTO saveCategoryDTO  = categoryService.updateCategory(categoryDTO,categoryId);
        return new ResponseEntity<>(saveCategoryDTO , HttpStatus.OK);

    }
}
