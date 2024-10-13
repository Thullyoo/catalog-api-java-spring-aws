package com.thullyoo.owner_catalog.exceptions.handler;

import com.thullyoo.owner_catalog.exceptions.CategoryNotExistsException;
import com.thullyoo.owner_catalog.exceptions.CategoryProductOwnerDiffException;
import com.thullyoo.owner_catalog.exceptions.ProductNotExistsException;
import com.thullyoo.owner_catalog.exceptions.UploadImageException;
import com.thullyoo.owner_catalog.exceptions.dto.ExceptionDTO;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalHandlerException {

    @ExceptionHandler(CategoryNotExistsException.class)
    public ResponseEntity<ExceptionDTO> categoryNotExistsException(CategoryNotExistsException e){
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ExceptionDTO("404", e.getMessage()));
    }

    @ExceptionHandler(ProductNotExistsException.class)
    public ResponseEntity<ExceptionDTO> productNotExistsException(ProductNotExistsException e){
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ExceptionDTO("404", e.getMessage()));
    }

    @ExceptionHandler(CategoryProductOwnerDiffException .class)
    public ResponseEntity<ExceptionDTO> categoryProductOwnerDiffException(CategoryProductOwnerDiffException e){
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ExceptionDTO("400", e.getMessage()));
    }

    @ExceptionHandler(UploadImageException.class)
    public ResponseEntity<ExceptionDTO> uploadImageException(UploadImageException e){
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new ExceptionDTO("500", e.getMessage()));
    }
}
