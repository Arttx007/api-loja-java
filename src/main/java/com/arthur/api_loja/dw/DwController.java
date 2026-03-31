package com.arthur.api_loja.dw;

import com.arthur.api_loja.response.ApiResponse;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/dw")
public class DwController {

    private final DwService dwService;

    public DwController(DwService dwService) {
        this.dwService = dwService;
    }

    @GetMapping("/produtos/catalogo")
    public ApiResponse<List<DwProdutoCatalogoResponse>> catalogoProdutos() {
        return new ApiResponse<>(
                "sucesso",
                "Catalogo DW carregado com sucesso",
                dwService.listarCatalogoProdutos()
        );
    }
}
