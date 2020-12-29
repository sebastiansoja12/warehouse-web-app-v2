package com.warehouse.warehouse.controller;

import com.warehouse.warehouse.service.CodeService;
import org.aspectj.apache.bcel.classfile.Code;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class CodeController {

    @GetMapping(value = "/generateAndDownloadQRCode/{text}")
    public void download(
            @PathVariable("text") String text)
            throws Exception {
        CodeService.generateQRCodeImage(text);
    }

    @GetMapping(value = "/generateQRCode/{text}")
    public ResponseEntity<byte[]> generateQRCode(
            @PathVariable("text") String text)
            throws Exception {
        return ResponseEntity.status(HttpStatus.OK).body(CodeService.getQRCodeImage(text));
    }
}